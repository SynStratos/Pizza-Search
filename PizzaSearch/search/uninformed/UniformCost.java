package uninformed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import framework.Board;
import framework.Node;
import utils.NodeComparator;

public class UniformCost {

	private Node root;
	private Node goal;
	private Board board;
	private int step = -1;
	
	private boolean reached = true;
	
	Queue<Node> queue = new PriorityQueue<Node>(10, new NodeComparator());
	List<Node> explored = new ArrayList<Node>();
	
	public UniformCost(Node root, Node goal, Board board) {
		this.root=root;
		this.goal=goal;
		this.board=board;
	}
	
	public Node run()
	{
		queue.add(root);
		Node current = new Node(null);
		
		while(!queue.isEmpty())
		{
			current = queue.poll();
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
