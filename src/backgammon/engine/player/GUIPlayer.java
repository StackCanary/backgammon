package backgammon.engine.player;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

import backgammon.client.config.Config.Side;
import backgammon.client.control.Event;
import backgammon.client.control.GameController;
import backgammon.client.control.TriangleController;
import backgammon.client.ui.shape.EmptyCircle;
import backgammon.client.ui.shape.HeadCircle;
import backgammon.client.ui.ui.Client;
import backgammon.engine.board.DiceAndSequencePair;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;

public class GUIPlayer implements Player {
	private TriangleController tController;
	private SynchronousQueue<Event> queue;
	private ConcurrentLinkedQueue<SequenceOfMoves> moveQueue = new ConcurrentLinkedQueue<SequenceOfMoves>();
	private Side side;
	
	private Client client;
	
	private Side currentTurn;
	private Side saveTurn;
	
	public GUIPlayer() {
		client = client.createUIAndgetReference();
		this.tController = client.gameview.triangleController;
		
		//currentTurn = client.gameview.triangleController.board.getTurn();
	//	saveTurn = currentTurn;
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
	public DiceAndSequencePair getDiceAndSequencePair() {
		// TODO Auto-generated method stub
		SequenceOfMoves move;
		
		System.out.println("Back in the GUIPlayer");
		
		DiceAndSequencePair sequencePair;
		while((sequencePair = tController.board.sequenceQueue.poll()) == null) {
			
		}

		return sequencePair;
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
			tController.move(pair.pos, pair.end);
			try {
				tController.getGameController().queue.put(new Event(null, null));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	@Override
	public boolean refuseMove() {
		return false;
	}


	
	
}
