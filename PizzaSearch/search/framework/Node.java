package framework;

import java.util.ArrayList; 
import java.util.List; 

public class Node {

	// N.B. state è stato rappresentato tramite un array di generici oggetti e non con un singolo oggetto in modo da poter gestire più facilmente
	// stati identificati tramite più elementi. Nel caso in cui lo stato sia costituito da un unico oggetto, nella specifica classe sarà utilizzato 
	// solamente l'elemento 0 dell'array. In questo modo sarà comunque possibile utilizzare senza alcun problema TUTTE le generiche classi
	// relative agli algoritmi.
	// (es. in 8puzzle lo stato è rappresentato dalla singola stringa di numeri, in MazeRun è rappresentato da un array di due Integer indicanti le coordinate del punto)
	private Object[] state;
	private Node parent;
	private double pathCost;
	private double heuristicCost;
	private int depth;
	
	
	public Node(Object[] state) {
		//state initialization
		this.state = state;
		this.pathCost = 0.0;
		this.depth = 0;
	}
	
	public Node(Object[] state, Node parent, double stepCost) {
		this.state = state;
		this.parent = parent;
		this.pathCost = stepCost + parent.getCost();
		this.depth = parent.depth+1;
	}
	
	public Object[] getState() {
		return state;
	}
 	
	public double getCost() {
		return pathCost;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setHeuristic(double h) {
		this.heuristicCost = h;
	}
	
	public double getHeuristic() {
		return heuristicCost;
	}
	
	

	public List<Node> getPath() {
		List<Node> path = new ArrayList<Node>();
		Node temp = this;
		while(temp.parent != null) {
			path.add(temp);
			temp = temp.parent;
		}
		path.add(temp);
		return path;
	}
}