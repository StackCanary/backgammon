package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextField;

import backgammon.client.config.Config.Side;

public class StackedCircle extends Circle {
	private static final long serialVersionUID = 1L;
	private int n;

	public StackedCircle(Side side, int n) {
		super(side);
		this.n = n;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawString("" + n, getHeight() / 2, getWidth() / 2);
	}
	
}
