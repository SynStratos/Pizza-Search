package connectfour;

import java.util.Scanner;

public class FourSetup {

	FourGame game = new FourGame();

	boolean begin;
	int PLAYER = -1;
	int COMPUTER = 1;
	
	public FourSetup(int i) {
		if(i == 0) begin = false;
		if(i == 1) begin = true;		
	}
	
	public void run() {
		Scanner scan = new Scanner(System.in);
		
		int column = -1;
		int row = -1;
		
		FourMove mymove;
		FourMove advmove;
		
		if(begin) {
			
			mymove = (FourMove) game.move();
			game.makeMove(mymove, COMPUTER);
			
			print();			
		}
		
		while(!game.gameOver()) {
			System.out.println("Inserisci la colonna in cui inserire la prossima pedina (da 1 a 7): ");
			column = Integer.parseInt( scan.nextLine() ) -1;
			
			for(int j=5; j>-1; j--) {
				if( game.b.board[j][column] == 0) {
					row = j;
					break;
				}
			}
//			
//			for(int j=3; j>-1; j--) {
//				if( game.b.board[j][column] == 0) {
//					row = j;
//					break;
//				}
//			}
			
			if(row > -1) {
				advmove = new FourMove(row, column);
				game.makeMove(advmove, PLAYER);
				
				print();
				
				if(game.hasWon(PLAYER)) {
					System.out.println("Hai vinto!");
					System.exit(0);
				}
				
				if(game.gameOver()) break;
				
				System.out.println("Turno avversario");
				
				mymove = (FourMove) game.move();
				game.makeMove(mymove, COMPUTER);
				
				print();
				
				if(game.hasWon(COMPUTER)) {
					System.out.println("Hai perso!");
					System.exit(0);
				}	
				
				row = -1;
			}else {
				System.out.println("Colonna già piena. Inserisci di nuovo");
			}
		}
		System.out.println("Pareggio!");
		System.exit(0);
	}
	
	
	private void print() {
		for(int i=0; i<6; i++) {
//		for(int i=0; i<4; i++) {
			System.out.println("");
			for(int j=0; j<7; j++) {
//			for(int j=0; j<4; j++) {
				if(game.b.board[i][j] == PLAYER) System.out.print("[X] ");
				if(game.b.board[i][j] == COMPUTER) System.out.print("[O] ");
				if(game.b.board[i][j] == 0) System.out.print("[ ] ");
			}
		}
		System.out.println("");
	}
	
}
