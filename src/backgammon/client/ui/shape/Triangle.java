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
	private HeadCircle head;
	private Circle replaceableCircle;
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
	
	public boolean isHeadClicked() {
		return head.clicked;
	}
	
	public void drawCircles(int n) {
		empty();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		if (n > 5) {
			System.out.println("N is greater than 5 in Triangle");
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
		
		boolean flag = n > 5;
		boolean top = this.i >= 13 && this.i <= 24;
	
		
		for (int i = flag ? 1 : 0 ; i < (flag ? 5 : n) ; i++) {
			gbc.gridx = 1;
			gbc.gridy = 5 - i;
			
			if (top) {
				gbc.gridy = i;
			}
			
			gbc.weightx = 3;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.BOTH;
			
			if (i == (flag ? 5 : (n - 1 ))) {
				add (head = new HeadCircle(this.side, this.i), gbc);
			} else {
				add(replaceableCircle = new Circle(this.side), gbc);
			}
			
		}
		
		
		if(flag) {
			if (top) {
				gbc.gridx = 1;
				gbc.gridy = 0;
			} else {
				gbc.gridx = 1;
				gbc.gridy = 5;
			}
			
			gbc.weightx = 3;
			gbc.weighty = 1;
			add(new StackedCircle(this.side, n - 4), gbc);
		}
				
		for (int i = n; i < 5; i++) {
			gbc.gridx = 1;
			gbc.gridy = 5 - i;
			gbc.fill = GridBagConstraints.BOTH;
			
			if (top) {
				gbc.gridy = i;
			}
			gbc.weightx = 3;
			gbc.weighty = 1;
			add(new EmptyCircle(), gbc);
		}
		
	}
	
	private void highlight(int n) {
		GridBagConstraints gbc = new GridBagConstraints();
		boolean flag = n > 4;
		boolean bottom = this.i >= 1 && this.i <= 12;
		
		gbc.gridx = 1;
		gbc.gridy = n;
		
		if (flag) {
			drawCircles(n + 1);
			gbc.gridy = 4;
			
		} else{
			drawCircles(n);
		}
		
		if (n > 4) {
			System.out.println("N is greater than 4 in Triangle");
		}
		
		gbc.fill = GridBagConstraints.BOTH;
		
		if (bottom) {
			gbc.gridy = 5 - n;
		}
		
		gbc.weightx = 3;
		gbc.weighty = 1;
		
		if (flag) {
			GridBagLayout layout = (GridBagLayout)getLayout();
			GridBagConstraints headGbc = layout.getConstraints(replaceableCircle);
			remove(replaceableCircle);
			add(new EmptyCircle(true, this.i), headGbc);
		} else {
			add(new EmptyCircle(true, this.i), gbc);
		}
		
	}
	
	public void highlightNext() {
		highlight(this.counters);
		this.predictedHighlighted = true;
	}
	
	public void unhighlight() {
		drawCircles(this.counters);
		this.predictedHighlighted = false;
	}

	public Side getSide() {
		return side;
	}
	
	public void changeSide(Side side) {
		this.side = side;
		repaint();
	}
	
	public void changeSide() {
		this.side = (this.side == Side.black) ? Side.white : Side.black;
		repaint();
	}
	
	public void add() {
		drawCircles(this.counters + 1);
	}
	
	public void remove() {
		drawCircles(this.counters - 1);
	}
	
}
