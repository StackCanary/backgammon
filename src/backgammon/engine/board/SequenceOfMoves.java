package backgammon.engine.board;

import java.util.ArrayList;
import java.util.List;

public class SequenceOfMoves {
	
	public List<Pair> moves = new ArrayList<Pair>();
	
	public SequenceOfMoves(Pair m1, Pair m2, Pair m3) {
		moves.add(m1);
		moves.add(m2);
		moves.add(m3);
		
	}
	
	public SequenceOfMoves(Pair m1, Pair m2) {
		moves.add(m1);
		moves.add(m2);
	}
	
	public SequenceOfMoves(Pair m1) {
		moves.add(m1);
	}
	
	//Clones the board and determines if a sequence can be played out
	public boolean isSequenceLegal(BasicBoard board) {
		BasicBoard clone = new BasicBoard(board.getTriangles(), board.getTurn());
		for (Pair pair : moves) {
			System.out.println("Possible Moves " + clone.getPossibleMoves(pair.pos, board.getDice()));
			if (clone.getPossibleMoves(pair.pos, board.getDice()).contains(pair.end)) {
				clone.move(pair.pos, pair.end);
			} else {
				return false;
			}
		}
		return true;
	}
	
	
	//Plays the move on the actual board
	public BasicBoard play(BasicBoard board) {
		for (Pair pair : moves) {
			board.move(pair.pos, pair.end);
		}
		
		return board;
	}

}