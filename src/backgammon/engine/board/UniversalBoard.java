package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import backgammon.client.config.Config.Side;
import backgammon.engine.player.TextPlayer;
import backgammon.engine.player.NetworkPlayer;
import backgammon.engine.player.Player;
import backgammon.engine.player.PlayerPipe;

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
						
						PlayerPipe players = new PlayerPipe(player1, player2);
						
						boolean flag = false;
						Player current = null, other = null;
						DiceRollHolder savedDice = null;
						SequenceOfMoves savedSequence = null;
						while(!gameOver) {
							if (flag) {
								Player save = current;
								current = other;
								other = save;
								System.out.println(current);
								System.out.println(other);
							} else {
								//Make sure the current player is the Network Player
								current = players.getPlayer();
								current = (current instanceof NetworkPlayer) ? current : players.getPlayer();
								other = players.getPlayer();
								flag = true;
								
							}
							
							if (current instanceof NetworkPlayer) {
								System.out.println("Network player's turn");
							}
							
							if (current instanceof TextPlayer) {
								System.out.println("GUI player's turn");
							}
							
							
							System.out.println("LOOPING");
							
							if (!completedTurn) {

								try {
									//Play individual moves
									if (current.playsIndividualMoves()) {
										if (current.refuseMove()) {
											continue;
										} else {
											Pair pair = current.getMove();
											moveWithException(pair);
										}
										
										
									//Play a sequence of moves
									} else {
										if (current.refuseMove()) {
											if (current instanceof NetworkPlayer) {
												System.out.println("Skipping network player");
											}
											
											if (current instanceof TextPlayer) {
												System.out.println("Skipping GUI player");
											}
											continue;
										} else {
											DiceAndSequencePair diceAndSequencePair = current.getDiceAndSequencePair();
											savedDice = diceAndSequencePair.holder;
											savedDice.reset();
											
											savedSequence = diceAndSequencePair.sequence;
											
											setDice(diceAndSequencePair.holder);
											makeAllMoves(diceAndSequencePair.sequence);
										}
										
									}

								} catch (InvalidMoveException e) {
									System.out.println(e.getMessage());
								}


							} 
								
								//At each completed turn update the other player
								if (other.playerReceivesSequences()) {
									//Pass the current sequence of moves and diceholder to the other Player
									other.updateThroughSequences(savedDice, savedSequence);
								}
								
								completedTurn = false;
								
								//We may have another if statement here if the other player receives moves
								//but we can avoid this


						}				
					}

				});

		t.run();
	}
	

	public void moveWithException(Pair pair) throws InvalidMoveException {
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
	public void makeAllMoves(SequenceOfMoves sequenceOfMoves) throws InvalidMoveException {

		Iterator<Pair> moves = sequenceOfMoves.moves.iterator();
		while(moves.hasNext()) {
			Pair currentMove = moves.next();
			moveWithException(currentMove);
		}
		
		completedTurn = true;

	}
}
