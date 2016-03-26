package backgammon.client.control;

import java.util.concurrent.SynchronousQueue;

import backgammon.client.config.Config.Side;
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
	
	public void dispatchEventThread(SynchronousQueue<Event> e) {
		Thread t = new Thread(
		new Runnable() {
			
			@Override
			public void run() {
				boolean quit = false;
				Event event;
				
				int lastLastClicked = 0;
				
				while(!quit) {
						while((event = e.poll()) != null) {
							
							if (event.hCircle != null) {
								if (event.clicks == 1) {
									triangleController.unhighlightAll();
									triangleController.highlightAllPossibleMoves(event.hCircle.n);
									lastLastClicked = event.hCircle.n;
								} else {
									if (triangleController.getTriangle(event.hCircle.n).getSide() == Side.white) {
										triangleController.move(event.hCircle.n, 0);
									} else {
										triangleController.move(event.hCircle.n, 25);
									}
								}
								continue;
							}
							
							if (event.eCircle != null) {
								triangleController.move(lastLastClicked, event.eCircle.n);
								triangleController.unhighlightAll();
								continue;
							}
							
 						}
				}
			}
		});
		
		t.start();
		
	}
	
	
	
	
}
