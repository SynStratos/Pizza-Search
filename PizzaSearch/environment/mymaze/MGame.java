package mymaze;

import adversarial_p.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MGame extends Game {
    private int PLAYER = -1;
    private int COMPUTER = 1;
    public MBoard b = new MBoard();


    ExpectiMiniMax search = new ExpectiMiniMax();

    @Override
    public Move move() {
        int dice = ThreadLocalRandom.current().nextInt(1, 4 + 1); // rnumero random tra 0 e 4;

        return (Move)search.expectmm(this, COMPUTER, dice, 2)[1];

    }

    @Override
    public int evaluate() {

       /* if( hasWon(PLAYER) ) return -1;
        if( hasWon(COMPUTER) ) return 1;

        return 0;*/

        int[] curp = b.playerIndex(PLAYER);
        int[] curc = b.playerIndex(COMPUTER);

        int diff_x = Math.abs(curp[0] - 8);
        int diff_y = Math.abs(curp[1] - 4);

        int diffp = diff_x + diff_y;

        int diff_x2 = Math.abs(curc[0] - 8);
        int diff_y2 = Math.abs(curc[1] - 4);

        int diffc = diff_x2 + diff_y2;

        return -(diffc - diffp);
    }

    @Override
    public boolean hasWon(int player) {

        if(b.board[8][4] == player) return true;
        return false;
    }

    @Override
    public boolean gameOver() {

        if( hasWon(PLAYER) || hasWon(COMPUTER)) return true;

        return false;
    }

    @Override
    public List<Move> getMoves(int player, int dice) {

        List<Move> list = new LinkedList<>();
        double prob = 0.25;

        int[] pos = b.playerIndex(player);

        int x = pos[0];
        int y = pos[1];

        MMove m1 = new MMove(x+dice, y, prob);
        if(validMove(m1)) list.add(m1);

        MMove m2 = new MMove(x, y+dice, prob);
        if(validMove(m2)) list.add(m2);

        MMove m3 = new MMove(x-dice, y, prob);
        if(validMove(m3)) list.add(m3);

        MMove m4 = new MMove(x, y-dice, prob);
        if(validMove(m4)) list.add(m4);



        /*
        * in base al giocatore valuto le mosse possibili per movimento e ostacolo
        * se mossa è valida aggiungo probabilità aggiungo all'elenco di mosse da restituire
        */
        /* tiro un d4 per decidere di quanto muovermi
        * tiro un d4 per stabibilire dove mettere l'ostacolo intorno all'aversario
        */


        return list;
    }

    @Override
    public void makeMove(Move move, int player) {

        int[] old = b.playerIndex(player);

        b.board[old[0]][old[1]] = 0;

        MMove m = (MMove)move;
        int x = m.move[0];
        int y = m.move[1];

        b.board[x][y] = player;
    }

    @Override
    public GameBoard getBoard() {
        MBoard bk = new MBoard();
        for(int i = 0; i <9; i++) {
            for(int j=0; j<9; j++) {
                bk.board[i][j] = b.board[i][j];
            }
        }
        return bk;
    }

    @Override
    public void resetBoard(GameBoard backup) {
        MBoard bk = (MBoard)backup;
        for(int i = 0; i <9; i++) {
            for(int j=0; j<9; j++) {
                b.board[i][j] = bk.board[i][j];
            }
        }
    }

    // FUNZIONE PER CONTROLLARE LA VALIDITA DELLA MOSSA
    public boolean validMove(Move move){
        MMove m = (MMove)move;
        int x = m.move[0];
        int y = m.move[1];

        // controllo se la mossa non supera i limiti della board
        if( x < 0) return false;
        if( y < 0) return false;

        if( x > 8) return false;
        if( y > 8) return false;

        // controllo se la casella non è occupata dall'avversario
        if( b.board[x][y] != 0 ) return false;

        return true;
    }


}
