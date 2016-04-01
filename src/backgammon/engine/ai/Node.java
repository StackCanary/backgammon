package backgammon.engine.ai;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;
import backgammon.engine.board.BasicBoard;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.SequenceOfMoves;

/**
 * This class dynamically generates a tree as children are needed
 * @author bobby
 *
 */
public class Node {
	Node parent;
	public int score;
	BasicBoard board;
	public boolean head;
	List<Node> children = new ArrayList<Node>();
	
	
	public Node(BasicBoard board, Node parent) {
		this.parent = parent;
		this.board = board;
	}
	
	public Node(BasicBoard board, boolean head) {
		this.board = board;
		this.head = head;
	}
	
	public void add(Node node) {
		node.setParent(this);
		this.children.add(node);
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public List<Node> getChildren() {
		if (this.children.isEmpty()) {
			createChildren();
			return this.children;
		} else {
			return this.children;
		}
	}
	
	public void createChildren() {
		List<DiceRollHolder> allDiceRolls = getAllPossibleDiceRolls();
		
		for (DiceRollHolder holder : allDiceRolls) {
			for (int i = 0; i < 24; i++) {
				if (!head) {
					board.setDice(holder);
				}
				List<Integer> legalMoves = board.getPossibleMoves(1 + i, board.getDice());
				List<SequenceOfMoves> sequencesList = board.getDice().getSequencesOfMoves(i + 1, board.getTurn());
				for (SequenceOfMoves sequence : sequencesList) {
					if (sequence.isSequenceLegal(board)) {
						BasicBoard childBoard = sequence.play(board);
						add(new Node(childBoard, false));
						System.out.println("Sequence legal");
					} 
				}
			}
		}
	}
 	
	public int getScore(Side max) {
		return board.getScore(max);
	}
	
	public boolean isTerminal() {
		return board.hasEnded();
	}
	
	public Side turn() {
		return board.getTurn();
	}

	
		
		public List<DiceRollHolder> getAllPossibleDiceRolls() {
			List<DiceRollHolder> result = new ArrayList<DiceRollHolder>();
			for (int j = 1; j <= 3; j++) {
				for (int i = 1; i <= 6; i++) {
					result.add(new DiceRollHolder(i, j));
				}
			}
			return result;
		}
}
