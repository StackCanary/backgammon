package backgammon.network;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;


public class NetworkConstants {
	
		public enum ClientProtocol {
			hello ("hello"),
			bye ("bye"),
			newgame ("newgame");
			
			private final String message;
			
			ClientProtocol(String message) {
				this.message = message;
			}
			
			public String getMessage() {
				return this.message;
			}
			
			public static String diceMessage(DiceRollHolder diceHolder, SequenceOfMoves moves) {
				return diceHolder.getMessage() + ":" +  moves.getMessage();
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
			
			public String getMessage() {
				return this.message;
			}
			
			public static SequenceOfMoves parseDiceMessageIntoSequence(String message) throws IndexOutOfBoundsException {
				List<Pair> listOfPairs = new ArrayList<Pair>();
				
				String split = message.split(":")[1];
				for (String possiblePair : split.split(",")) {
					int pos = Integer.parseInt(possiblePair.split("\\|")[0].replaceAll("[^0-9]", ""));
					int end = Integer.parseInt(possiblePair.split("\\|")[1].replaceAll("[^0-9]", ""));
					
					Pair pair = new Pair(pos, end);
					listOfPairs.add(pair);
				}
			
				
				return new SequenceOfMoves(listOfPairs);
			}
			
			public static DiceRollHolder parseDiceMessageIntoDiceRoll(String message) {
				String result = message.split("\\s*:\\s*")[0];
				
				int x = Integer.parseInt(result.split("\\s*-\\s*")[0]);
				int y = Integer.parseInt(result.split("\\s*-\\s*")[1]);
				
				return new DiceRollHolder(x, y);
			}
			
		}
			

		public static void main(String[] args) {
			NetworkConstants.ServerProtocol.parseDiceMessageIntoSequence("3-3:(1|4),(1|4),(5|8),(10|13);");
		}
}
