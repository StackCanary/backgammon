package backgammon.engine.player;

import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;

import backgammon.client.config.Config.Side;
import backgammon.client.control.Event;
import backgammon.client.control.GameController;
import backgammon.client.control.TriangleController;
import backgammon.client.ui.shape.EmptyCircle;
import backgammon.client.ui.shape.HeadCircle;
import backgammon.client.ui.ui.Client;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;

public class GUIPlayer implements Player {
	private TriangleController tController;
	private SynchronousQueue<Event> queue;
	private Side side;
	
	private Side currentTurn;
	private Side saveTurn;
	
	public GUIPlayer() {
		Client client = new Client();
		client.run();
		this.queue = client.gameview.eventQueue;
		this.tController = client.gameview.triangleController;
		
		currentTurn = client.gameview.triangleController.board.getTurn();
		saveTurn = currentTurn;
	}

	@Override
	public Side getSide() {
		return side;
	}

	@Override
	public void setSide(Side side) {
		this.side = side;
	}
	
	/**
	 * Not supported
	 * @return
	 */
	@Override
	public boolean playsIndividualMoves() {
		return false;
	}

	@Override
	public SequenceOfMoves getSequenceOfMoves() {
		SequenceOfMoves move;
		while((move = tController.board.getSequence()) == null) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return move;
	}

	//Not supported
	@Override
	public Pair getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Supported 
	 */
	@Override
	public void updateThroughSequences(DiceRollHolder holder, SequenceOfMoves sequencesOfMoves) {
		tController.board.changeDice(holder);
		Iterator<Pair> iterator = sequencesOfMoves.getIterator();
		while(iterator.hasNext()) {
			Pair pair = iterator.next();
			queue.add(new Event(new EmptyCircle(true, pair.pos, null), null));
			queue.add(new Event( null, new HeadCircle(null, pair.end, null)));
		}
	}

	/**
	 * 
	 */
	@Override
	public void updateThroughMove(DiceRollHolder holder, Pair move) {
		//Unsupported
	}

	@Override
	public boolean playerReceivesIndividialMove() {
		return false;
	}

	@Override
	public boolean playerReceivesSequences() {
		return true;
	}

	@Override
	public DiceRollHolder getDiceRoll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void error() {
		// TODO Auto-generated method stub
	}

	
	
}
