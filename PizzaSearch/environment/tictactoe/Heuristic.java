package tictactoe;

import java.util.List;

public abstract class Heuristic {

    public TicTacToeBoard board;
    public List<int[]> victory;
    public int PLAYER = -1;
    public int COMPUTER = 1;

    public Heuristic(List<int[]> victory, TicTacToeBoard board){
        this.victory = victory;
        this.board = board;
    }

    public abstract int value();
}
