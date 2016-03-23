package backgammon.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import backgammon.client.config.Config.Side;
import backgammon.client.ui.shape.Triangle;
import backgammon.engine.board.BasicBoard;
import backgammon.engine.board.BoardInterface;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.TriangleInterface;

public class TriangleController implements BoardInterface{
	
	private List<TriangleInterface> triangles;
	private BasicBoard board;
	private GameController controller;
	private SynchronousQueue<Event> eventQueue;
	private List<Integer> highlighted = new ArrayList<Integer>();
	public int lastClicked;
	
	public TriangleController(List<Triangle> triangles) {
		this.triangles = triangles.stream().map(x -> (TriangleInterface) x).collect(Collectors.toList());
//		board = new BasicBoard(this.triangles, Side.black);
		// Pass self to game controller
	}
	
	public TriangleController() {
		// Pass self to game controller
	}
	
	public void reset() {
		for (TriangleInterface t : board.getTriangles()) {
			drawNCountersAtTriangleT(t.getN(), t.getCount());
		}
	}
	
	public void setEvent(SynchronousQueue<Event> eventQueue) {
		GameController controller = new GameController(this);
		controller.dispatchEventThread(eventQueue);
		board = new BasicBoard(this.triangles, Side.black);
	}
	
	public void setTriangles(List<Triangle> triangles) {
		this.triangles = triangles.stream().map(x -> (TriangleInterface) x).collect(Collectors.toList());
	//	board = new BasicBoard(this.triangles, Side.black);
		
	}
	
	public TriangleInterface getTriangle(int n) {
		return triangles.get(n - 1);
	}
	
	public void drawInitBoard() {
		drawNCountersAtTriangleT(1, 2, Side.black);
		drawNCountersAtTriangleT(6, 5, Side.white);
		
		drawNCountersAtTriangleT(8, 3, Side.white);
		drawNCountersAtTriangleT(12, 5, Side.black);
		
		drawNCountersAtTriangleT(13, 5, Side.white);
		drawNCountersAtTriangleT(17, 3, Side.black);
		
		drawNCountersAtTriangleT(19, 5, Side.black);
		drawNCountersAtTriangleT(24, 2, Side.white);
		
		
	}
	
	public void drawNCountersAtTriangleT(int t, int n) {
		TriangleInterface triangle = getTriangle(t);
		((Triangle) triangle).drawCircles(n);
	}
	
	public void drawNCountersAtTriangleT(int t, int n, Side side) {
		TriangleInterface triangle = getTriangle(t);
		triangle.setSide(side);
		((Triangle) triangle).drawCircles(n);
		
	}
	
	public void add(int t) {
		board.add(t);
		drawNCountersAtTriangleT(t, board.getTriangle(t).getCount());
	}
	
	public void remove(int  t) {
		board.remove(t);
		drawNCountersAtTriangleT(t, board.getTriangle(t).getCount());
	}
	
	public void unhighlightAll() {
		for (int t : highlighted) {
			unhighlightCounter(t);
		}
		
		highlighted.clear();
	}
	
	public void highlightCounter(int t) {
		((Triangle )getTriangle(t)).highlightNext(); 
		highlighted.add(t);
	}
	
	public void unhighlightCounter(int t) {
		((Triangle )getTriangle(t)).unhighlight();
	}
	
	public void highlightAllPossibleMoves(int t) {
		for (int i: getPossibleMoves(t, board.getDice())) {
			highlightCounter(i);
		}
	}

	@Override
	public List<Integer> getPossibleMoves(int triangle, DiceRollHolder roll) {
		return board.getPossibleMoves(triangle, roll);
	}

	@Override
	public boolean move(int from, int to) {
		boolean result = board.move(from, to);
		System.out.println("Moving " + from + ":"+ to);
		drawNCountersAtTriangleT(from, board.getTriangle(from).getCount());
		drawNCountersAtTriangleT(to, board.getTriangle(to).getCount());
		
		return result;
	}

	@Override
	public void capture(int triangle) {
		board.capture(triangle);
		drawNCountersAtTriangleT(triangle, board.getTriangle(triangle).getCount());
	}

	@Override
	public void bearOff(int from) {
		board.bearOff(from);
		drawNCountersAtTriangleT(from, board.getTriangle(from).getCount());
	}

	@Override
	public List<TriangleInterface> getTriangles() {
		return board.getTriangles();
	}
	
	@Override
	public void setDice(DiceRollHolder holder) {
		board.setDice(holder);
	}

	@Override
	public void switchSide() {
		board.switchSide();
	}
	
	


}
