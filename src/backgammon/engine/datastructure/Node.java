package backgammon.engine.datastructure;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;
import backgammon.engine.ai.Scorable;
import backgammon.engine.board.BasicBoard;
import backgammon.engine.board.DiceRollHolder;

public class Node {
	Node parent;
	public Side side;
	public int score;
	public Scorable scorable;
	public boolean head;
	List<Node> children = new ArrayList<Node>();
	
	
	public Node(Scorable scorable, Node parent) {
		this.scorable = scorable;
		this.parent = parent;
	}
	
	public Node(Scorable scorable, boolean head) {
		this.scorable = scorable;
		this.head = head;
	}
	
	public Node() {
		this.head = true;
		scorable = new BasicBoard();
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
		List<Scorable> scorables;
		if (head) {
			scorables = scorable.getChildren(new DiceRollHolder(6, 6));
		} else {
			if (scorable instanceof BasicBoard) {
				scorables = scorable.getChildren(getParent().scorable);
			} else {
				scorables = scorable.getChildren(null);
			}
		}
		
		for (Scorable s : scorables) {
			add(new Node(s, this));
		}
	}
 	
	public int getScore() {
		return scorable.getScore();
	}
	
	public boolean isTerminal() {
		if (scorable instanceof BasicBoard) {
			return ((BasicBoard) scorable).hasEnded();
		} else {
			return false;
		}
	}
	
	public Side turn() {
		if (scorable instanceof BasicBoard) {
			return ((BasicBoard) scorable).getTurn();
		} else {
			return Side.chance;
		}
	}
	
}
