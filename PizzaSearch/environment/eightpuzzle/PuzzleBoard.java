package eightpuzzle;

import java.util.*;

import framework.Board;
import framework.Node;

public class PuzzleBoard extends Board {
	//supponendo che il costo di ogni movimento in questo caso sia sempre costante == 1
	private double cost = 1.0;

	public PuzzleBoard(Node parent) {
		super(parent);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Node> buildChildren(){
		ArrayList<Node> children = new ArrayList<Node>();
		
		String state = parent.getState()[0].toString();
		
		StringBuilder childstate = new StringBuilder(state);	
		
		String[] temp = new String[1];
				
		switch(state.indexOf('0')) {
			
		case 0	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(0,state.charAt(1)); childstate.setCharAt(1, state.charAt(0));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(0,state.charAt(3)); childstate.setCharAt(3, state.charAt(0)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 1	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(1,state.charAt(0)); childstate.setCharAt(0, state.charAt(1)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(1,state.charAt(2)); childstate.setCharAt(2, state.charAt(1)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(1,state.charAt(4)); childstate.setCharAt(4, state.charAt(1)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 2	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(2,state.charAt(1)); childstate.setCharAt(1, state.charAt(2)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(2,state.charAt(5)); childstate.setCharAt(5, state.charAt(2)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 3	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(3,state.charAt(0)); childstate.setCharAt(0, state.charAt(3)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(3,state.charAt(4)); childstate.setCharAt(4, state.charAt(3)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(3,state.charAt(6)); childstate.setCharAt(6, state.charAt(3)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 4	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(4,state.charAt(5)); childstate.setCharAt(5, state.charAt(4)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(4,state.charAt(1)); childstate.setCharAt(1, state.charAt(4)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(4,state.charAt(3)); childstate.setCharAt(3, state.charAt(4)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(4,state.charAt(7)); childstate.setCharAt(7, state.charAt(4));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 5	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(5,state.charAt(2)); childstate.setCharAt(2, state.charAt(5));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(5,state.charAt(4)); childstate.setCharAt(4, state.charAt(5)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(5,state.charAt(8)); childstate.setCharAt(8, state.charAt(5)); 
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 6	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(6,state.charAt(3)); childstate.setCharAt(3, state.charAt(6));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(6,state.charAt(7)); childstate.setCharAt(7, state.charAt(6));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 7	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(7,state.charAt(4)); childstate.setCharAt(4, state.charAt(7));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(7,state.charAt(6)); childstate.setCharAt(6, state.charAt(7));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(7,state.charAt(8)); childstate.setCharAt(8, state.charAt(7));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		
		case 8	: {
			childstate = new StringBuilder(state);
			childstate.setCharAt(8,state.charAt(5)); childstate.setCharAt(5, state.charAt(8));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			temp = new String[1];
			childstate = new StringBuilder(state);
			childstate.setCharAt(8,state.charAt(7)); childstate.setCharAt(7, state.charAt(8));
			temp[0] = childstate.toString();
			children.add(new Node(temp, parent, cost));
			break;			
		}
		}
		
		
		
		return children;
	}

	
}
