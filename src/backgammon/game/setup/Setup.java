package backgammon.game.setup;

import backgammon.client.ui.ui.Client;
import backgammon.engine.board.UniversalBoard;
import backgammon.engine.player.TextPlayer;
import backgammon.network.Network.NetworkRole;
import backgammon.engine.player.NetworkPlayer;

/**
 * This package contains the main method. It is a factory for setting up a game with its needed parameters. 
 * An example of this would be passing classes that implement the Player class.
 * 
 * 
 * This class also takes in command line arguments
 * @author bobby
 *
 */
public class Setup {
	
	/**
	 * Start GUIPlayer vs GUIPlayer in which you can play on the same keyboard
	 */
	public void GUIPlayervsGUIPlayer() {
		Thread t = new Thread(
				new Runnable() {
					
					/**
					 * Launch thread using Swing's invokeLater to launch Client
					 */
					@Override
					public void run() {
						javax.swing.SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								Client client = new Client();
								client.setVisible(true);
							}
							
						});
					}
				}
				);
		
		t.start();
	}
	
	/**
	 * Start TextClient vs NetworkPlayer in a networking based match
	 * 
	 * @param role
	 * @param host
	 * @param port
	 */
	public void TextPlayervsNetworkPlayer(NetworkRole role, String host, int port) {
		
		/**
		 * Launch thread that instantiates the network player, the text player and 
		 * then passes them to the universal board
		 */
		Thread t = new Thread(
				new Runnable() {
					@Override
					public void run() {
						NetworkPlayer networkPlayer;
						TextPlayer guiPlayer = new TextPlayer();
						
						if (role == NetworkRole.client) {
							networkPlayer = new NetworkPlayer(host, port);
						} else {
							networkPlayer = new NetworkPlayer(port);
						}
						
						UniversalBoard uBoard = new UniversalBoard(guiPlayer, networkPlayer);
						
					}
					
				}
				);
		
		t.start();
	}
	
	public void AIPlayervsNetworkPlayer() {
		
	}
	
	public void AIPlayervsAIPlayer() {
		
	}
	
	public void NetworkPlayervsNetworkPlayer() {
		
	} 
	
	/**
	 * Deals with command line arguments 
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length < 1) {
			printUsage();
			return;
		}
		
		
		Setup setup = new Setup();
		switch(args[0]) {
		case "PvP":
			setup.GUIPlayervsGUIPlayer();
			break;
		
			//Start as Server or client
		case "PvNs":
			if (args.length > 2) {
				printUsage();
			} else {
				setup.TextPlayervsNetworkPlayer(NetworkRole.server, "0", Integer.parseInt(args[1]));
			}
			break;
		
		case "PvNc":
			if (args.length > 3) {
				printUsage();
			} else {
				setup.TextPlayervsNetworkPlayer(NetworkRole.client, args[1], Integer.parseInt(args[2]));
			}
			break;
			
		default:
			System.out.println("Incorrect arguments");
		}
		
	}
	
	public static void printUsage() {
		System.out.println("Usage: ");
		System.out.println("[program] PvP");
		System.out.println("[program] PvA");
		System.out.println("[program] PvPs [port]");
		System.out.println("[program] PvPc [host] [port]");
	}
}
