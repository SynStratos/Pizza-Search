package mazerun;

import framework.Heuristic;
import framework.Node;

public class MazeHeuristic extends Heuristic{
	//private double difference = 0.0;
	private Node node;
	private Node goal;
	
	public MazeHeuristic(Node node, Node goal) {
		this.node = node;
		this.goal = goal;
	}
		
	public void setNode(Node node) {
		this.node = node;
	}
	
	
	public double hCost() {
		Integer[] cur = (Integer[]) node.getState();
		Integer[] goalstate = (Integer[]) goal.getState();
				
		int diff_x = Math.abs(cur[0] - goalstate[0]);
		int diff_y = Math.abs(cur[1] - goalstate[1]);
			
		return diff_x + diff_y;
	}	
}