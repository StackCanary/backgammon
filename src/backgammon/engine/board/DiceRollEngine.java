package backgammon.engine.board;

import java.util.Random;

public class DiceRollEngine {
	DiceRollHolder holder;
	Random random = new Random();
	
	public DiceRollEngine() {
		
	}
	
	public DiceRollHolder getNext() {
		return (holder = new DiceRollHolder(random.nextInt(7), random.nextInt(7)));
	}
	
	public DiceRollHolder getCurrent() {
		return holder;
	}
}
