package mazerun;

import java.util.List;

import framework.Node;
import informed.Astar;
import informed.GreedyBestFirst;
import uninformed.BreadthFirst;
import uninformed.DepthFirst;
import uninformed.DepthLimited;
import uninformed.IterativeDeepening;
import uninformed.UniformCost;

public class RunMaze {

	private int dim;
	private int walls;
	private Integer[] begin;
	private Integer[] end;
	private String algorithm;
	
	public RunMaze(int dim, int walls, Integer[] begin, Integer[] end, String algorithm) {
		this.dim = dim;
		this.walls = walls;
		this.begin = begin;
		this.end = end;
		this.algorithm = algorithm;
	}

	
	public void run() {
		
		Node root = new Node(begin);
		Node goal = new Node(end);
		Node result = null;
		
		MazeBoard pb = new MazeBoard(root, dim, walls);
		MazeHeuristic ph = new MazeHeuristic(root, goal);
		
		switch (algorithm) {
		case "0" : {
			BreadthFirst bf = new BreadthFirst(root,goal,pb);
			result = bf.run();
			break;
		}
		case "1" : {
			DepthFirst bf = new DepthFirst(root,goal,pb);
			result = bf.run();
			break;
		}
		case "2" : {
			DepthLimited bf = new DepthLimited(root,goal,pb);
			result = bf.run();
			break;
		}
		case "3" : {
			IterativeDeepening bf = new IterativeDeepening(root,goal,pb);
			result = bf.run();
			break;
		}
		case "4" : {
			UniformCost bf = new UniformCost(root,goal,pb);
			result = bf.run();
			break;
		}
		case "5" : {
			GreedyBestFirst bf = new GreedyBestFirst(root,goal,pb,ph);
			result = bf.run();
			break;
		}
		case "6" : {
			Astar bf = new Astar(root,goal,pb, ph);
			result = bf.run();
			break;
		}
		default : return;
		}
		
		
		
		List<Node> path = result.getPath();
		for(Node a:path) {
			System.out.print("[");
			System.out.print(a.getState()[0].toString());
			System.out.print(",");
			System.out.print(a.getState()[1].toString());
			System.out.println("]");
		}
		System.exit(0);
	}
}
