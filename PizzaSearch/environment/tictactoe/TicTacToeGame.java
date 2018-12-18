package tictactoe;

import java.util.*;

import adversarial.*;

public class TicTacToeGame extends Game {
	
	public TicTacToeBoard board = new TicTacToeBoard();
	private List<int[]> victory = new ArrayList<>();
	private int PLAYER = -1;
	private int COMPUTER = 1;
	
	MiniMax search = new MiniMax();
	AlphaBeta pruned_search = new AlphaBeta();
	
	public TicTacToeGame() {
		victory.add(new int[]{0,1,2});
		victory.add(new int[]{3,4,5});
		victory.add(new int[]{6,7,8});
		victory.add(new int[]{0,3,6});
		victory.add(new int[]{1,4,7});
		victory.add(new int[]{2,5,8});
		victory.add(new int[]{0,4,8});
		victory.add(new int[]{2,4,6});
	}
	
	public Move move() {
		//avvio algoritmo minimax con parametro relativo al giocatore (pc)
		//restituisco il valore della casella in modo da poter riempire la board e continuare il gioco
		
		//MINIMAX
		return (Move)search.minimax(this, COMPUTER)[1];
		//ALPHA-BETA
//		return (Move)pruned_search.alphabeta(this, COMPUTER, Integer.MIN_VALUE, Integer.MAX_VALUE)[1];
	}
	
	// funzione per generare tutte le mosse possibili nello stato attuale del board
	// nel caso in cui uno dei due giocatori abbia vinto restituisco un'array nullo -> nessuna mossa sarà "necessaria"
	public List<Move> getMoves(){
		List<Move> next = new LinkedList<>();
		
		if(hasWon(1) || hasWon(-1)) {
			return next;
		}
		
		for(int i=0; i<9; i++) {
			if( board.board[i] == 0) {
				next.add( new TicTacToeMove(i));
			}
		}		
		//aggiungo tutte le possibili caselle dello step successivo (sostanzialmente tutte le caselle vuote
		//se non ci sono caselle vuote la partita è finita --> funzione vittoria
		
		return next;	
	}

	
	// funzione che valuta lo stato complessivo del board
	public int evaluate() {
		// +1 0 -1
		Heuristic h = new Heuristic0(victory, board);

		// +100 +10 +1 0 -1 -10 -100
//		Heuristic h = new Heuristic1(victory, board);

		return h.value();
	}
	

	// funzione che controlla se il giocatore passato come parametro ha soddisfatto una delle condizioni di vittoria
	public boolean hasWon(int player) {
		/*
		 * confronto board con possibili condizioni di vittoria per il singolo giocatore
		 * esamino il contenuto della board nelle combinazioni di caselle predefinite per la vittoria
		 * nel momento in cui trovo lo stesso valore (corrispondente al player in esame) su una delle combinazioni di 3 caselle
		 * restituisco TRUE per indicare che il giocatore in questione ha vinto la partita
		 * 012 345 678 036 147 258 048 246		
		 */
		for(int[] i : victory) {
			if(board.board[i[0]] == player && board.board[i[1]]==player && board.board[i[2]]==player) return true;
		}
		return false;
	}
	
	//funzione che controlla se il board è completo o meno (ovvero nessun valore è assegnato a 0)
	public boolean gameOver() {
		for(int i = 0; i <9; i++) {
			if(board.board[i] == 0) return false;
		}		
		return true;
	}
	

	@Override
	public void makeMove(Move move, int player) {
		// TODO Auto-generated method stub
		TicTacToeMove m = (TicTacToeMove) move;
		int i = m.i;
		board.board[i] = player;
		
	}

	@Override
	public void resetBoard(GameBoard backup) {
		TicTacToeBoard b = (TicTacToeBoard) backup;
		for(int i=0; i<9; i++) {
			board.board[i] = b.board[i];
		}
	}

	@Override
	public GameBoard getBoard() {
		TicTacToeBoard b = new TicTacToeBoard();
		for(int i=0; i<9; i++) {
			b.board[i] = board.board[i];
		}
		return b;
	}

}
