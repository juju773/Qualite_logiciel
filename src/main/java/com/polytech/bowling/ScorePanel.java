package com.polytech.bowling;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridLayout;

public class ScorePanel extends JPanel {

    int scoreLancer1;
    int scoreLancer2;
    JPanel turnPanel = new JPanel(new GridLayout(1, 2));
    JLabel finalScore = new JLabel();

    public ScorePanel(int turn) {
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel lTurn = new JLabel("" + turn);
        lTurn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lTurn, 0 , 0);
        this.add(turnPanel, 1, 0);
        this.add(finalScore, 2, 0);
    }

    public void setFinalScore(int score){
        finalScore.setText("" + score);
    }

    public void setScore(int score, int lancer){
        if(lancer == 1)
            setScoreLancer1(score);
        if(lancer == 2)
            setScoreLancer1(score);
    }

    public void setScoreLancer1(int score){
        this.scoreLancer1 = score;
        turnPanel.add(new JLabel("" + score), 0 , 0);
    }
    public void setScoreLancer2(int score){
        this.scoreLancer2 = score;
        turnPanel.add(new JLabel("" + score), 0 , 1);
    }


}