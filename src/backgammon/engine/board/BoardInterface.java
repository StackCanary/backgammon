package backgammon.engine.board;

import java.util.List;

import backgammon.client.config.Config.Side;

public interface BoardInterface {
	
	public void drawNCountersAtTriangleT(int n, int i, Side side);
	public List<Integer> getPossibleMoves(int triangle, DiceRollHolder roll);
	public void setDice(DiceRollHolder holder);
	public boolean move(int from, int to);
	public void switchSide();
	public void capture(int to);
	public void bearOff(int from);
	public void add(int triangle);
	public void remove(int triangle);
	public List<TriangleInterface> getTriangles();
	public TriangleInterface getTriangle(int n);
	
}
