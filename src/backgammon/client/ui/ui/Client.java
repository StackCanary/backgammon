package backgammon.client.ui.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.sound.midi.ControllerEventListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import backgammon.client.control.TriangleController;
import backgammon.game.setup.Setup;

public class Client extends JFrame {
	public BoardView gameview;
	public JPanel menu;
	public Client() {
		UI();
	}
	
	/**
	 * Draw a frame 640x460, the outer most window
	 */
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
	}
	
	public static void menu() {
		
	}
	
	/**
	 * Add Jpanel to children of window
	 */
	public void boardview() {
		getContentPane().add(gameview);
	}
	
	/**
	 * Returns TriangleController which controls triangles
	 * @return
	 */
	public TriangleController getTriangleController() {
		return gameview.getTriangleController();
	}
	
	/**
	 * A run method which instantiates the client and launches it in another thread
	 * @return
	 */
	public static Client run() {
		final Client client = new Client();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				client.setVisible(true);
			}
			
		});
		
		return client;
	}
	
	/**
	 * Returns a reference to the Client after it has been instantiated, 
	 * deals with the threading issues by waiting for to instantiate and blocking
	 * until it has instantiated
	 * @return
	 */
	//http://stackoverflow.com/questions/9148899/
	public static Client createUIAndgetReference() {
	    Client x = null;
	    ExecutorService service = Executors.newSingleThreadExecutor();
	    Future<Client> result = service.submit(new Callable<Client>() {
	        public Client call() throws Exception {
	        	Client client = new Client();
	        	client.setVisible(true);
	            // the other thread
	            return client;
	        }
	    });
	    try {
	        x = result.get();
	    } catch (Exception e) {
	        // failed
	    }
	    service.shutdown();
		return x;
	}
	
	
}
