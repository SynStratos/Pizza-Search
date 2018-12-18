package mazerun;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Iterator;

import framework.Board;
import framework.Node;

public class MazeBoard extends Board {

	private int dim;
	private int wall_n;
	private List<Integer[]> walls = new ArrayList<Integer[]>();
	private boolean collision = false;
	
	public MazeBoard(Node parent, int dim, int wall_n) {
		super(parent);
		this.dim = dim;
		this.wall_n = wall_n;
		Integer[] wall = new Integer[2];
		for(int i=0; i<wall_n; i++) {
			wall[0] = ThreadLocalRandom.current().nextInt(1, dim-1);
			wall[1] = ThreadLocalRandom.current().nextInt(1, dim-1);
			walls.add(wall);
		}
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public ArrayList<Node> buildChildren() {
		ArrayList<Node> children = new ArrayList<Node>();
		
		Integer[] state = (Integer[]) parent.getState();
		Integer[] child = new Integer[2];
		
		//movimento a sinistra
		if(state[1] != 0) {
			Iterator<Integer[]> it = walls.iterator();
			collision=false;
			while(it.hasNext()) {
				Integer[] t = it.next();
				if(state[0] == t[0] && state[1] - 1 == t[1]) {
					collision=true;
				}
			}
			if(!collision) {
				child = new Integer[2];
				child[0] = state[0];
				child[1] = state[1] - 1;
				children.add( new Node(child, parent, 2) );
			}
		}
		//movimento a destra
		if(state[1] != dim-1) {
			Iterator<Integer[]> it = walls.iterator();
			collision=false;
			while(it.hasNext()) {
				Integer[] t = it.next();
				if(state[0] == t[0] && state[1] + 1 == t[1]) {
					collision=true;
				}
			}
			if(!collision) {
				child = new Integer[2];
				child[0] = state[0];
				child[1] = state[1] + 1;
				children.add( new Node(child, parent, 2) );
			}
		}
		//movimento in alto
		if(state[0] != 0) {
			Iterator<Integer[]> it = walls.iterator();
			collision=false;
			while(it.hasNext()) {
				Integer[] t = it.next();
				if(state[0] == t[0] -1 && state[1] == t[1]) {
					collision=true;
				}
			}
			if(!collision) {
				child = new Integer[2];
				child[0] = state[0] -1;
				child[1] = state[1];
				children.add( new Node(child, parent, 1) );
			}
		}
		//movimento in basso
		if(state[0] != dim-1) {
			Iterator<Integer[]> it = walls.iterator();
			collision=false;
			while(it.hasNext()) {
				Integer[] t = it.next();
				if(state[0] == t[0] + 1 && state[1] == t[1]) {
					collision=true;
				}
			}
			if(!collision) {
				child = new Integer[2];
				child[0] = state[0] + 1;
				child[1] = state[1];
				children.add( new Node(child, parent, 3) );
			}
		}
		
		return children;
	
	}
	
}
