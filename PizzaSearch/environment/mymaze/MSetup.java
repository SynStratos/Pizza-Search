package mymaze;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MSetup {

    MGame game = new MGame();



    boolean begin;
    int PLAYER = -1;
    int COMPUTER = 1;

    boolean myturn = true;

    public MSetup(int i) {
        if(i == 0) begin = false;
        if(i == 1) begin = true;
    }

    public void run(){
        game.b.board[0][0] = 1;
        game.b.board[0][8] = -1;
        Scanner scan = new Scanner(System.in);

        MMove mymove;
        MMove advmove;
        int dice = 0;


        if(begin){
            mymove = (MMove)game.move();
            game.makeMove(mymove, COMPUTER);
        }
        print();
        while(!game.gameOver()) {

            if(myturn) {
                dice = ThreadLocalRandom.current().nextInt(1, 4 + 1);
                System.out.println("Il risultato del tuo dado è: " + dice);
                myturn = false;
            }
            System.out.println("In che direzione vuoi muoverti? (sx dx up dw)");

            String direction = scan.nextLine();

            int[] pl = game.b.playerIndex(PLAYER);

            switch (direction){
                case "sx": {
                    advmove = new MMove( pl[0], pl[1]-dice, 1/4);

                    if( game.validMove(advmove)) { game.makeMove(advmove, PLAYER); myturn=true; }
                    else System.out.println("Mossa non valida");
                    break;
                }
                case "dx": {
                    advmove = new MMove( pl[0], pl[1]+dice, 1/4);
                    if( game.validMove(advmove)) { game.makeMove(advmove, PLAYER); myturn=true; }
                    else System.out.println("Mossa non valida");
                    break;
                }
                case "up": {
                    advmove = new MMove( pl[0]-dice, pl[1], 1/4);
                    if( game.validMove(advmove)) { game.makeMove(advmove, PLAYER); myturn=true; }
                    else System.out.println("Mossa non valida");
                    break;
                }
                case "dw": {
                    advmove = new MMove( pl[0]+dice, pl[1], 1/4);

                    if( game.validMove(advmove)) { game.makeMove(advmove, PLAYER); myturn=true; }
                    else System.out.println("Mossa non valida");
                    break;
                }
                default: break;
            }

            print();

            if(game.hasWon(PLAYER)) {
                System.out.println("Hai vinto!");
                System.exit(0);
            }

            if(myturn){
                System.out.println("Turno avversario");
                mymove = (MMove)game.move();
                game.makeMove(mymove, COMPUTER);

                print();

                if(game.hasWon(COMPUTER)) {
                    System.out.println("Hai perso!");
                    System.exit(0);
                }
            }

        }
    }


    private void print(){
        for(int i=0; i<9; i++) {
            System.out.println("");
            for(int j=0; j<9; j++) {
                if(game.b.board[i][j] == PLAYER) System.out.print("[X] ");
                if(game.b.board[i][j] == COMPUTER) System.out.print("[O] ");
                if(game.b.board[i][j] == 0) System.out.print("[ ] ");
            }
        }
        System.out.println("");
    }
}
