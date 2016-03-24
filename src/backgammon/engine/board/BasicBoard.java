package backgammon.engine.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import backgammon.client.config.Config.Side;
import backgammon.engine.ai.Scorable;

public class BasicBoard implements BoardInterface, Scorable {
	List<TriangleInterface> triangles = new ArrayList<TriangleInterface>();
	List<TriangleInterface> save;
	Side turn = Side.black;
	private int blackCounters = 15;
	private int whiteCounters = 15;
	DiceRollHolder diceHolder;
	DiceRollEngine diceEngine = new DiceRollEngine();
	boolean gameOver = false;

	Score score = null; 
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
		System.out.println(triangle);
		boolean left = (getTriangle(triangle).getSide() == Side.black);
		while(iterator.hasNext()) {
			int move = iterator.next();
			boolean isLegal = theLaw.canMove(triangle, triangle + (left ? move : - move));
			if (isLegal) {
				legalMoveList.add(triangle + (left ? move : - move));
			} 
		}
		
		Set<Integer> legalSet = new LinkedHashSet<Integer>(legalMoveList);
		return new ArrayList<Integer>(legalMoveList);
	}
	
	
	enum Score {
		noMove (0),
		move (1),
		stack (2),
		capture (4),
		bearOff(3);
		
		final int score;
		
		Score(int score) {
			this.score = score;
		}
		
		public int getScore() {
			return score;
		}
		
	}
	
	public Side getTurn() {
		return turn;
	}

	public int evaluate() {
		return this.score.getScore();
	}
	
	public boolean hasEnded() {
		return gameOver;
	}
	
	public void setDice(DiceRollHolder holder) {
		this.diceHolder = holder;
	}
	
	public DiceRollHolder getDice() {
		return this.diceHolder;
	} 
	
	@Override
	public boolean move(int from, int to) {
		boolean capture = false;
		boolean legal = false;
		boolean bearOff = false;
		boolean canMove = false;
		
		System.out.println("From : " + getTriangle(from).getCount() + " : " + getTriangle(from).getSide());
		System.out.println("To : " + getTriangle(to).getCount() + " : " + getTriangle(to).getSide());
		List<Integer> legalMoves = getPossibleMoves(from, diceHolder);
		
		if (legalMoves.isEmpty()) {
			this.score = Score.noMove;
		}
		
		System.out.println(from);
		System.out.println(legalMoves);
		capture = theLaw.isCapture(from, to);
		bearOff = theLaw.isLegalBearingOff(from, to);
		canMove = theLaw.canMove(from, to);
		
		if (legalMoves.contains(to)) {
			if (bearOff) {
				bearOff(from);
				this.score = Score.bearOff;
			} else if (capture) {
				remove(from);
				capture(to);
				this.score = Score.capture;
			} else {
				getTriangle(to).setSide(getTriangle(from).getSide());
				remove(from);
				add(to);
				this.score = Score.move;
			}
			
			diceHolder.clear(Math.abs(from - to));
		} else {
			System.out.println("Doesn't contain " + to);
		}
		
		if (diceHolder.options.isEmpty()) {
			switchSide();
			setDice(diceEngine.getNext());
		}
		
		checkGameOver();
		System.out.println("From : " + getTriangle(from).getCount() + " : " + getTriangle(from).getSide());
		System.out.println("To : " + getTriangle(to).getCount() + " : " + getTriangle(to).getSide());
		return true;
	}
	
	@Override
	public void capture(int triangle) {
		getTriangle(triangle).switchSide();
	}
	
	@Override
	public void bearOff(int from) {
		remove(from);
		if (turn == Side.black) {
			blackCounters--;
		} else {
			whiteCounters--;
		}
	}
	
	@Override
	public void add(int triangle) {
		getTriangle(triangle).setCount(getTriangle(triangle).getCount() + 1);
		System.out.println("Count " + getTriangle(triangle).getCount());
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
			gameOver = true;
		}
		
		if (whiteCounters == 0) {
			System.out.println("White won");
			gameOver = true;
		}
	}

	@Override
	public int getScore() {
		return evaluate();
	}

	@Override
	public List<Scorable> getChildren(Scorable o) {
		List<Scorable> result = new ArrayList<Scorable>();
		
		for (int j = 1; j <= 6; j++) {
			for (int i = 1; i <= 6; i++) {
				result.add(new DiceRollHolder(i, j));
			}
		}
		
		return result;
	}


}
