package backgammon.engine.board;

import java.util.Random;

public class DiceRollEngine {
	DiceRollHolder holder;
	Random random = new Random();
	
	public DiceRollEngine() {
		
	}
	
	public DiceRollHolder getNext() {
		 holder = new DiceRollHolder(1 + random.nextInt(7),1 + random.nextInt(7));
		 System.out.println("options " + holder.options.toString());
		 return holder;
	}
	
	public DiceRollHolder getCurrent() {
		return holder;
	}
}
