package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import backgammon.client.config.Config.Side;

public class Board {
	List<Triangle> triangles = new ArrayList<Triangle>();
	List<Triangle> save;
	Side turn = Side.black;
	
	public Board() {
		setBoard();
	}
	
	public Board(List<Triangle> triangles, Side turn) {
		this.triangles = triangles;
		this.turn = turn;
	}
	
	public void setBoard() {
		for (int i = 0; i < 24; i++) {
			triangles.add(new Triangle(i + 1)); 
		}
		
		drawNCountersAtTriangleT(1, 2, Side.black);
		drawNCountersAtTriangleT(6, 5, Side.white);
		
		drawNCountersAtTriangleT(8, 3, Side.white);
		drawNCountersAtTriangleT(12, 5, Side.black);
		
		drawNCountersAtTriangleT(13, 5, Side.white);
		drawNCountersAtTriangleT(17, 3, Side.black);
		
		drawNCountersAtTriangleT(19, 5, Side.black);
		drawNCountersAtTriangleT(24, 2, Side.white);
		
	}
	
	private void drawNCountersAtTriangleT(int n, int i, Side side) {
		Triangle triangle = getTriangle(n);
		triangle.setCount(i);
		triangle.setSide(side);
	}
	
	public List<Integer> getPossibleMoves(int triangle, DiceRollHolder roll) {
		
		Iterator<Integer> iterator = roll.options.iterator();
		List<Integer> legalMoveList = new ArrayList<Integer>();
		boolean left = (this.turn == Side.black);
		while(iterator.hasNext()) {
			int move = iterator.next();
			boolean isLegal = canMove(triangle, triangle + (left ? move : - move));
			if (isLegal) {
				legalMoveList.add(move);
			} 
		}
		
		return legalMoveList;
	}
	
	/**
	 * No support for bearing off yet
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean canMove(int from, int to) {
		boolean left = (this.turn == Side.black);
		if (left) {
			if (from > to) {
				return false;
			}
		} else {
			if (from < to) {
				System.out.println("False");
				return false;
			}
		}
		
		try {
			Triangle fromTriangle = getTriangle(from);
			Triangle toTriangle = getTriangle(to);
			
			if (fromTriangle.side != toTriangle.side) {
				if (toTriangle.count > 1) {
					return false;
				} 
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
		
		return true;
	}
	

	public Triangle getTriangle(int n) {
		return triangles.get(n - 1);
	}
 	
	public static int tooIndex(int i) {
		return i + 1;
	}
	
	public static void main(String[] args) {
		Board board = new Board();
//		for (Triangle triangle : board.triangles) {
//			System.out.println(triangle.count);
//		}
		
		List<Integer> moves = board.getPossibleMoves(1, new DiceRollHolder(4, 5));
		System.out.println(moves);
	}
}
