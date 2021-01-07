package com.polytech.bowling;

public class Score{

    public static final int MAX_TURN = 10;

    private int[] scores = new int[MAX_TURN];
    private int[] ttls = new int[MAX_TURN];
    private int[] nbQuillesTombees = new int[MAX_TURN];

    public Score(){
        //.
    }

    private void spare(int turn){
        ttls[turn] = 1;
    }
    private void strike(int turn){
        ttls[turn] = 2;
    }

    public void addPoint(int nbQuilles, int turn, int lancer){
        turn--;
        if(turn - 2 >= 0 && ttls[turn - 2] > 0){
            scores[turn - 2] += nbQuilles;
            ttls[turn - 2]--;
        }
        if(turn - 1 >= 0 && ttls[turn - 1] > 0){
            scores[turn - 1] += nbQuilles;
            ttls[turn - 1]--;
        }
        scores[turn] += nbQuilles; 
        if(lancer == 1 && nbQuilles == 10 && turn != 9){
            strike(turn);
        }
        else if (lancer == 2 && nbQuilles + nbQuillesTombees[turn] == 10 && turn != 9){
            spare(turn);
        }
        nbQuillesTombees[turn] += nbQuilles;
    }

    public int getScoreTotal(){
        int total = 0;
        for (int i = 0; i < scores.length ; i++) {
            if(ttls[i] == 0)
                total += scores[i];
        }
        return total;
    }

    public int getScoreTurn(int turn){
        turn--;
        return ttls[turn] == 0 ? scores[turn] : 0;
    }
}