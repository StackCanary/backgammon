package backgammon.engine.datastructure;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;

public class Node {
	Node parent;
	public Side side;
	public int score;
	public boolean head;
	List<Node> children = new ArrayList<Node>();
	
	public Node() {
		head = true;
	}
	
	public Node(Node parent, int score, int side) {
		this.parent = parent;
		this.score = score;
	}
	
	public Node(Node parent) {
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
		return this.children;
	}
	
	public static void main(String[] args) {
	    Node head = new Node();
	    
	    
	    
	}
	
}
