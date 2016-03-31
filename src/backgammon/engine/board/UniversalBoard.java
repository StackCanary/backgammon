package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import backgammon.client.config.Config.Side;
import backgammon.engine.player.Player;

public class UniversalBoard extends BasicBoard {
	Queue<OldMoveHolder> queue = new SynchronousQueue<OldMoveHolder>();

	Player player1;
	Player player2;

	public UniversalBoard(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		gameLoop();
	}

	public UniversalBoard(List<TriangleInterface> triangles, Side black) {
		super(triangles, black);
	}

	public void gameLoop() {

		Thread t = new Thread(
				new Runnable() {

					@Override
					public void run() {
						List<Pair> sequencesOfMoves = new ArrayList<Pair>();

						while(!gameOver) {
							Player current = (player1.getSide() == getTurn()) ? player1 : player2;
							Player other = (player1.getSide() == getTurn()) ? player2 : player1;


							if (!completedTurn) {

								try {
									//Play individual moves
									if (current.playsIndividualMoves()) {
										Pair pair = current.getMove();
										move(pair);

									//Play a sequence of moves
									} else {
										SequenceOfMoves sequence = current.getSequenceOfMoves();
										makeAllMoves(sequence);

									}

								} catch (InvalidMoveException e) {
									System.out.println(e.getMessage());
								}


							} else {
								
								//At each completed turn update the other player
								if (other.playerReceivesSequences()) {

								}
								
								
								//We may have another if statement here if the other player receives moves
								//but we can avoid this

							}

						}				
					}

				});

		t.run();
	}


	public void move(Pair pair) throws InvalidMoveException {
		boolean madeMove = move(pair.pos, pair.end);

		if (!madeMove) {
			throw new InvalidMoveException(pair);
		}
	}

	/**
	 * This method will try and make a move in the universal board and throw an exception if it cannot
	 * make the move
	 * 
	 * @param sequenceOfMoves
	 * @throws InvalidMoveException
	 */
	public void makeAllMoves(SequenceOfMoves sequenceOfMoves)throws InvalidMoveException {

		Iterator<Pair> moves = sequenceOfMoves.moves.iterator();
		while(moves.hasNext()) {
			Pair currentMove = moves.next();
			move(currentMove);
		}

	}
}
