package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class Circle extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Circle(Color color) {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.green);
			
		boolean result = w > h;
		g.fillOval(0, 0, result ? h : w , result ? h : w);
		
	}

}
