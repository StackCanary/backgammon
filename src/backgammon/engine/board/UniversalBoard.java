package backgammon.engine.board;

import java.util.ArrayList;
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
					
					
					if (current.playsIndividualMoves()) {
						Pair pair = current.getMove();
						move(pair);
						
						if (other.playerReceivesSequences()) {
			
						}
					} else {
						SequenceOfMoves sequence = current.getSequenceOfMoves();
						makeAllMoves(sequence);
						
						if (other.playerReceivesSequences()) {
							other.updateThroughSequences(diceHolder, sequence);
						}
					}
					
				}				
			}
			
		});
		
		t.run();
	}
	
	
	public void move(Pair pair) {
		move(pair.pos, pair.end);
	}
}
