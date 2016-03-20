package backgammon.client.control;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.SwingUtilities;
import javax.swing.plaf.SliderUI;

import backgammon.client.socket.Network;

/**
 * The purpose of this class is to provide an interface to communicate the
 * moves a player makes to the board representation
 * 
 * It will connect the Networking and the BasicBoard
 * @author bobby
 *
 */
public class GameController {
	private TriangleController triangleController;
	Network network = new Network();
	private Event event;
	
	public GameController(TriangleController triangleController) {
		this.triangleController = triangleController;
	}
	
	public void dispatchEventThread(ConcurrentLinkedQueue<Event> e) {
		Thread t = new Thread(
		new Runnable() {
			
			@Override
			public void run() {
				boolean quit = false;
				Event event;
				
				while(!quit) {
						while((event = e.poll()) != null) {
							System.out.println("We never reach this");
						}
				}
			}
		});
		
		t.start();
		
	}
	
	
	
	
}
