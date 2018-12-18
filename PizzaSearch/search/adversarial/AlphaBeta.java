package adversarial;

import java.util.Collections;
import java.util.List;

public class AlphaBeta {
	int PLAYER = -1;
	int COMPUTER = 1;
	
	public Object[] alphabeta(Game game, int player, int alpha, int beta)
	{
		Object[] result = new Object[2];
		
		GameBoard backup = game.getBoard();

		int score;
		if(player == PLAYER) score = Integer.MAX_VALUE;
		else score = Integer.MIN_VALUE;

		Move move = null;
		
		List<Move> moves = game.getMoves();
		Collections.shuffle(moves);
		
		if(moves.isEmpty()) {

			score = game.evaluate();
			result[0] = score;
		}else {
			//per ognuna delle mosse possibili avvio la ramificazione dell'algoritmo minimax
			for(Move i : moves) {				
				//FUNZIONE DI GAME CHE ESEGUE LA MOSSA --> EQUIVALENTE NEL TRIS AD ESEMPIO AD ASSEGNARE IL NUMERO DEL GIOCATORE ALLA CASELLA
				game.makeMove(i, player);
				
				if(player == PLAYER) {
				//human
				//devo minimizzare il valore nel caso in cui il giocatore selezionato sia l'avversario (umano)
					score = (Integer)alphabeta(game, COMPUTER, alpha, beta)[0];
					if(score < beta) {
						beta = score;
						move = i;
					}
				}else {
				//pc
				//devo massimizzare il valore nel caso in cui il giocatore selezionato sia il computer
					score = (Integer)alphabeta(game, PLAYER, alpha, beta)[0];
					if(score > alpha) {
						alpha = score;
						move = i;
					}
					
				}
				//resetto mossa per provare quelle successive
				game.resetBoard(backup);
				
				//cutoff nel caso in cui alpha sia maggiore di beta
				if(alpha >= beta) break;
		
			}
			
			//per il giocatore avversario, restituisco come punteggio il valore di beta aggiornato
			//per il giocatore da masssimizzare invece restituisco alpha
			//nel caso in cui non ci siano mosse invece ho restituito come score il risultato della funzione evaluate() della classe GameBoard
			if(player == PLAYER) {
				result[0] = beta;
			}else {
				result[0] = alpha;
			}
		}
		
		

		result[1] = move;
		return result;
	}
}
