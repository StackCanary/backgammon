package backgammon.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * This class abstracts the process of sending and receiving data from another machine
 * @author bobby
 *
 */
public class Network {
	private Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	
	public String host;
	public int port;
	
	private SynchronousQueue<String> queue = new SynchronousQueue<String>();
	
	NetworkRole myRole;
	
	public static enum NetworkRole {
		client,
		server,
	}
	
	/**
	 * Start as client and connect to remote host:port
	 * @param host
	 * @param port
	 */
	public Network(String host, int port) {
		this.myRole = NetworkRole.client;
		this.host = host;
		this.port = port;
		
		try {
			socket = new Socket(host, port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			dispatchThread();
			
		} catch (IOException e) {
			System.out.println("Perhaps the host is down");
		}
		
	}
	
	/**
	 * Start as server listening on port
	 * @param port
	 */
	public Network(int port) {
		this.myRole = NetworkRole.server;
		this.port = port;
		
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
		    socket = serverSocket.accept();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			serverSocket.close();
			dispatchThread();
			
		} catch (IOException e) {
			System.out.println("Perhaps the host is down");
		}
		
	}
	
	/**
	 * Send a message to the other machine
	 * @param message
	 */
	public void sendMessage(String message) {
		System.out.println("Sent: " + message);
		writer.println(message);
		writer.flush();
	}
	
	/**
	 * Receive a message from the other machine, only to be used within this class
	 * @return
	 */
	public String getMessage() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Queue<String> getQueue() {
		return queue;
	}
	
	/**
	 * Launch dispatch thread if you want to receive all messages immediately and store them in a queuue
	 * perhaps this can minimise blocking 
	 */
	public void dispatchThread() {
		Thread t = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						String message;
						while((message = getMessage()) != null) {
							try {
								System.out.println("Received: " + message);
								queue.put(message);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				); 
		
		t.start();
	}

	
}