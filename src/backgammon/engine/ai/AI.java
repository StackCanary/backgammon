package backgammon.engine.ai;

import backgammon.client.config.Config.Side;
import backgammon.engine.datastructure.Node;

public class AI {
	public AI() {
		
	}
	
	public int minimax(Node node, int depth, Side min, Side max) {
		if (node.isTerminal() || depth == 0) {
			return node.getScore();
		}
		
		if (node.turn() == min) {
			int a = 10000;
			for (Node child : node.getChildren()) {
				a = min(a, minimax(child, depth-1, min, max));
			}
		}
		
		if (node.turn() == max ) {
			int a = -10000;
			for (Node child : node.getChildren()) {
				a = min(a, minimax(child, depth-1, min, max));
			}
		}
		
		return 0;
	}
	
	public int min(int a, int i) {
		return (a < i) ? a : i;
	}
	
	public int max(int a, int i) {
		return (a > i) ? a : i;
	}
	
	public int chance(int a, int i) {
		return 0;
	}
	
}
