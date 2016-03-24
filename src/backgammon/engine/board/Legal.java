package backgammon.engine.board;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;

public class Legal {
	
	private BasicBoard board;
	List<TriangleInterface> triangles = new ArrayList<TriangleInterface>();
	
	public Legal(BasicBoard board) {
		this.board = board;
		triangles = board.getTriangles();
		
	}
	
	public boolean isCapture(int from, int to) {
		if ((from >= 1 && from <= 24) && (to >= 1 && to <= 24)) {
			if (board.getTriangle(to).getCount() < 1) {
				return false;
			}
			
			boolean result = (board.getTriangle(from).getSide() != board.getTriangle(to).getSide());
			return !result;
		}
		
		return false;
	}
	
	public boolean isLegalBearingOff(int from, int to) {
		TriangleInterface fromTriangle = board.getTriangle(from);
		
		boolean isBlack = (fromTriangle.getSide() == Side.black);
		if (isBlack) {
				if (from >= 19 && from <= 24 && to == 25) {
					return true;
				}
		} else {
				if (from >= 1 && from <= 6 && to == 0) {
					return true;
				}
		}
		
		return false;
		
	}
	
	public boolean canMove(int from, int to) {
		boolean left = (board.turn == Side.black);
		
		if (left) {
			if (from > to) {
				return false;
			}
		} else {
			if (from < to) {
				return false;
			}
		}
		
		try {
			TriangleInterface fromTriangle = board.getTriangle(from);
			TriangleInterface toTriangle = board.getTriangle(to);
			
			if (isCapture(from, to)) {
				if (toTriangle.getCount() > 1) {
					return false;
				} 
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
		
		return true;
	}

}
