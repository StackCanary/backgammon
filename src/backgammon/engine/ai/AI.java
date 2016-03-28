package backgammon.engine.ai;

import backgammon.client.config.Config.Side;
import backgammon.engine.board.BasicBoard;
import backgammon.engine.datastructure.Node;

public class AI {
	
	public AI() {
		
	}
	
	public int minimax(Node node, int depth, Side min, Side max) {
		if (node.isTerminal() || depth == 0) {
			return node.getScore(max);
		}
		
		int a = 0;
		if (node.turn() == min) {
			a = 10000;
			for (Node child : node.getChildren()) {
				a = min(a, minimax(child, depth-1, min, max));
				System.out.println("min:" + a);
			}
			
		}
		
		if (node.turn() == max ) {
			a = -10000;
			for (Node child : node.getChildren()) {
				a = max(a, minimax(child, depth-1, min, max));
			//	System.out.println("max:" + a);
			}
			
		}
		
		
		return a;
	}
	
	public void printDepth(int depth) {
		for(int i = 0; i < depth; i++) {
			System.out.print("-");
		}
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
	
	
	public static void main(String[] args) {
		AI ai = new AI();
		BasicBoard board = new BasicBoard(Side.black);
		board.setBoard();
		Node node = new Node(board, true);
		System.out.println("Minmax " + ai.minimax(node, 3, Side.black, Side.white));
		
	}
}
