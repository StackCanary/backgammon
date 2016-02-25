package backgammon.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Client extends JFrame {
	
	public Client() {
		UI();
	}
	
	public void UI() {
		setTitle("Backgammon");
		setSize(640,460);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//getContentPane().add(pane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setPreferredSize(new Dimension(200, 25));
		menuBar.setVisible(true);
		
		try {
			JPanelWithBackground jPanel = new JPanelWithBackground("background.png");
			getContentPane().add(new JPanelWithBackground("background.png"));
		} catch (IOException e) {
			menuBar.setBackground(Color.lightGray);
		}
		
		
		JMenu file = new JMenu("File");
		file.setVisible(true);
		
		JMenuItem exit = new JMenu("Exit");
		exit.setVisible(true);
		
		
		setJMenuBar(menuBar);
		menuBar.add(file);
		file.add(exit);
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
