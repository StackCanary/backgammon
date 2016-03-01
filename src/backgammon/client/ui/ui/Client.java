package backgammon.client.ui.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.sound.midi.ControllerEventListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import backgammon.client.ui.shape.DiceDraw;
import backgammon.client.ui.shape.TriangleController;

public class Client extends JFrame {
	public BoardView gameview;
	public JPanel menu;
	
	public Client() {
		UI();
	}

	
	public void UI() {
		setTitle("Backgammon");
		setSize(640,460);
		setMinimumSize(new Dimension(640, 460));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		BoardView board = new BoardView();
		
		gameview = board;
		getContentPane().add(board, BorderLayout.CENTER);
		pack();
		
		DiceDraw dice = new DiceDraw();
		
		add(dice);
		
		
	}
	
	public static void menu() {
		
	}
	
	public void boardview() {
		getContentPane().add(gameview);
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
