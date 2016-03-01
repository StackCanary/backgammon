package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import backgammon.client.config.Config.Side;

public class HeadCircle extends Circle implements MouseListener {
	private static final long serialVersionUID = 1L;

	public boolean clicked = false; 
	
	public HeadCircle(Side side) {
		super(side);
		
		enableInputMethods(true);   
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		clicked = true;
		this.myColor = Color.red;
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
