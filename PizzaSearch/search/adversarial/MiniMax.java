package adversarial;

import java.util.Collections;
import java.util.List;

public class MiniMax {
	int PLAYER = -1;
	int COMPUTER = 1;
	public Object[] minimax(Game game, int player)
	{
		//SearchResult result = new SearchResult();
		
		Object[] result = new Object[2];
		
//		//salvo il risultato in un vettore di due interi
//		//il primo elemento corrisponde al valore del "bestscore" mentre il secondo all'indice della mossa da fare
		
		GameBoard backup = game.getBoard();
		
		int bestScore;
		
		if(player == PLAYER) bestScore = Integer.MAX_VALUE;
		else bestScore = Integer.MIN_VALUE;
		
		Move move = null;
		
		List<Move> moves = game.getMoves();
		Collections.shuffle(moves);
		
		if(moves.isEmpty()) {
			bestScore = game.evaluate();
		}else {
			//per ognuna delle mosse possibili avvio la ramificazione dell'algoritmo minimax
			for(Move i : moves) {
				//FUNZIONE DI GAME CHE ESEGUE LA MOSSA --> EQUIVALENTE NEL TRIS AD ESEMPIO AD ASSEGNARE IL NUMERO DEL GIOCATORE ALLA CASELLA
				game.makeMove(i, player);
				
				if(player == PLAYER) {
				//human
				//devo minimizzare il valore nel caso in cui il giocatore selezionato sia l'avversario (umano)
					int score = (Integer)minimax(game, COMPUTER)[0];
					if(score < bestScore) {
						bestScore = score;
						move = i;
					}
				}else {
				//pc
				//devo massimizzare il valore nel caso in cui il giocatore selezionato sia il computer
					int score = (Integer)minimax(game, PLAYER)[0];
					if(score > bestScore) {
						bestScore = score;
						move = i;
					}
					
				}
				//resetto mossa per provare quelle successive
				game.resetBoard(backup);
		
			}
		
			
		}
		
		result[0] = bestScore;
		result[1] = move;
		
		return result;
	}
}
