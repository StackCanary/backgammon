package backgammon.client.control;

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
	
	public GameController(TriangleController triangleController) {
		this.triangleController = triangleController;
	}
	
	
	
	
}
