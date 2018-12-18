package mymaze;


import adversarial_p.GameBoard;

public class MBoard extends GameBoard {

    public int[][] board = new int[9][9];



    public int[] playerIndex(int player){
        int[] a = new int[2];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if( board[i][j] == player) {

                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return null;
    }

}
