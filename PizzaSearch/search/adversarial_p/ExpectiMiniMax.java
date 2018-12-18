package adversarial_p;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class ExpectiMiniMax {
	int PLAYER = -1;
	int COMPUTER = 1;

	public Object[] expectmm(Game game, int player, int dice, int maxdepth) {

		Object[] result = new Object[2];
		Move move = null;
		double bestScore;
		if (player == PLAYER) bestScore = Integer.MAX_VALUE;
		else bestScore = Integer.MIN_VALUE;
		double prob_score = 0;


		GameBoard backup = game.getBoard();

		//passo il valore del dado, genero le mosse possibili per quel valore
		List<Move> moves = game.getMoves(player, dice);
		Collections.shuffle(moves);

		if (moves.isEmpty() || maxdepth == 0) {
			bestScore = game.evaluate();
		} else {

			for (Move m : moves) {
			//per ogni mossa possibile dato il tiro di dado (passato come parametro)

				prob_score = 0;
				// faccio mossa sul board -> funzione di game
				game.makeMove(m, player);

				//se sono a profondità maggiore di 1 vuol dire che devo valutare nuovamente la probabilità del prossimo tiro di dado
				//altrimenti passo a valutare lo stato attuale delle mosse
				if (maxdepth > 1) {
					for (int d = 1; d < 5; d++) {
						double score = (Double) expectmm(game, player * (-1), d, maxdepth - 1)[0];
						prob_score += m.probability * score;
					}
				} else {
					prob_score = (Double) expectmm(game, player * (-1), 20, maxdepth - 1)[0];
				}

				//fase di valutazione dello score
				if (player == PLAYER) {
					if (prob_score < bestScore) {
						bestScore = prob_score;
						move = m;
					}
				}
				if (player == COMPUTER) {
					if (prob_score > bestScore) {
						bestScore = prob_score;
						move = m;
					}
				}
				game.resetBoard(backup);
			}

		}


		result[0] = bestScore;
		result[1] = move;

		return result;
	}
}