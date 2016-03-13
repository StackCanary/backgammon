package backgammon.client.socket;

import java.awt.Color;


public class NetworkConstants {
	
		public enum ClientProtocol {
			hello ("hello"),
			bye ("bye"),
			newgame ("newgame"),
			diceMessage("dice1-dice2:");
			
			private final String message;
			
			ClientProtocol(String message) {
				this.message = message;
			}
			
			public String getMessage() {
				return this.message;
			}
			
		}
		
		public enum ServerProtocol {
			hello ("hello"),
			bye ("bye"),
			ready ("ready"),
			reject ("reject");
			
			
			private final String message;
			
			ServerProtocol(String message) {
				this.message = message;
			}
			
		}
		
}
