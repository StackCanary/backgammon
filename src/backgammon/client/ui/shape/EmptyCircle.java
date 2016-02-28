package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class EmptyCircle extends JComponent {

	public EmptyCircle() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();

		// Set alpha to 0, this makes the circle invisible -> opaque (invisible in our case)
		g.setColor(new Color(255, 255, 255, 0));
		
		boolean result = w > h;
		g.fillOval(0, 0, result ? h : w , result ? h : w);
	//	g.fillOval(0, 0, (int) (w * 0.9), (int) (h * 0.9));
	}

}
