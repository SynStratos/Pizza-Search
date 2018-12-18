package uninformed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import framework.Board;
import framework.Node;

public class DepthFirst {
	private Node root;
	private Node goal;
	private Board board;
	
	private boolean reached = true;
	
	private int step = -1;
	
	Stack<Node> queue = new Stack<Node>();
	List<Node> explored = new ArrayList<Node>();
	
	public DepthFirst(Node root, Node goal, Board board) {
		this.root=root;
		this.goal=goal;
		this.board=board;
	}
	
	public Node run()
	{
		queue.push(root);
		Node current = new Node(null);
		
		while(!queue.isEmpty())
		{
			current = queue.pop();
			explored.add(current);
			step++;
			
			reached = true;
			for( int i=0; i<goal.getState().length; i++) {
				if(!current.getState()[i].equals(goal.getState()[i])) reached = false;
			}
			if( reached ) {
				System.out.println("Steps: " + step);
				return current;
			}
			
			board.parent = current;
			
			ArrayList<Node> bb = board.buildChildren();
			Iterator<Node> it = bb.iterator();
			while(it.hasNext()) {
				Node t = it.next();
				if(!checkExplored(t)) {
					queue.add(t);
				}
			}
			
		}
		System.out.println("No result found in "+step+" steps.");
		System.exit(0);
		return null;
	}
	
	private boolean checkExplored(Node node) {
		
		for(Node iterated : explored) {
			for( int i=0; i<goal.getState().length; i++) {
				if(node.getState()[i].equals(iterated.getState()[i])) return true;
			}
		}
		return false;
	}
}