package adversarial;

import java.util.List;

public abstract class Game {

	
	public abstract Move move();
	
	public abstract int evaluate(); //funzione che restituisce il valore dello stato raggiunto
	
	public abstract boolean hasWon(int player); //funzione che restituisce se un giocatore ha vinto o meno
	
	public abstract boolean gameOver(); //funzione che definisce se lo stato del board è completo 
	
	public abstract List<Move> getMoves();
	
	public abstract void makeMove(Move move, int player);
	
	public abstract GameBoard getBoard();
	
	public abstract void resetBoard(GameBoard backup);
}
