package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class EmptyCircle extends JComponent implements MouseListener {
	private boolean highlight = false;
	private boolean top = false;
	private boolean pressed = false;
	private boolean released = false;
	private boolean clicked = false; 
	
	public EmptyCircle() {

		enableInputMethods(true);   
		addMouseListener(this);
	}
	
	public EmptyCircle(boolean higlight) {
		this();
		this.highlight = higlight;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		// Set alpha to 0, this makes the circle invisible -> opaque (invisible in our case)
		
		
		boolean result = w > h;
		
		
		if (this.highlight) {
			g.setColor(Color.green);
			g.drawOval(0, 0, result ? h : w , result ? h : w);
		} else {
			g.setColor(new Color(255, 255, 255, 0));
			g.fillOval(0, 0, result ? h : w , result ? h : w);
		}
	}
	
	public void setHighlight() {
		this.highlight = true;
		repaint();
	}
	
	public void deHighlight() {
		this.highlight = false;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!highlight) {
			return;
		}
		
		JOptionPane.showMessageDialog(this, "hi");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
