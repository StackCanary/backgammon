package backgammon.engine.datastructure;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;
import backgammon.engine.ai.Scorable;
import backgammon.engine.board.BasicBoard;

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
		if (this.children != null) {
			return this.children;
		} else {
			createChildren();
			return this.children;
		}
	}
	
	public void createChildren() {
		for (Scorable s : scorable.getChildren(scorable)) {
			add(new Node(scorable, this));
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
