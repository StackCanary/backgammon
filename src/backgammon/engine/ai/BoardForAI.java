package backgammon.engine.ai;

import backgammon.engine.board.BasicBoard;

public class BoardForAI extends BasicBoard {
	enum Score {
		move (1),
		stack (2),
		capture (4),
		bearOff(3);
		
		final int score;
		
		Score(int score) {
			this.score = score;
		}
		
	}
	
	public BoardForAI() {
		
	}
	
	public int evaluate() {
		return 0;
	}
}
