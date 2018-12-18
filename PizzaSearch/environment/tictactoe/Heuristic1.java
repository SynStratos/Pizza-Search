package tictactoe;

import java.util.List;

public class Heuristic1 extends Heuristic {
    public Heuristic1(List<int[]> victory, TicTacToeBoard b) {
        super(victory, b);
    }

    @Override
    public int value() {
        /*
         * restituisco 0 nel momento in cui nella fila considerata ho elementi sia di un giocatore che di un altro
         * per ogni giocatore, il punteggio (POSITIVO per pc e NEGATIVO per giocatore) sarà pari a 1, 10, 100 a seconda
         * di quante caselle sono occupate dal segno corrispondente
         */
        int result = 0;
        for(int[] i : victory) {
            int score =0;
            // PRIMA CELLA
            if(board.board[ i[0] ] == COMPUTER) score = 1;
            if(board.board[ i[0] ] == PLAYER) score = -1;

            // SECONDA CELLA
            if(board.board[ i[1] ] == COMPUTER) {
                //la casella precedente era assegnata al giocatore selezionato
                if(score > 0) score = 10;
                //la casella precedente era vuota
                if(score == 0) score = 1;
                //la casella precedente era assegnata all'avversario
                if(score < 0) score = 0;
            }else if(board.board[  i[1] ] == PLAYER) {
                //la casella precedente era assegnata al giocatore selezionato
                if(score < 0) score = -10;
                //la casella precedente era vuota
                if(score == 0) score = -1;
                //la casella precedente era assegnata all'avversario
                if(score > 0) score = 0;
            }

            // TERZA CELLA
            if(board.board[  i[2] ] == COMPUTER) {
                //la casella precedente era assegnata al giocatore selezionato
                if(score > 0) score *= 10;
                //la casella precedente era vuota
                if(score == 0) score = 1;
                //la casella precedente era assegnata all'avversario
                if(score < 0) score = 0;
            }else if(board.board[  i[2] ] == PLAYER) {
                //la casella precedente era assegnata al giocatore selezionato
                if(score < 0) score *= 10;
                //la casella precedente era vuota
                if(score == 0) score = -1;
                //la casella precedente era assegnata all'avversario
                if(score > 0) score = 0;
            }
            result +=score;
        }
        return result;
    }
}
