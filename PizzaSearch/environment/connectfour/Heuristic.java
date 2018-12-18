package connectfour;

import java.util.List;

public abstract class Heuristic {

    public List<List<int[]>> toCheck;
    public FourBoard b;
    public int PLAYER = -1;
    public int COMPUTER = 1;

    public Heuristic(List<List<int[]>> toCheck, FourBoard b){
        this.toCheck = toCheck;
        this.b = b;
    }

    public abstract int value();
}
