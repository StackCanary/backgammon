package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JButton;
import javax.swing.JComponent;

import backgammon.client.config.Config.Side;
import backgammon.client.control.Event;
import backgammon.client.control.TriangleController;
import backgammon.engine.board.TriangleInterface;

/**
 * This class draws a triangle and its Circles
 * @author bobby
 *
 */
public class Triangle extends JComponent implements TriangleInterface {
	private static final long serialVersionUID = 1L;

	private int n = 0;
	private int counters = 0;
	private boolean predictedHighlighted = false;
	private HeadCircle head;
	private Circle replaceableCircle;
	public Side side;
	private SynchronousQueue<Event> eventQueue;
	
	public Triangle(int i, SynchronousQueue<Event> event) {
		this.n = i;
		this.side = Side.white;
		this.eventQueue = event;
	}
	
	/**
	 * Takes the triangles position, its side (Black/White) and an event queue which is
	 *  passed to the Circles for clicking events
	 * @param i
	 * @param side
	 * @param event
	 */
	public Triangle(int i, Side side, SynchronousQueue<Event> event) {
		this.n = i;
		this.side = side;
		this.eventQueue = event;
	}
	
	/**
	 * Draws a black or orange triangle
	 * Draws them on both the top and the bottom (see the use of ternary expression)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (n % 2 == 0) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.ORANGE);
		}
		
		boolean flip = n < 13;
		
		int[] x_points = {0, getWidth() / 2, getWidth()};
		int[] y_points = {flip ? getHeight() : 0, flip ? 0 : getHeight() , flip? getHeight() : 0};
		g.fillPolygon(x_points, y_points, 3);

	}
	
	/**
	 * Clear triangle
	 */
	public void empty() {
		removeAll();
	}
	
	/**
	 * Return if the to most circle is clicked
	 * @return
	 */
	public boolean isHeadClicked() {
		return head.clicked;
	}
	
	/**
	 * Draw N circles, stack them if necessary
	 * @param n
	 */
	public void drawCircles(int n) {
		empty();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		if (n > 5) {
			
		}
		
		//The offending line :()
		if (!predictedHighlighted) {
			this.counters = n;
		}
		
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
		boolean top = this.n >= 13 && this.n <= 24;
	
		
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
				add (head = new HeadCircle(this.side, this.n, eventQueue), gbc);
			} else {
				add(replaceableCircle = new HeadCircle(this.side, this.n, eventQueue), gbc);
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
			add(new StackedCircle(this.side, n - 4, this.n, eventQueue), gbc);
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
		
		revalidate();
	}
	
	/**
	 * Highlight a particular amount
	 * @param n
	 */
	public void highlight(int n) {
		GridBagConstraints gbc = new GridBagConstraints();
		boolean flag = n > 4;
		boolean bottom = this.n >= 1 && this.n <= 12;
		
		gbc.gridx = 1;
		gbc.gridy = n;
		
		if (flag) {
			drawCircles(n + 1);
			gbc.gridy = 4;
			
		} else{
			drawCircles(n);
		}
		
		if (n > 4) {
			
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
			add(new EmptyCircle(true, this.n, eventQueue), headGbc);
		} else {
			add(new EmptyCircle(true, this.n, eventQueue), gbc);
		}
		
		revalidate();
	}
	
	/**
	 * Highlights the next circle in a set of circles
	 */
	public void highlightNext() {
		this.predictedHighlighted = true;
		highlight(this.counters);
	}
	
	/**
	 * Unhighlights a triangle
	 */
	public void unhighlight() {
		this.predictedHighlighted = false;
		drawCircles(this.counters);
	}

	public Side getSide() {
		return side;
	}
	
	public void setSide(Side side) {
		this.side = side;
		repaint();
	}
	
	/**
	 * Flips the color of the circles
	 */
	public void switchSide() {
		this.side = (this.side == Side.black) ? Side.white : Side.black;
		repaint();
	}
	
	public void add() {
		drawCircles(this.counters + 1);
	}
	
	public void remove() {
		drawCircles(this.counters - 1);
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
	public void setCount(int count) {
		this.counters = count;
	}


	@Override
	public int getCount() {
		return counters;
	}

}
