package backgammon.client.ui.shape;

import java.awt.Color;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import backgammon.client.config.Config.Side;
import backgammon.client.control.Event;
import backgammon.client.control.TriangleController;
import backgammon.client.control.Event.EventT;

public class HeadCircle extends Circle implements MouseListener {
	private static final long serialVersionUID = 1L;
	public int n;
	public boolean clicked = false; 
	private ConcurrentLinkedQueue<Event> eventQueue;
	
	public HeadCircle(Side side, int n, ConcurrentLinkedQueue<Event> event) {
		super(side);
		
		this.n = n;
		enableInputMethods(true);   
		addMouseListener(this);
		this.eventQueue = event;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		clicked = true;
		this.eventQueue.add(new Event(EventT.add, null, this));
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.myColor = Color.red;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (!clicked) {
			this.myColor = this.save;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		repaint();
	}

}
