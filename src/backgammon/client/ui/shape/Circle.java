package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import backgammon.client.config.Config;

public class Circle extends JComponent implements MouseListener {
	private Color myColor;
	
	private boolean top = false;
	
	private boolean pressed = false;
	private boolean released = false;
	private boolean clicked = false; 

	private static final long serialVersionUID = 1L;
	
	public Circle(Config.Side side) {
			
		this.myColor = Config.Side.enumToColor(side);
		
		enableInputMethods(true);   
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		
		if (pressed) { 
			
		} else {
			
		}
		
		g.setColor(myColor);
			
		
		
		boolean result = w > h;
		g.fillOval(0, 0, result ? h : w , result ? h : w);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		pressed = clicked;
		JOptionPane.showMessageDialog(this, "hi");
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		pressed = clicked;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		pressed = clicked;
		repaint();
	}

}
