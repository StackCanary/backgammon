package backgammon.client.ui.shape;

import java.awt.Color;
import java.util.Observable;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import backgammon.client.config.Config.Side;
import backgammon.client.control.TriangleController;

public class HeadCircle extends Circle implements MouseListener {
	private static final long serialVersionUID = 1L;
	public int n;
	public boolean clicked = false; 
	private TriangleController triangleController;
	
	public HeadCircle(Side side, int n, TriangleController triangleController) {
		super(side);
		
		this.n = n;
		enableInputMethods(true);   
		addMouseListener(this);
		
		this.triangleController = triangleController;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		clicked = true;
		this.myColor = Color.red;
		triangleController.drawNCountersAtTriangleT(n, 20);
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
