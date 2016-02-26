package backgammon.client;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Client extends JFrame {
	
	public Client() {
		UI();
	}
	
	public void UI() {
		setTitle("Backgammon");
		setSize(640,460);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setPreferredSize(new Dimension(200, 25));
		menuBar.setVisible(true);
		
		JPanelWithBackground jPanelWithBackground = new JPanelWithBackground();
		getContentPane().add(new JPanelWithBackground());
		
		
		
//		JMenu file = new JMenu("File");
//		file.setVisible(true);
		
		JMenuItem exit = new JMenu("Exit");
		exit.setVisible(true);
		
		
//		setJMenuBar(menuBar);
//		menuBar.add(file);
//		file.add(exit);
	}
	
	public static void main(String args[]) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Client client = new Client();
				client.setVisible(true);
			}
			
		});
		
		
	}
}
