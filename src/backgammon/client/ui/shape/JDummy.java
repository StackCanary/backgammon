package backgammon.client.ui.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class JDummy extends JComponent {
	
	public JDummy() {
		Dimension size = new Dimension(40, 80);
		setMinimumSize(size);
		setPreferredSize(size);
	}

}
