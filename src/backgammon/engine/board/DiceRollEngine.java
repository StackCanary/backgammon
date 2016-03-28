package backgammon.engine.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRollEngine {
	DiceRollHolder holder;
	Random random = new Random();
	private List<DiceRollHolder> history = new ArrayList<DiceRollHolder>();
	
	public DiceRollEngine() {
		
	}
	
	public DiceRollHolder getNext() {
		 holder = new DiceRollHolder(1 + random.nextInt(6),1 + random.nextInt(6));
		 history.add(holder);
		 return holder;
	}

	public DiceRollHolder getCurrent() {
		return holder;
	}
	
	public DiceRollHolder getRollForTurn(int turn) {
		return history.get(turn - 1);
	}
}
