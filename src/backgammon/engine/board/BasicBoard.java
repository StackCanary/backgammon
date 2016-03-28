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
	private int whiteScore = 0;
	private int blackScore = 0;
	DiceRollHolder diceHolder;
	DiceRollEngine diceEngine = new DiceRollEngine();
	boolean gameOver = false;
	
	private List<Integer> currentMoveRemaining;
	
	Legal theLaw;
	private boolean autoDice = true;
	
	public BasicBoard(Side turn) {
		setBoard();
		theLaw = new Legal(this);
		setDice(diceEngine.getNext());
		this.turn = turn;
		
	}
	
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
		triangles = new ArrayList<TriangleInterface>();
		
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
		triangle.setN(n);
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
			boolean isBearingOff = theLaw.isLegalBearingOff(triangle, triangle + (left ? move : - move));
			// The error is here
			if (isLegal || isBearingOff) {
				legalMoveList.add(triangle + (left ? move : - move));
			} 
			
		}
		
		// This removes duplicates
		Set<Integer> legalSet = new LinkedHashSet<Integer>(legalMoveList);
		System.out.println("" + roll.x + ":" + roll.y +":"+ legalSet.toString());
		return new ArrayList<Integer>(legalMoveList);
	}
	
	enum Score {
		noMove (3),
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
	
	public void updateScore(int score) {
		if (this.turn == Side.black) {
			blackScore += score;
		} else {
			whiteScore += score;
		}
	}
	
	public Side getTurn() {
		return turn;
	}

	public int evaluate(Side side) {
		boolean flag = side == Side.black;
		
		int averageDistance = 0;
		int averageDistanceCounter = 0;
		int total = 0;
		for (TriangleInterface triangle : getTriangles() ) {
			int count;
			if ((count = triangle.getCount()) > 0 && triangle.getSide() == side) {
				 total += count;
				 averageDistance += Math.abs(flag ? triangle.getN() : 24 - triangle.getN());
				 averageDistanceCounter++;
			}
		}
		
		try {
			averageDistance = averageDistance / (averageDistanceCounter + 1);
		} catch (ArithmeticException e) {
			averageDistance = 10;
		}
		
		
		if (total == 0) {
			return 10000;
		} else {
			return averageDistance;
		}
		
	}
	
	public boolean hasEnded() {
		return gameOver;
	}
	
	public void setDice(DiceRollHolder holder) {
		this.diceHolder = holder;
	}
	
	public void changeDice(DiceRollHolder holder) {
		this.diceHolder = holder;
		autoDice = false;
	}
	
	public DiceRollHolder getDice() {
		return this.diceHolder;
	} 
	
	public void makeTwoMoves(int from1, int to1, int from2, int to2) {
		int myScore = 0;
		
		move(from1, to1);
		move(from2, to2);
	}
	
	public void makeAllMoves(MoveHolder holder) {
		
		Iterator<Pair> moves = holder.moves.iterator();
		while(moves.hasNext()) {
			Pair currentMove = moves.next();
			move(currentMove.pos, currentMove.end);
		}
		
	}
	
	@Override
	public boolean move(int from, int to) {
		boolean capture = false;
		boolean legal = false;
		boolean bearOff = false;
		boolean canMove = false;
		
		//System.out.println("From : " + getTriangle(from).getCount() + " : " + getTriangle(from).getSide());
		//System.out.println("To : " + getTriangle(to).getCount() + " : " + getTriangle(to).getSide());
		List<Integer> legalMoves = getPossibleMoves(from, diceHolder);
		
		if (legalMoves.isEmpty()) {
			updateScore(Score.noMove.getScore());
		}
		
		capture = theLaw.isCapture(from, to);
		bearOff = theLaw.isLegalBearingOff(from, to);
		canMove = theLaw.canMove(from, to);
		
		if (legalMoves.contains(to)) {
			if (bearOff) {
				bearOff(from);
				updateScore(Score.bearOff.getScore());
			} else if (capture) {
				remove(from);
				capture(to);
				updateScore(Score.capture.getScore());
			} else {
				getTriangle(to).setSide(getTriangle(from).getSide());
				remove(from);
				add(to);
				updateScore(Score.move.getScore());
			}
			
			diceHolder.clear(Math.abs(from - to));
		} else {
			System.out.println("Can't move from " + from + ":" + to);
		}
		
		if (diceHolder.options.isEmpty()) {
			switchSide();
			if (autoDice) {
				setDice(diceEngine.getNext());
			}
		}
		
		//System.out.println(evaluate(Side.black));
		checkGameOver();
		return true;
	}
	
	@Override
	public void capture(int triangle) {
		getTriangle(triangle).switchSide();
	}
	
	@Override
	public void bearOff(int from) {
	//	boolean bearOff = theLaw.isLegalBearingOff(from, getTriangle(from).getSide() == Side.black ? 25 : 0);

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
	public int getScore(Side max) {
		return evaluate(max);
	}


}
