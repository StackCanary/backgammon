package backgammon.engine.board;

import org.w3c.dom.views.AbstractView;

import backgammon.client.config.Config.Side;

public class Triangle implements TriangleInterface {
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
	
	@Override
	public void setN(int n) {
		this.n = n;
	}

	@Override
	public int getN() {
		return n;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.count;
	}

	@Override
	public Side getSide() {
		// TODO Auto-generated method stub
		return this.side;
	}
	
	@Override
	public void add() {
		this.count++;
	}
	
	@Override
	public void remove() {
		this.count--;
	}
	
	@Override
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public void setSide(Side side) {
		this.side = side;
	}
	
	@Override
	public void switchSide() {
		this.side = this.side == Side.black ? Side.white : Side.black; 
	}


}
