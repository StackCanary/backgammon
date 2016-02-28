package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class HighlightCircle extends JComponent {
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.green);
			
		boolean result = w > h;
		g.drawOval(0, 0, result ? h : w , result ? h : w);
	}
	

}
