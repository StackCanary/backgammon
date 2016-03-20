package backgammon.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
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
	private ConcurrentLinkedQueue<Event> event;
	
	public TriangleController(List<Triangle> triangles) {
		this.triangles = triangles.stream().map(x -> (TriangleInterface) x).collect(Collectors.toList());
		board = new BasicBoard(this.triangles, Side.black);
		// Pass self to game controller
	}
	
	public TriangleController() {
		// Pass self to game controller
	}
	
	public void setEvent(ConcurrentLinkedQueue<Event> eventQueue) {
		GameController controller = new GameController(this);
		controller.dispatchEventThread(eventQueue);
	}
	
	public void setTriangles(List<Triangle> triangles) {
		board = new BasicBoard(this.triangles, Side.black);
		this.triangles = triangles.stream().map(x -> (TriangleInterface) x).collect(Collectors.toList());
		
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
		getTriangle(t).add();
	}
	
	public void remove(int  t) {
		getTriangle(t).remove();
	}
	
	public void highlightCounter(int t) {
		((Triangle )getTriangle(t)).highlightNext(); 
	}
	
	public void unhighlightCounter(int t) {
		((Triangle )getTriangle(t)).unhighlight();
	}

	@Override
	public List<Integer> getPossibleMoves(int triangle, DiceRollHolder roll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean move(int from, int to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void capture(int to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bearOff(int from) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TriangleInterface> getTriangles() {
		// TODO Auto-generated method stub
		return null;
	}


}
