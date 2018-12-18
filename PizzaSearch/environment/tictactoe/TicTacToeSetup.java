package tictactoe;

import java.util.Scanner;

// CLASSE UTILE PER LA CREAZIONE DELLO STATO INIZIALE DEL GIOCO E LA GESTIONE DEI TURNI/VITTORIA

public class TicTacToeSetup {

	TicTacToeGame game = new TicTacToeGame();

	boolean begin;
	int PLAYER = -1;
	int COMPUTER = 1;
	
	public TicTacToeSetup(int i) {
		if(i == 0) begin = false;
		if(i == 1) begin = true;		
	}
	
	public void run() {
		Scanner scan = new Scanner(System.in);
		int human = -1;
		
		TicTacToeMove mymove;
		TicTacToeMove advmove;
		
		if(begin) {
			mymove = (TicTacToeMove) game.move();
			game.makeMove(mymove, COMPUTER);
			
			print();
		}
		
		while(!game.gameOver()) {
			System.out.println("Inserisci numero della casella per la prossima mossa (da 1 a 9): ");
			human = Integer.parseInt( scan.nextLine() ) -1;
			if(game.board.board[human] == 0) {
				advmove = new TicTacToeMove(human);
				game.makeMove(advmove, PLAYER);
				
				print();
				
				if(game.hasWon(PLAYER)) {
					System.out.println("Hai vinto!");
					System.exit(0);
				}
				
				if(game.gameOver()) break;
				
				System.out.println("Turno avversario");
				
				mymove = (TicTacToeMove) game.move();
				game.makeMove(mymove, COMPUTER);
				
				print();
				
				if(game.hasWon(COMPUTER)) {
					System.out.println("Hai perso!");
					System.exit(0);
				}		
			}else {
				System.out.println("Casella già occupata. Inserisci di nuovo");
			}
		}
		System.out.println("Pareggio!");
		System.exit(0);
	}
	
	private void print() {
			
		for(int i=0; i<9; i++) {
			if(i%3 == 0) System.out.println("");
			if(game.board.board[i] == PLAYER) System.out.print("[X] ");
			if(game.board.board[i] == COMPUTER) System.out.print("[O] ");
			if(game.board.board[i] == 0) System.out.print("[ ] ");
		}
		System.out.println("");
		
	}
	
}
