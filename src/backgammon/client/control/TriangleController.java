package backgammon.client.control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import backgammon.client.config.Config.Side;
import backgammon.client.ui.shape.Triangle;

public class TriangleController {
	
	private ArrayList<Triangle> triangles;
	
	public TriangleController(ArrayList<Triangle> triangles) {
		this.triangles = triangles;
		drawInitBoard();
		
		// Pass self to game controller
		GameController controller = new GameController(this);
	}
	
	public Triangle getTriangle(int n) {
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
		Triangle triangle = getTriangle(t);
		triangle.drawCircles(n);
	}
	
	public void drawNCountersAtTriangleT(int t, int n, Side side) {
		Triangle triangle = getTriangle(t);
		triangle.changeSide(side);
		triangle.drawCircles(n);
	}
	
	public void addCounter(int t) {
		getTriangle(t).add();
	}
	
	public void removeCounter(int  t) {
		getTriangle(t).remove();
	}
	
	public void highlightCounter(int t) {
		getTriangle(t).highlightNext(); 
	}
	
	public void unhighlightCounter(int t) {
		getTriangle(t).unhighlight();
	}

	

}
