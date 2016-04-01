package backgammon.game.setup;

import backgammon.client.socket.Network.NetworkRole;
import backgammon.client.ui.ui.Client;
import backgammon.engine.board.UniversalBoard;
import backgammon.engine.player.GUIPlayer;
import backgammon.engine.player.NetworkPlayer;

public class Setup {
	public void GUIPlayervsGUIPlayer() {
		Thread t = new Thread(
				new Runnable() {
					
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
	
	public void GUIPlayervsNetworkPlayer(NetworkRole role, String host, int port) {
		Thread t = new Thread(
				new Runnable() {
					@Override
					public void run() {
						NetworkPlayer networkPlayer;
						GUIPlayer guiPlayer = new GUIPlayer();
						
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
				setup.GUIPlayervsNetworkPlayer(NetworkRole.server, "0", Integer.parseInt(args[1]));
			}
			break;
		
		case "PvNc":
			if (args.length > 3) {
				printUsage();
			} else {
				setup.GUIPlayervsNetworkPlayer(NetworkRole.client, args[1], Integer.parseInt(args[2]));
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
