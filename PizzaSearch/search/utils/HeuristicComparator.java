package utils;


import java.util.Comparator;

import framework.Node;

public class HeuristicComparator implements Comparator<Node>{

	@Override
	public int compare(Node arg0, Node arg1) {
		
		 if (arg0.getHeuristic() < arg1.getHeuristic())		return -1; 
         else if (arg0.getHeuristic() > arg1.getHeuristic())	return 1; 
         
		 return 0;
	}

	
}
