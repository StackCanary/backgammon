package backgammon.engine.player;

import backgammon.client.config.Config.Side;
import backgammon.engine.board.DiceAndSequencePair;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;

public interface Player {
	
	public Side getSide();
	public void setSide(Side side);
	
	public boolean playsIndividualMoves();
	public DiceAndSequencePair getDiceAndSequencePair();
	public Pair getMove();
	
	public void updateThroughSequences(DiceRollHolder holder, SequenceOfMoves sequencesOfMoves);
	void updateThroughMove(DiceRollHolder holder, Pair move);
	
	boolean playerReceivesIndividialMove();
	boolean playerReceivesSequences();
	
	public DiceRollHolder getDiceRoll();
	
	public void error();
	
	public boolean refuseMove();
	
}
