package eightpuzzle;

import framework.Heuristic;
import framework.Node;

public class PuzzleHeuristic extends Heuristic {

	private double difference = 0.0;
	private Node node;
	private Node goal;
	
	public PuzzleHeuristic(Node node, Node goal) {
		this.node = node;
		this.goal = goal;
	}
		
	public void setNode(Node node) {
		this.node = node;
	}
	
	@Override
	public double hCost() {
		difference = 0.0;
		String cur = node.getState()[0].toString();
		String gstring = goal.getState()[0].toString();
		
		for(int i=0; i<cur.length(); i++) {
			if(cur.charAt(i) != gstring.charAt(i)) difference=difference+1.0;
		}
		
		return difference;
	}	
}
