package backgammon.engine.player;

import backgammon.client.config.Config.Side;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;

public interface Player {
	
	public Side getSide();
	public boolean hasNextMove();
	public Pair getMove();
	public void update(SequenceOfMoves sequencesOfMoves);
	
}
