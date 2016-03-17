package backgammon.engine.board;

import backgammon.client.config.Config.Side;

public interface TriangleInterface {
	public void setN(int n);
	public int getN();
	
	public void setCount(int count);
	public int getCount();
	
	public void setSide(Side side);
	public Side getSide();
	
	public void add();
	
	public void remove();
	
	public void switchSide();
	
}
