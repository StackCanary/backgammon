package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Triangle extends JComponent{
	int i = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Triangle(int i) {
		this.i = i;
		drawElements(1);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (i % 2 == 0) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.ORANGE);
		}
		
		boolean flip = i > 12;
		
		int[] x_points = {0, getWidth() / 2, getWidth()};
		int[] y_points = {flip ? getHeight() : 0, flip ? 0 : getHeight() , flip? getHeight() : 0};
		g.fillPolygon(x_points, y_points, 3);

	}
	
	public void drawElements(int n) {
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		if (n > 5) {
			System.out.println("N is greater than 5 in Triangle");
			return;
		}
		
		for (int i = 0; i < n; i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			
			if (this.i >= 13 && this.i <= 24) {
				gbc.gridy = 5 - i;
			}
			
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.BOTH;
			add(new Circle(Color.green), gbc);
		}
		
		for (int i = n; i < 5; i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.fill = GridBagConstraints.BOTH;
			
			if (this.i >= 13 && this.i <= 24) {
				gbc.gridy = 5 - i;
			}
			gbc.weightx = 1;
			gbc.weighty = 1;
			add(new HighlightCircle(), gbc);
		}
		
	}
	
}
