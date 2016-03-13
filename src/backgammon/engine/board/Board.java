package backgammon.engine.board;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;

public class Board {
	List<Triangle> triangles = new ArrayList<Triangle>();
	List<Triangle> save;
	
	public Board() {
		setBoard();
	}
	
	public Board(List<Triangle> triangles) {
		this.triangles = triangles;
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

	public Triangle getTriangle(int n) {
		return triangles.get(n - 1);
	}
 	
	public static int tooIndex(int i) {
		return i + 1;
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		for (Triangle triangle : board.triangles) {
			System.out.println(triangle.count);
		}
	}
}
