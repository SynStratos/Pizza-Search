package connectfour;

import java.util.List;

public class Heuristic2 extends Heuristic {
    public Heuristic2(List<List<int[]>> toCheck, FourBoard b) {
        super(toCheck, b);
    }

    @Override
    public int value() {
        int x = 0;
        int y = 0;
        int score = 0;

        for( List<int[]> check : toCheck) {
            int countpc = 0;
            int countpl = 0;
            for(int[] i : check) {
                x = i[0];
                y = i[1];

                if(b.board[x][y] == COMPUTER) {
                    countpl=0;
                    countpc++;
                    if(countpc==4) {
                        score += 10;
                        break;
                    }
                }
                if(b.board[x][y] == PLAYER) {
                    countpc=0;
                    countpl++;
                    if(countpl==4) {
                        score -= 10;
                        break;
                    }
                }
            }
			if(countpc == 3) {
				score += 8;
			}
			if(countpl == 3) {
				score -= 8;
			}
        }
        return score;
    }
}
