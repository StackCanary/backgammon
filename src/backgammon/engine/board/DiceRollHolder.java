package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import backgammon.engine.ai.Scorable;

public class DiceRollHolder implements Scorable {
	public final int x;
	public final int y;
	public List<Integer> options = new ArrayList<Integer>();
	
	public DiceRollHolder(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.options.add(x);
		this.options.add(y);
		this.options.add(x + y);
		
		this.options.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				/**
				 * www.java2s.com/Tutorial/Java/0140__Collections/ImplementingaComparatorforaclass.htm
				 * ternary expressions for expressing this in one line
				 */
				int result = (o2 > o1) ? -1 : ((o2 == o1) ? 0 : 1);
				return result;
				
			}
		}); 
		
	}
	
	public void clear(int i) {
		if (i == x + y) {
			options.clear();
		}
		
		if (i == x) { 
			options.remove((Object) i);
			options.remove((Object) (x + y));
		}
		
		if (i == y) {
			options.remove((Object) i);
			options.remove((Object) (x + y));
		}
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Scorable> getChildren(Scorable o) {
		if (o instanceof BasicBoard) {
			
		}
		return null;
	}
	
	public static double getExpectedValue(int x, int y) {
		return (x);
	}
		
}
