package framework;

import java.util.ArrayList;

//azioni possibili -> cambiano a seconda del gioco

public abstract class Board {
	
	public Node parent;
	public Object[] state;
	
	public Board(Node parent) {
		this.parent = parent;
	};

	public abstract ArrayList<Node> buildChildren();
	
}
