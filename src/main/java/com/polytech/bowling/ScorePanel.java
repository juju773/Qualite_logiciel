package com.polytech.bowling;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ScorePanel extends JPanel {

    int scoreLancer1;
    int scoreLancer2;
    JPanel turnPanel;
    JLabel finalScore = new JLabel();

    public ScorePanel(int turn) {
        if(turn == Score.MAX_TURN)
            turnPanel = new JPanel(new GridLayout(1, 3));
        else
            turnPanel = new JPanel(new GridLayout(1, 2));
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel lTurn = new JLabel("" + turn);
        lTurn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lTurn, 2 , 0);
        this.add(turnPanel, 1, 0);
        this.add(finalScore, 0, 0);
        this.setPreferredSize(new Dimension(50, 50));
    }

    public void setFinalScore(int score){
        finalScore.setText("" + score);
    }

    public void setScore(int score, int lancer){
        this.scoreLancer1 = score;
        turnPanel.add(new JLabel("" + score), 0 , lancer - 1);
    }
}