package backgammon.client.ui.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

public class JPanelForScore extends JComponent{
	private static final long serialVersionUID = 1L;
	
	public JPanelForScore() {
		
	}
	
	private final int borderWidth = 5;
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.red);
		g.fillRect(borderWidth - 5, borderWidth, getWidth() - 2* borderWidth, getHeight() - 2*borderWidth);
		
	}
	
}
