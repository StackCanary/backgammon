package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JTextField;

import backgammon.client.config.Config.Side;
import backgammon.client.control.Event;

public class StackedCircle extends HeadCircle {
	private static final long serialVersionUID = 1L;
	private int n;

	public StackedCircle(Side side, int n, int triangle, SynchronousQueue<Event> queue) {
		super(side, triangle, queue);
		this.n = n;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawString("" + n, getHeight() / 2, getWidth() / 2);
	}
	
}
