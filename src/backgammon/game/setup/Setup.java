package backgammon.game.setup;

import backgammon.client.ui.ui.Client;
import backgammon.engine.player.GUIPlayer;

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
	
	public void GUIPlayervsNetworkPlayer() {
		Thread t = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						
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
			System.out.println("Usage: ");
			System.out.println("[program] PvP");
			System.out.println("[program] PvA");
			System.out.println("[program] PCvNS");
			
			return;
		}
		
		
		Setup setup = new Setup();
		switch(args[0]) {
		case "PvP":
			setup.GUIPlayervsGUIPlayer();
			break;
		
		case "PvN":
			setup.GUIPlayervsNetworkPlayer();
			break;
			
		default:
			System.out.println("Incorrect arguments");
		}
		
	}
}
