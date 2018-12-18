package connectfour;

import adversarial.Move;

public class FourMove extends Move {
	
	public int[] move = new int[2];
	//move[0] = row
	//move[1] = column
	
	public FourMove(int x, int y) {
		move[0] = x;
		move[1] = y;
	}
}
