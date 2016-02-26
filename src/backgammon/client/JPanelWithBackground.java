package backgammon.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;


//http://stackoverflow.com/questions/1466240/
public class JPanelWithBackground extends JComponent {
	private static final long serialVersionUID = 1L;

	public JPanelWithBackground() {
		setBackground(Color.black);
	}
	
	private final int borderWidth = 30;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.red);
		
		g.fillRect(borderWidth, borderWidth, getWidth() - 2* borderWidth, getHeight() - 2*borderWidth);
		
		g.setColor(Color.BLACK);
		for (int i = 1; i <= 12 ; i++) {
			int[] x_points = {getTriangleBaseX(i) - getBaseSize(), getTriangleBaseX(i), getTriangleBaseX(i) + getBaseSize()};
			int[] y_points = {getBaseY(i), getBaseY(i) + getHeight(i) , getBaseY(i)};
			
			g.fillPolygon(x_points, y_points, 3);
		}
		
		
		g.setColor(Color.ORANGE);
		for (int i = 13; i <= 24 ; i++) {
			int[] x_points = {getTriangleBaseX(i) - getBaseSize(), getTriangleBaseX(i), getTriangleBaseX(i) + getBaseSize()};
			int[] y_points = {getBaseY(i), getBaseY(i) - getHeight(i) , getBaseY(i)};
			
			g.fillPolygon(x_points, y_points, 3);
		}
		
//		
	}
	
	
	public int getTriangleBaseX(int i) {
		Dimension dimension = getSize();
		if (i >= 1 && i <= 12) {
			int number = i + 1;
			int total = 12 + 3;
			
			int divided_width = dimension.width / total;
			
			int base = number * divided_width;
			return base;
			
		}
		
		if (i >= 12 && i <= 24) {
			return getTriangleBaseX(i - 12);
		}
		
		return 0;
	}
	
	
	public int getBaseY(int i) {
		Dimension dimension = getSize();
		
		if (i >= 1 && i <= 12) {
			return 40;
		}
		
		if (i >= 12 && i <= 24) {
			return dimension.height - 40;
		}
		
		return 0;
	}
	
	public int getHeight(int i) {
		Dimension dimension = getSize();
		
		if (i >= 1 && i <= 12) {
			return (int) (dimension.height * 0.4);
		}
		
		if (i >= 12 && i <= 24) {
			return (int) (dimension.height * 0.4);
		}
		
		
		return 0;
	}

	
	public int getBaseSize() {
		Dimension dimension = getSize();
		System.out.println(dimension.width / 20 - 20);
		return dimension.width / 30;
	}
}
