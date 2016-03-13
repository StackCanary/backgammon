package backgammon.client.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Network {
	private Socket socket;
	OutputStream outputStream;
	InputStream inputStream;
	
	public final String host = "localhost";
	public final int port = 3713;
	
	public Network() {
		try {
			socket = new Socket(host, port);
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			
		} catch (IOException e) {
			System.out.println("Perhaps the host is down");
		}
		
	}
	
	public void sendMessage(String message) {
		try {
			outputStream.write(message.getBytes());
		}  catch (IOException e) {
			System.out.println("Insufficient permissions to create socket");
			System.out.println("Try a lower port number");
		}
	}
	
	public static void main(String[] args) {
		Network n = new Network();
		n.sendMessage("HELLO\n");
	}
	
}
