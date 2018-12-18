package mymaze;

import adversarial_p.Move;

public class MMove extends Move {

    public int[] move = new int[2];

    public MMove(int x, int y, double p){
        move[0] = x;
        move[1] = y;
        probability = p;
    }

}
