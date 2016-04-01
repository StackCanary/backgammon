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

/**
 * Paints a single circle
 * @author bobby
 *
 */
public class Circle extends JComponent {
	protected Color myColor;
	protected Color save;
	
	private static final long serialVersionUID = 1L;
	
	public Circle(Config.Side side) {
			
		this.myColor = this.save = Config.Side.enumToColor(side);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(myColor);
		drawCircle(g);
	}
	
	public void drawCircle(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		boolean result = w > h;
		g.fillOval(0, 0, result ? h : w , result ? h : w);
	}
	

}
