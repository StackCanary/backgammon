package backgammon.engine.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import backgammon.client.config.Config.Side;

public class TextBoard {
	public BasicBoard myBoard = new BasicBoard();
	
	private static String white(String message) {
		return "\033[1m" + message + "\033[0m"; 
	}
	
	private static String black(String message) {
		return message;
	}
	
	public TextBoard() {
		
	}
	
	public void printNumber(int i) {
		System.out.print(getFormattedNumber(i));
	}
	
	public String getFormattedNumber(int i) {
		if (i < 10) {
			return (i + " ");
		} else {
			return (i + "");
		}
	}
	
	public void printCounter(TriangleInterface triangle) {
		boolean sideBlack = (triangle.getSide() == Side.black);
		String result = "" + getFormattedNumber(triangle.getCount());
		
		
		if (sideBlack || triangle.getCount() == 0) {
			System.out.print(black(result));
		} else {
			System.out.print(white(result));
		}
		
	}
	
	public void print() {
		System.out.println("Turn " + myBoard.getTurn());
		System.out.println("DiceRoll " + myBoard.getDice().getMessage());
		
		for (int i = 12; i < 24; i++) {
			printNumber(i + 1);
			System.out.print(" ");
		}
		
		System.out.println();
		
		for (int i = 12; i < 24; i++) {
			printCounter(myBoard.getTriangle(i + 1));
			System.out.print(" ");
		}
		
		for (int i = 0; i < 12; i++) {
			System.out.print(" ");
		}
		
		System.out.println();
		System.out.println();
		
		
		for (int i = 12; i > 0; i--) {
			printCounter(myBoard.getTriangle(i));
			System.out.print(" ");
		}
		
		System.out.println();
		
		for (int i = 12; i > 0; i--) {
			printNumber(i);
			System.out.print(" ");
		}
		
		
	}
	
	public void drawCounters() {
		
	}
	
	public DiceAndSequencePair getInput() {
		List<Pair> pairs = new ArrayList<Pair>();
		DiceRollHolder holder = myBoard.diceHolder;
		Side turn = myBoard.getTurn();
		Side save = myBoard.getTurn();
		
		while(save == turn) {
			print();
			Pair pair = getPairFromUser();
			if (!myBoard.move(pair.pos, pair.end)) {
				System.out.println("Invalid move, please try again");
			} else {
				save = myBoard.getTurn();
				pairs.add(pair);
			}
			
		}
		
		System.out.println("Returning dice from TextBoard " + holder.x +":"+ holder.y);
		
		SequenceOfMoves sequences = new SequenceOfMoves(pairs);
		return new DiceAndSequencePair(holder, sequences);
	}
	
	public static Pair getPairFromUser() {
		Scanner scanner = new Scanner(System.in);
		boolean complete = false;
		Pair pair = null;
		
		while(!complete) {
			System.out.println();
			System.out.print(">");
			String result = scanner.nextLine();
			if (result.matches("^[0-9]+?\\,[0-9]+?$")) {
				String[] split = result.split(",");
				int from = (Integer.parseInt(split[0]));
				int to = (Integer.parseInt(split[1]));
				
				if ((from >= 1 && from <= 24) && (to >= 1 && to <= 24)) {
					pair = new Pair(from, to);
					complete = true;
				}
				
			} 
		}
		
		return pair;
	}
	
	
}

