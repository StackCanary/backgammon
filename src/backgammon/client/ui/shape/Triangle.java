package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;

import backgammon.client.config.Config.Side;

public class Triangle extends JComponent{
	private static final long serialVersionUID = 1L;

	private int i = 0;
	private int counters = 0;
	private boolean predictedHighlighted = false;
	private boolean headHighlighted = false;
	public Side side;
	
	public Triangle(int i) {
		this.i = i;
		this.side = Side.white;
	}
	
	
	public Triangle(int i, Side side) {
		this.i = i;
		this.side = side;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (i % 2 == 0) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.ORANGE);
		}
		
		boolean flip = i < 13;
		
		int[] x_points = {0, getWidth() / 2, getWidth()};
		int[] y_points = {flip ? getHeight() : 0, flip ? 0 : getHeight() , flip? getHeight() : 0};
		g.fillPolygon(x_points, y_points, 3);

	}
	
	public void empty() {
		removeAll();
	}
	
	public void drawCircles(int n) {
		empty();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		if (n > 5) {
			System.out.println("N is greater than 5 in Triangle");
			return;
		}
		
		this.counters = n;
		
		JDummy spacer = new JDummy();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		add(spacer, gbc);
		
		spacer = new JDummy();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		add(spacer, gbc);
		
		
		for (int i = 0; i < n; i++) {
			gbc.gridx = 1;
			gbc.gridy = 5 - i;
			
			if (this.i >= 13 && this.i <= 24) {
				gbc.gridy = i;
			}
			
			gbc.weightx = 3;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.BOTH;
			
			if (i == (n - 1)) {
				add (new HeadCircle(this.side), gbc);
			} else {
				add(new Circle(this.side), gbc);
			}
		}
		
		for (int i = n; i < 5; i++) {
			gbc.gridx = 1;
			gbc.gridy = 5 - i;
			gbc.fill = GridBagConstraints.BOTH;
			
			if (this.i >= 13 && this.i <= 24) {
				gbc.gridy = i;
			}
			gbc.weightx = 3;
			gbc.weighty = 1;
			add(new EmptyCircle(), gbc);
		}
		
	}
	
	private void highlight(int n) {
		drawCircles(n);
		GridBagConstraints gbc = new GridBagConstraints();
		
		if (n > 4) {
			System.out.println("N is greater than 4 in Triangle");
			return;
		}
		
		gbc.gridx = 1;
		gbc.gridy = n;
		gbc.fill = GridBagConstraints.BOTH;
		
		if (this.i >= 13 && this.i <= 24) {
			gbc.gridy = 5 - n;
		}
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		add(new EmptyCircle(true), gbc);
	}
	
	public void highlightNext() {
		highlight(this.counters);
		this.predictedHighlighted = true;
	}
	
	public void unhighlight() {
		drawCircles(this.counters);
		this.predictedHighlighted = false;
	}
	
	public void changeSide(Side side) {
		this.side = side;
		repaint();
	}
	
	public void add() {
		drawCircles(this.counters + 1);
	}
	
	public void remove() {
		drawCircles(this.counters - 1);
	}
	
}
