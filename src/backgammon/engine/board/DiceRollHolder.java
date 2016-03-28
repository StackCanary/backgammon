package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import backgammon.client.config.Config.Side;
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
	
	/*
	 * Returns all the sequences that can be made from a given dice roll regardless of whether they are legal or not
	 */
	public List<SequenceOfMoves> getSequencesOfMoves (int from, Side side) {
		boolean left = side == Side.white;
		List<SequenceOfMoves> sequences = new ArrayList<SequenceOfMoves>();
		Pair m1 = new Pair(from, from + (left ? - x : x));
		Pair m2 = new Pair(from, from + (left ? - y : y));
		Pair m3 = new Pair(from, from + (left ? - (x + y) : (x + y)));
		
		//You can play m1 and m2 in any order (m1 then m2)
		SequenceOfMoves sequence1 = new SequenceOfMoves(m1, m2);
		
		//You can play m1 and m2 in any order (m2 then m1)
		SequenceOfMoves sequence2 = new SequenceOfMoves(m2, m1);
		
		//This is the sum sequence where you add the dice
		SequenceOfMoves sequence3 = new SequenceOfMoves(m3);
		
		sequences.add(sequence1);
		sequences.add(sequence2);
		sequences.add(sequence3);
		
		return sequences;
	}
	

	@Override
	public int getScore(Side max) {
		// TODO Auto-generated method stub
		return 1;
	}

	
	
	public static double getExpectedValue(int x, int y) {
		return (x);
	}
		
}
