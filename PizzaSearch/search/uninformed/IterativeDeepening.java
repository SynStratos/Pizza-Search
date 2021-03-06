package uninformed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import framework.Board;
import framework.Node;

public class IterativeDeepening {
	private Node root;
	private Node goal;
	private Board board;
	private int maxlimit = 5;
	private int step = -1;

	private boolean reached = true;
	
	Stack<Node> queue = new Stack<Node>();
	List<Node> explored = new ArrayList<Node>();
	
	public IterativeDeepening(Node root, Node goal, Board board) {
		this.root=root;
		this.goal=goal;
		this.board=board;
	}
	
	public Node run()
	{	Node current = new Node(null);
		for(int limit=0; limit<=maxlimit; limit++ ) {
			queue.empty();
			explored = new ArrayList<Node>();
			queue.push(root);
			current = new Node(null);
			
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
				if(current.getDepth() < limit) {
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

