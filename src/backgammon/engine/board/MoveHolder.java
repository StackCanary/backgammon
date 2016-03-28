package backgammon.engine.board;

import java.util.ArrayList;
import java.util.List;

public class MoveHolder {
	
	List<Pair> moves;
	
	public boolean doubleRoll = false;
	
	
	public MoveHolder(Pair m1, Pair m2) {
		moves.add(m1);
		moves.add(m2);
	}
	
	
	public MoveHolder(Pair m1, Pair m2, Pair m3, Pair m4) {
		
		doubleRoll = true;
	}
	
	
	public List<Pair> getList() {
		return moves;
	}
 


	public String getMessage() {
		String result = null;
		
		if (doubleRoll) {
			result = String.format(
					"(%d | %d), (%d | %d), (%d | %d), (%d | %d);", null
					);
		} else {
			result = String.format(
					"(%d | %d), (%d | %d);", null
					);
		}
		return result;
	}
}
