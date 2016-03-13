package backgammon.client.control;

import java.util.Queue;

import backgammon.client.socket.Network;

public class ClientController {
	private Network network;
	private Queue<Message> queue;
	
	public ClientController() {
		Network network = new Network();
	}
	
	
	
}
