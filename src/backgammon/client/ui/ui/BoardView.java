package backgammon.client.ui.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JComponent;

import backgammon.client.ui.shape.JDummy;
import backgammon.client.ui.shape.Triangle;


//http://stackoverflow.com/questions/1466240/
public class BoardView extends JComponent {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Triangle> triangleController;
	
	public BoardView() {
		setBackground(Color.red);
		setLayout(new GridBagLayout());
		
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		triangleController = triangles;
		
		GridBagConstraints gbc;
		for (int i = 1; i <= 6 * 4; i++) {
			Triangle triangle = new Triangle(i);
			gbc = new GridBagConstraints();
			gbc.gridx = i - 1;
			gbc.gridy = 0;
			
			if(i > 12){
				gbc.gridy = 2;
				gbc.gridx -= 12;
			}
			
			/* for spacer */
			if(gbc.gridx > 5)
				gbc.gridx++;
			
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.BOTH;
			add(triangle, gbc);
			
			triangles.add(triangle);
		}
		
		JDummy spacer = new JDummy();
		Dimension size = new Dimension(40, 80);
		spacer.setMinimumSize(size);
		spacer.setPreferredSize(size);
		gbc = new GridBagConstraints();
		gbc.gridx = 6;
		gbc.gridy = 1;
		add(spacer, gbc);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
	
}
