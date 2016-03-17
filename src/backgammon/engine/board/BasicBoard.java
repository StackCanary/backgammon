package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import backgammon.client.config.Config.Side;

public class BasicBoard implements BoardInterface {
	List<TriangleInterface> triangles = new ArrayList<TriangleInterface>();
	List<TriangleInterface> save;
	Side turn = Side.black;
	boolean capture = false;
	boolean legal = false;
	
	Legal theLaw;
	
	public BasicBoard() {
		setBoard();
		theLaw = new Legal(this);
	}
	
	public BasicBoard(List<TriangleInterface> triangles, Side turn) {
		this.triangles = triangles;
		this.turn = turn;
		theLaw = new Legal(this);
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
	
	@Override
	public void drawNCountersAtTriangleT(int n, int i, Side side) {
		TriangleInterface triangle = getTriangle(n);
		triangle.setCount(i);
		triangle.setSide(side);
	}
	
	@Override
	public List<Integer> getPossibleMoves(int triangle, DiceRollHolder roll) {
		
		Iterator<Integer> iterator = roll.options.iterator();
		List<Integer> legalMoveList = new ArrayList<Integer>();
		boolean left = (this.turn == Side.black);
		while(iterator.hasNext()) {
			int move = iterator.next();
			boolean isLegal = theLaw.canMove(triangle, triangle + (left ? move : - move));
			if (isLegal) {
				legalMoveList.add(move);
			} 
		}
		
		return legalMoveList;
	}
	@Override
	public boolean move(int from, int to) {
		legal = theLaw.canMove(from, to);
		capture = theLaw.isCapture(from, to);
		
		if (legal) {
			if (capture) {
				capture(to);
				remove(from);
			} else {
				add(to);
				remove(from);
			}
		}
		
		return legal;
	}
	
	@Override
	public void capture(int triangle) {
		getTriangle(triangle).switchSide();
		add(triangle);
	}
	
	@Override
	public void bearOff(int from) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void add(int triangle) {
		getTriangle(triangle).setCount(getTriangle(triangle).getCount() + 1);
	}
	
	@Override
	public void remove(int triangle) {
		getTriangle(triangle).setCount(getTriangle(triangle).getCount() - 1);
	}
	
	@Override
	public List<TriangleInterface> getTriangles() {
		return this.triangles;
	}
	

	@Override
	public TriangleInterface getTriangle(int n) {
		return triangles.get(n - 1);
	}
 	
	public static int tooIndex(int i) {
		return i + 1;
	}
	
	public static void main(String[] args) {
		BasicBoard board = new BasicBoard();
//		for (Triangle triangle : board.triangles) {
//			System.out.println(triangle.count);
//		}
		
		List<Integer> moves = board.getPossibleMoves(1, new DiceRollHolder(4, 5));
		System.out.println(moves);
	}


}
