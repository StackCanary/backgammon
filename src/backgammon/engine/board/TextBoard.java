package backgammon.engine.board;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

import backgammon.client.config.Config.Side;

public class TextBoard {
	private BasicBoard myBoard = new BasicBoard();
	
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
	
	public void getInput() {
		
	}
	
	public static void main(String[] args) {
		TextBoard textBoard = new TextBoard();
		textBoard.print();
	}
	
	
	
}

