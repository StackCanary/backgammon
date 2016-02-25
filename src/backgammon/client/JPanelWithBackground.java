package backgammon.client;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


//http://stackoverflow.com/questions/1466240/
public class JPanelWithBackground extends JPanel {
	
	private Image background;
	
	public JPanelWithBackground(String file) throws IOException {
		background = ImageIO.read(new File(file));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, this);
	}

}
