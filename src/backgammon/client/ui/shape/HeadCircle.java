package backgammon.client.ui.shape;

import java.awt.Color;
import java.util.concurrent.SynchronousQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import backgammon.client.config.Config.Side;
import backgammon.client.control.Event;

public class HeadCircle extends Circle implements MouseListener {
	private static final long serialVersionUID = 1L;
	public int n;
	public boolean clicked = false; 
	private SynchronousQueue<Event> eventQueue;
	
	public HeadCircle(Side side, int n, SynchronousQueue<Event> event) {
		super(side);
		
		this.n = n;
		enableInputMethods(true);   
		addMouseListener(this);
		this.eventQueue = event;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		clicked = true;
		try {
			this.eventQueue.put(new Event(null, this));
		} catch (InterruptedException e) {
			System.out.println("Something interrupted our queue, :(");
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.myColor = Color.red;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	//	if (!clicked) {
			this.myColor = this.save;
			repaint();
	//	}
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
