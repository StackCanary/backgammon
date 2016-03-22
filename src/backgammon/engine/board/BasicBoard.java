package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import backgammon.client.config.Config.Side;

public class BasicBoard implements BoardInterface {
	List<TriangleInterface> triangles = new ArrayList<TriangleInterface>();
	List<TriangleInterface> save;
	Side turn = Side.black;
	private int blackCounters = 15;
	private int whiteCounters = 15;
	DiceRollHolder diceHolder;
	DiceRollEngine diceEngine = new DiceRollEngine();
	boolean capture = false;
	boolean legal = false;
	
	private List<Integer> currentMoveRemaining;
	
	Legal theLaw;
	
	public BasicBoard() {
		setBoard();
		theLaw = new Legal(this);
		setDice(diceEngine.getNext());
	}
	
	public BasicBoard(List<TriangleInterface> triangles, Side turn) {
		this.triangles = triangles;
		this.turn = turn;
		theLaw = new Legal(this);
		setDice(diceEngine.getNext());
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
		boolean left = (getTriangle(triangle).getSide() == Side.black);
		while(iterator.hasNext()) {
			int move = iterator.next();
			boolean isLegal = theLaw.canMove(triangle, triangle + (left ? move : - move));
			if (isLegal) {
				legalMoveList.add(move);
			} 
		}
		
		return legalMoveList;
	}
	
	public void setDice(DiceRollHolder holder) {
		this.diceHolder = holder;
	}
	
	public DiceRollHolder getDice() {
		return this.diceHolder;
	} 
	
	@Override
	public boolean move(int from, int to) {
		
		List<Integer> legalMoves = getPossibleMoves(from, diceHolder);
		System.out.println(from);
		System.out.println(legalMoves);
		capture = theLaw.isCapture(from, to);
		
		if (legalMoves.contains(to)) {
			if (capture) {
				capture(to);
				remove(from);
			} else {
				add(to);
				remove(from);
			}
			
			diceHolder.options.remove((Object) Math.abs(from - to));
		} else {
			System.out.println("Doesn't contain " + to);
		}
		
		if (diceHolder.options.isEmpty()) {
			switchSide();
			setDice(diceEngine.getNext());
		}
		
		checkGameOver();
		
		return true;
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
	
	@Override
	public void switchSide() {
		this.turn = (this.turn == Side.black) ? Side.white : Side.black;
	}
	
	public void checkGameOver() {
		if (blackCounters == 0) {
			System.out.println("Black won");
		}
		
		if (whiteCounters == 0) {
			System.out.println("White won");
		}
	}


}
