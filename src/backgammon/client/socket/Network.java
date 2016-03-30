package backgammon.client.socket;

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

public class Network {
	private Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	
	public String host;
	public int port;
	
	private SynchronousQueue<String> queue = new SynchronousQueue<String>();
	
	NetworkT myRole;
	
	public static enum NetworkT {
		client,
		server,
	}
	
	/**
	 * Start as client and connect to remote host:port
	 * @param host
	 * @param port
	 */
	public Network(String host, int port) {
		this.myRole = NetworkT.client;
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
		this.myRole = NetworkT.server;
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
	
	
	public void sendMessage(String message) {
		writer.println(message);
		writer.flush();
	}
	
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
	
	
	public void dispatchThread() {
		Thread t = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						String message;
						while((message = getMessage()) != null) {
							try {
								System.out.println(message);
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
