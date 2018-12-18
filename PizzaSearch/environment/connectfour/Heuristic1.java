package connectfour;

import java.util.List;

public class Heuristic1 extends Heuristic{
    public Heuristic1(List<List<int[]>> toCheck, FourBoard b) {
        super(toCheck, b);
    }

    @Override
    public int value() {
        int x = 0;
        int y = 0;

        for( List<int[]> check : toCheck) {
            int countpc = 0;
            int countpl = 0;
            for(int[] i : check) {
                x = i[0];
                y = i[1];

                if(b.board[x][y] == COMPUTER) {
                    countpl=0;
                    countpc++;
                    if(countpc==4) return 1;
                }
                if(b.board[x][y] == PLAYER) {
                    countpc=0;
                    countpl++;
                    if(countpl==4) return -1;
                }

            }
        }
        return 0;
    }
}
