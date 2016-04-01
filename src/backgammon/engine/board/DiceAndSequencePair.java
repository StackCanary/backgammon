package backgammon.engine.board;

public class DiceAndSequencePair {
	public DiceRollHolder holder;
	public SequenceOfMoves sequence;
	
	public DiceAndSequencePair(DiceRollHolder holder, SequenceOfMoves sequence) {
		this.holder = holder;
		this.sequence = sequence;
	}
}
