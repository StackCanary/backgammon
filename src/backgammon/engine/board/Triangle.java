package backgammon.engine.board;

import backgammon.client.config.Config.Side;

public class Triangle {
	int n;
	public int count;
	public Side side;
	
	public Triangle() {
		
	}
	
	public Triangle(int n) {
		this.n = n;
	}
	
	public Triangle(int count, Side side, int n) {
		this.n = n;
		this.count = count;
		this.side = side;
	}
	
	public void add() {
		this.count++;
	}
	
	public void remove() {
		this.count--;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setSide(Side side) {
		this.side = side;
	}
	
	public void switchSide() {
		this.side = this.side == Side.black ? Side.white : Side.black; 
	}
}
