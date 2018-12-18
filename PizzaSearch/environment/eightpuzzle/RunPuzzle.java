package eightpuzzle;

import java.util.*;

import framework.*;
import informed.*;
import local.HillClimbing;
import local.SimulatedAnnealing;
import uninformed.*;

public class RunPuzzle {

	private String[] begin = new String[1];
	private String[] end = new String[1];
	private String algorithm;
	
	public RunPuzzle(String begin, String algorithm) {
		this.begin[0] = begin;
		this.end[0] = "123456780";
		this.algorithm = algorithm;
	}

	public void run() {
		
		Node root = new Node(begin);
		Node goal = new Node(end);
		Node result = null;
		
		PuzzleBoard pb = new PuzzleBoard(root);
		PuzzleHeuristic ph = new PuzzleHeuristic(root, goal);
		
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
		case "7" : {
			HillClimbing bf = new HillClimbing(root,goal,pb, ph);
			result = bf.run();
			break;
		}
		case "8" : {
			SimulatedAnnealing bf = new SimulatedAnnealing(root,goal,pb, ph);
			result = bf.run();
			break;
		}
		default : return;
		}
		
		
		
		List<Node> path = result.getPath();
		for(Node a:path) {
			System.out.println(a.getState()[0].toString());
		}
		System.exit(0);
	}
}
