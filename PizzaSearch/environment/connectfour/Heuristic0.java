package connectfour;

import java.util.List;

public class Heuristic0 extends Heuristic {
    public Heuristic0(List<List<int[]>> toCheck, FourBoard b) {
        super(toCheck, b);
    }

    @Override
    public int value() {
        int x = 0;
        int y = 0;
        int score = 0;

        for( List<int[]> check : toCheck) {
            score -= check_pairs(check, PLAYER);
            score -= check_triples(check, PLAYER);

            score += check_pairs(check, COMPUTER);
            score += check_triples(check, COMPUTER);
        }
        return score;
    }

    private int check_pairs(List<int[]> check, int giocatore){
        int pairs = 0;

        for (int i = 0; i < check.size()-1; i++) {
            int[] current = check.get(i);
            int[] prox = check.get(i+1);

            if(b.board[current[0]][current[1]] != giocatore){
                continue;
            }
            if(b.board[current[0]][current[1]] == b.board[prox[0]][prox[1]]){
                pairs++;
            }
        }
        return pairs;
    }

    private int check_triples(List<int[]> check, int giocatore){
        int triples = 0;

        for (int i = 0; i < check.size()-2; i++) {
            int[] current = check.get(i);
            int[] prox = check.get(i+1);
            int[] prox_prox = check.get(i+2);

            if(b.board[current[0]][current[1]] != giocatore){
                continue;
            }
            if(b.board[current[0]][current[1]] == b.board[prox[0]][prox[1]]
                    &&
                    b.board[current[0]][current[1]] == b.board[prox_prox[0]][prox_prox[1]]){
                triples++;
            }
        }
        return triples;
    }
}
