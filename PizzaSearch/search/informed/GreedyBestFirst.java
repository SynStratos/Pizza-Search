package informed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import framework.Board;
import framework.Heuristic;
import framework.Node;
import utils.HeuristicComparator;


public class GreedyBestFirst {

	private Node root;
	private Node goal;
	private Board board;
	private Heuristic heuristic;
	private int step = -1;
	
	private boolean reached = true;
	
	Queue<Node> queue = new PriorityQueue<Node>(10, new HeuristicComparator());
	List<Node> explored = new ArrayList<Node>();
	
	public GreedyBestFirst(Node root, Node goal, Board board, Heuristic heuristic) {
		this.root=root;
		this.goal=goal;
		this.board=board;
		this.heuristic=heuristic;
	}
	
	public Node run()
	{
		//imposto come costo stimato del nodo radice quello ottenuto dalla funzione euristica
		//questa è stata precedentemente settata con il nodo radice all'inizio dell'esecuzione
		root.setHeuristic(heuristic.hCost()); 
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
			//per ogni nodo child ottenuto aggiorno il valore del nodo da esaminare e aggiungo al nodo child da inserire in coda il rispettivo valore di costo stimato
			//il comparator "HeuristicComparator" ordinerà gli elementi della coda in base al costo euristico inferiore
			while(it.hasNext()) {
				Node t = it.next();
				if(!checkExplored(t)) {
					heuristic.setNode(t);
					t.setHeuristic(heuristic.hCost());
					queue.add(t);
				}
			}

		}
		System.out.println("No result found in " + step + " steps.");
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