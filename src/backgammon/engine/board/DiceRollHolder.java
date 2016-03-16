package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DiceRollHolder {
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
	
		
}
