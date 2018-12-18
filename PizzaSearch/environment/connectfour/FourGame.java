package connectfour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import adversarial.*;

public class FourGame extends Game {

	List< List<int[]> > toCheck = new ArrayList<>();
	private int PLAYER = -1;
	private int COMPUTER = 1;
	
	public FourBoard b = new FourBoard();
	LimitedMiniMax search = new LimitedMiniMax();
	AlphaBeta pruned_search = new AlphaBeta();
	LimitedAlphaBeta limited_search = new LimitedAlphaBeta();
	
	public FourGame(){
		List<int[]> temp;
		// elementi delle righe
		for(int i=0; i<6; i++) {
//		for(int i=0; i<4; i++) {
			temp = new LinkedList<>();
			for(int j=0; j<7; j++) {
//			for(int j=0; j<4; j++) {
				int[] t = {i, j};
				temp.add(t);
			}
			toCheck.add(temp);
		}
		
		// elementi delle colonne
		for(int j=0; j<7; j++) {
//		for(int j=0; j<4; j++) {
			temp = new LinkedList<>();
			for(int i=0; i<6; i++) {
//			for(int i=0; i<4; i++) {
				int[] t = {i, j};
				temp.add(t);
			}
			toCheck.add(temp);
		}
		
//		temp = new LinkedList<>();
//		temp.add(new int[] {0,0});
//		temp.add(new int[] {1,1});
//		temp.add(new int[] {2,2});
//		temp.add(new int[] {3,3});
//		toCheck.add(temp);
//		
//		temp = new LinkedList<>();
//		temp.add(new int[] {0,3});
//		temp.add(new int[] {1,2});
//		temp.add(new int[] {2,1});
//		temp.add(new int[] {3,0});
//		toCheck.add(temp);
		
		// elementi delle diagonali da sx a dx
		for(int i=0; i<4; i++) {
			temp = new LinkedList<>();
			int row=0;
			for(int col=i; col<7; col++) {
			
				
					int[] t = {row, col};
					temp.add(t);
					row ++;
					if(row==6) break;
				
			}
			toCheck.add(temp);
		}
		
		// elementi delle diagonali da dx a sx
		for(int i=6; i>2; i--) {
			temp = new LinkedList<>();
			int row=0;
			for(int col=i; col>-1; col--) {
					
					int[] t = {row, col};
					temp.add(t);
					row ++;
					if(row==6) break;
				
			}
			
			toCheck.add(temp);
		}
		
		temp = new LinkedList<>();
		temp.add(new int[] {1,0});
		temp.add(new int[] {2,1});
		temp.add(new int[] {3,2});
		temp.add(new int[] {4,3});
		temp.add(new int[] {5,4});
		toCheck.add(temp);
		
		temp = new LinkedList<>();
		temp.add(new int[] {2,0});
		temp.add(new int[] {3,1});
		temp.add(new int[] {4,2});
		temp.add(new int[] {5,3});
		toCheck.add(temp);
		
		temp = new LinkedList<>();
		temp.add(new int[] {1,6});
		temp.add(new int[] {2,5});
		temp.add(new int[] {3,4});
		temp.add(new int[] {4,3});
		temp.add(new int[] {5,2});
		toCheck.add(temp);
		
		temp = new LinkedList<>();
		temp.add(new int[] {2,6});
		temp.add(new int[] {3,5});
		temp.add(new int[] {4,4});
		temp.add(new int[] {5,3});
		toCheck.add(temp);
		
		
	}
	

	
	@Override
	public Move move() {
		//MINIMAX
//		return (Move)search.minimax(this, COMPUTER)[1];
		//ALPHA-BETA (sufficiente solo per configurazione 4x4)
//		return (Move)pruned_search.alphabeta(this, COMPUTER, Integer.MIN_VALUE, Integer.MAX_VALUE)[1];
		//LIMITED ALPHA-BETA
		return (Move)limited_search.alphabeta(this, COMPUTER, Integer.MIN_VALUE, Integer.MAX_VALUE, 10)[1];
	}
	
	@Override
	public int evaluate() {
		/*
		* lo score complessivo è dato dalla somma di quello di ogni giocatore, ottenuto aggiungendo 1 punto
		* per ogni coppia e tripla a suo favore all'interno di ogni riga/colonna/diagonale da controllore
		* */
		Heuristic h = new Heuristic0(toCheck, b);

		/*
		* lo score complessivo è dato da 1 nel caso di vittoria per MAX, 0 in caso di pareggio e -1 nel caso di vittoria di MIN
		* */
//		Heuristic h = new Heuristic1(toCheck, b);

		/*
		* lo score complessivo valuta la presenza di quattro caselle di fila all'interno della riga da controllare
		* assegnando un punteggio maggiore se le caselle sono 4 piuttosto che 3
		* */
//		Heuristic h = new Heuristic2(toCheck, b);

		/*
		* lo score in questo caso valuta anche le caselle vuote a vantaggio di uno o dell'altro giocatore
		* nel momento in cui la mia mossa risulta inutile per fermare l'avversario in quanto se la somma delle caselle occupate da lui
		* più la somma delle caselle vuote tra le sue è uguale a 4 non valuto la posizione
		* */
//		Heuristic h = new Heuristic3(toCheck, b);

		return h.value();
	}
	

	// FIXED: adesso funziona NB non modificare più
	@Override
	public boolean hasWon(int player) {
		int x = 0;
		int y = 0;
		int count = 0;
		for( List<int[]> check : toCheck) {
			count = 0;
			for(int[] i : check) {
				x = i[0];
				y = i[1];
				if(b.board[x][y] == player) {
					count++;
					if(count == 4) {
						return true;
					}
				}else {
					count = 0;
				}			
			}		
			
		}
		return false;
	}

	@Override
	public boolean gameOver() {
		for(int i = 0; i <6; i++) {
			for(int j=0; j<7; j++) {
				if(b.board[i][j] == 0) return false;
			}
		}		
//		for(int i = 0; i <4; i++) {
//			for(int j=0; j<4; j++) {
//				if(b.board[i][j] == 0) return false;
//			}
//		}	
		return true;
	}

	// funzione per generare tutte le mosse possibili nello stato attuale del board
	// nel caso in cui uno dei due giocatori abbia vinto restituisco un'array nullo -> nessuna mossa sarà "necessaria"
	public List<Move> getMoves() {
		List<Move> next = new LinkedList<>();
		
		if(hasWon(1) || hasWon(-1)) {
			return next;
		}
		
		for(int col = 0; col < 7; col++) {
			
			for(int row = 5; row > -1; row--) {
				
				if(b.board[row][col] == 0) {
					next.add( new FourMove(row, col));
					break;
				}				
			}			
		}
//		
//		for(int col = 0; col < 4; col++) {
//			
//			for(int row = 3; row > -1; row--) {
//				
//				if(b.board[row][col] == 0) {
//					next.add( new FourMove(row, col));
//					break;
//				}				
//			}			
//		}
		return next;
	}

	@Override
	public void makeMove(Move move, int player) {
		FourMove m = (FourMove)move;
		int x = m.move[0];
		int y = m.move[1];
		
		b.board[x][y] = player;
		
	}

	@Override
	public GameBoard getBoard() {
		FourBoard bk = new FourBoard();
		for(int i = 0; i <6; i++) {
			for(int j=0; j<7; j++) {
				bk.board[i][j] = b.board[i][j];
			}
		}
//		for(int i = 0; i <4; i++) {
//			for(int j=0; j<4; j++) {
//				bk.board[i][j] = b.board[i][j];
//			}
//		}
		return bk;
	}

	@Override
	public void resetBoard(GameBoard backup) {
		FourBoard bk = (FourBoard)backup;
		for(int i = 0; i <6; i++) {
			for(int j=0; j<7; j++) {
				b.board[i][j] = bk.board[i][j];
			}
		}	
//		for(int i = 0; i <4; i++) {
//			for(int j=0; j<4; j++) {
//				b.board[i][j] = bk.board[i][j];
//			}
//		}	
	}
}
