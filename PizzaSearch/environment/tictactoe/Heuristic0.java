package tictactoe;

import java.util.List;

public class Heuristic0 extends Heuristic {
    public Heuristic0(List<int[]> victory, TicTacToeBoard b) {
        super(victory, b);
    }

    @Override
    public int value() {
        /*
         * restituisco 0 nel momento in cui in ogni fila considerata ho elementi sia dell'uno che dell'altro giocatore
         * restituisco 1 nel momento in cui in almeno una delle file considerate ci sono 3 caselle occupate da COMPUTER
         * restituisco -1 nel momento in cui in almeno una delle file considerate ci sono 3 caselle occupate da PLAYER
         */
        for(int[] i : victory) {
            if(board.board[i[0]] == COMPUTER && board.board[i[1]]==COMPUTER && board.board[i[2]]==COMPUTER) return 1;
            if(board.board[i[0]] == PLAYER && board.board[i[1]]==PLAYER && board.board[i[2]]==PLAYER) return -1;
        }
        return 0;
    }
}
