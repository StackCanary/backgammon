package backgammon.engine.board;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidMoveException(Pair pair) {
		this(pair.pos, pair.end);
	}
	
	public InvalidMoveException(int pos, int end) {
		super("Can't move from " + pos + " to " + end);
	}
}
