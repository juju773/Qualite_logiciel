
package com.polytech.bowling;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.border.Border;

public class launch extends JFrame implements ActionListener{

    public static final int MAX_JOUEUR = 5;

    List<JButton> boutons = new ArrayList<JButton>();
    Bowling bowling = new Bowling();
    Player p = new Player("test");

    JButton addPlayer = new JButton("+");
    JButton removePlayer = new JButton("-");

    //Init composants de la vue
    JPanel panelBoutonsQuilles = new JPanel(); 
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    JPanel panelBoutonAddRemove = new JPanel();
    JPanel panelLabelJoueurCourant = new JPanel(new GridLayout(2,1));
    JPanel panelScores = new JPanel(new GridLayout(MAX_JOUEUR,1));
    JPanel panelAffichageFin = new JPanel();

    //Tableau des scores
    JPanel panelJoueurScore = new JPanel(new GridLayout(MAX_JOUEUR,1));
    JPanel[] panelTableauScore = new JPanel[MAX_JOUEUR + 1];
    ScorePanel[][] panelTourScore = new ScorePanel[MAX_JOUEUR][Score.MAX_TURN];
    JLabel[] finalScores = new JLabel[MAX_JOUEUR];


    List<JLabel> listLabelScores = new ArrayList<JLabel>();

    int regleT10 = 0;
    boolean hasDoneT10 = false;

    // Colors
    Color whiteColor = new Color(246,246,255);
    Color blackColor = new Color(28,29,46);

    // Fonts
    Font bahnschriftBold = new Font("Bahnschrift", Font.BOLD, 14);
    Font bahnschrift = new Font("Bahnschrift", Font.PLAIN, 14);
    Border border = BorderFactory.createLineBorder(whiteColor,40);


    private int nbJoueurs = 0;
    JLabel labelNbJoueurs = new JLabel("Nombre de joueurs : 0");
    JLabel labelNBTour = new JLabel("Tour n° : 1");

    JLabel labelCurrentPlayer = new JLabel("");
    int currentPlayer = 0;

    JLabel labelScoreFin = new JLabel();

   public launch() {
        super("Bowling !");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(1200,600);
        setVisible(true);

        addPlayer.setBackground(Color.WHITE);
        addPlayer.setForeground(blackColor);
        panelBoutonAddRemove.add(addPlayer);
        removePlayer.setBackground(Color.WHITE);
        removePlayer.setForeground(blackColor);
        panelBoutonAddRemove.add(removePlayer);
        labelNbJoueurs.setFont(bahnschrift);
        panelBoutonAddRemove.add(labelNbJoueurs);

        labelCurrentPlayer.setFont(bahnschrift);
        labelCurrentPlayer.setBorder(border);
        panelLabelJoueurCourant.add(labelCurrentPlayer,1,0);
        labelNBTour.setFont(bahnschriftBold);
        labelNBTour.setBorder(border);
        panelLabelJoueurCourant.add(labelNBTour,0,0);

        //Listeners
        for(int i = 0; i < Score.MAX_QUILLES + 1; i++){
            JButton b = new JButton("" + i);
            boutons.add(b);
            b.addActionListener(this);
            panelBoutonsQuilles.add(b);
            b.setEnabled(false);
        }
        addPlayer.addActionListener(this);
        removePlayer.addActionListener(this);
        removePlayer.setEnabled(false);

        labelScoreFin.setFont(bahnschriftBold);
        panelAffichageFin.add(labelScoreFin);
        

        //Tableau des scores:
        for(int i = 0;  i < MAX_JOUEUR; i++){
            panelTableauScore[i] = new JPanel();
            for (int j = 0; j < Score.MAX_TURN; j++){
                panelTourScore[i][j] = new ScorePanel(j+1);
                panelTourScore[i][j].setBackground(Color.WHITE);
                panelTourScore[i][j].setForeground(blackColor);
                panelTableauScore[i].add(panelTourScore[i][j], 0, j);
                panelTableauScore[i].setVisible(false);
            }
            finalScores[i] = new JLabel("" + 0);
            panelTableauScore[i].setBackground(whiteColor);
            panelTableauScore[i].setForeground(blackColor);
            panelTableauScore[i].add(finalScores[i],0,Score.MAX_TURN);
            panelTableauScore[i].add(finalScores[i], 0, Score.MAX_TURN);
            panelJoueurScore.add(panelTableauScore[i], i, 0);
            
        }


        this.setContentPane(panelPrincipal);
        panelPrincipal.add(panelBoutonAddRemove, BorderLayout.WEST);
        panelPrincipal.add(panelBoutonsQuilles, BorderLayout.NORTH);
        panelPrincipal.add(panelLabelJoueurCourant, BorderLayout.EAST);
        //panelPrincipal.add(panelScores,BorderLayout.CENTER);
        panelPrincipal.add(panelJoueurScore, BorderLayout.CENTER);
        panelPrincipal.add(panelAffichageFin,BorderLayout.SOUTH);

        panelBoutonAddRemove.setBackground(whiteColor);
        panelBoutonsQuilles.setBackground(whiteColor);
        panelLabelJoueurCourant.setBackground(whiteColor);
        panelJoueurScore.setBackground(whiteColor);
        panelAffichageFin.setBackground(whiteColor);

        panelPrincipal.revalidate();
        
}

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < boutons.size(); i++){
            if(e.getSource().equals(boutons.get(i))){
                panelPrincipal.remove(panelBoutonAddRemove);
                panelPrincipal.remove(panelLabelJoueurCourant);
                panelPrincipal.add(panelLabelJoueurCourant, BorderLayout.WEST);
                labelNbJoueurs.setText("");
                addPlayer.setEnabled(false);
                removePlayer.setEnabled(false);

                int nbQuillesTombees = Integer.valueOf(boutons.get(i).getText());
                Player p = bowling.getPlayers().get(currentPlayer);
                bowling.getPlayers().get(currentPlayer).incrementNbLancer();
                p.getScore().addPoint(nbQuillesTombees, p.getNbTour(), p.getNbLancer());
                
                //Update Scores
                panelTourScore[currentPlayer][p.getNbTour()-1].setScore(nbQuillesTombees, p.getNbLancer());
                for(int j = 0; j< Score.MAX_TURN;j++)
                    panelTourScore[currentPlayer][j].setFinalScore(p.getScore().getScoreTurn(j+1));
                finalScores[currentPlayer].setText("" + p.getScore().getScoreTotal());

                //listLabelScores.get(currentPlayer).setText("Joueur " + (currentPlayer + 1) + " : " + p.getScore().getScoreTotal());

                if(regleT10 > 0){
                    regleT10--;
                    if(regleT10 == 0){
                        hasDoneT10 = true;
                    }
                    else{
                        return;
                    }
                }

                //CAS STRIKE
                if(nbQuillesTombees == Score.MAX_QUILLES){
                    if(p.getNbTour() == Score.MAX_TURN && regleT10 == 0 && !hasDoneT10){
                        regleT10 = 2;
                        return;
                    }
                    p.incrementNbLancer();
                }
                //CAS SPARE
                else if(p.getScore().getScoreTurn(p.getNbTour()) == Score.MAX_QUILLES){
                    regleT10 = 1;
                    resetQuilles();
                    return;
                }

                //CAS NORMAL
                else{
                    //les boutons deviennent invisible pour que la somme soit inférieur ou égale à 10 (on enleve les quilles tombées)
                    for(int j = boutons.size() - 1; j > boutons.size() - i - 1; j--){
                        boutons.get(j).setVisible(false);
                    }
                }
                
                //CHANGEMENT DE JOUEUR
                if(p.getNbLancer() == 2 || hasDoneT10){
                
                    p.resetNbLancer();
                    p.incrementNbTour();
                    labelNBTour.setText("Tour n° " + p.getNbTour());

                    currentPlayer = (currentPlayer + 1)%bowling.getPlayers().size(); //joueur suivant
                    labelCurrentPlayer.setText("Joueur " + (currentPlayer + 1) + ", à toi de jouer !");
                    nbQuillesTombees = 0;
                    
                    //tous les boutons sont de nouveaux visibles (on remet les quilles droites)
                    for(int j = 0; j < boutons.size(); j++){
                        boutons.get(j).setVisible(true);
                    }
                    hasDoneT10 = false;
                }
                if(bowling.getPlayers().get(bowling.getPlayers().size() - 1).getNbTour() > Score.MAX_TURN){
                    //FIN + REJOUER
                    int scoreMax = 0;
                    Player gagnant = null;
                    for(int j = 0; j < bowling.getPlayers().size();j++){
                        if(bowling.getPlayers().get(j).getScore().getScoreTotal() > scoreMax){
                            gagnant = bowling.getPlayers().get(j);
                            scoreMax  = gagnant.getScore().getScoreTotal();
                        }
                    }
                    labelScoreFin.setText(gagnant.getNom() + " a gagné avec " + gagnant.getScore().getScoreTotal() + " points !");
                    for(int j = 0; j < boutons.size(); j++){
                        boutons.get(j).setEnabled(false);
                    }
                }
            }
            
        }
        
        //GESTION DES AJOUTS ET SUPPRESSION DES UTILISATEURS
        if(e.getSource().equals(addPlayer)){
            System.out.println("add player");
            bowling.addNewPlayer("joueur " + (bowling.getPlayers().size() + 1));
            nbJoueurs += 1;
            labelNbJoueurs.setText("Nombre de joueurs : " + nbJoueurs);

            //LABEL SCORES
            JLabel l = new JLabel("Joueur " + nbJoueurs + " : ");
            listLabelScores.add(l);
            panelScores.add(l,nbJoueurs - 1,0);
            removePlayer.setEnabled(true);
            panelTableauScore[nbJoueurs-1].setVisible(true);

            if(nbJoueurs > 0){
                for(int i = 0; i < boutons.size(); i++){
                    boutons.get(i).setEnabled(true);
                    boutons.get(i).setBackground(Color.WHITE);
                    boutons.get(i).setForeground(blackColor);
                }
            }

            labelCurrentPlayer.setText("Joueur " + (currentPlayer + 1) + ", à toi de jouer !");

            if(nbJoueurs == MAX_JOUEUR){
                addPlayer.setEnabled(false);
            }
        }
        if(e.getSource().equals(removePlayer)){
            System.out.println("remove player");
            bowling.removePlayer();
            listLabelScores.get(listLabelScores.size() - 1).setVisible(false);
            panelTableauScore[nbJoueurs-1].setVisible(false);
            panelScores.remove(listLabelScores.get(listLabelScores.size() - 1));
            listLabelScores.remove(listLabelScores.size() - 1);
            nbJoueurs -= 1;
            labelNbJoueurs.setText("Nombre de joueurs : " + nbJoueurs);

            if(nbJoueurs <=0 ){
                for(int i = 0; i < boutons.size(); i++){
                    boutons.get(i).setEnabled(false);
                }
                if(nbJoueurs ==0){
                    removePlayer.setEnabled(false);
                }
            }
            if(nbJoueurs < MAX_JOUEUR){
                addPlayer.setEnabled(true);
            }
        }

    }

    private void resetQuilles(){
        for(int j = 0; j < boutons.size(); j++){
            boutons.get(j).setVisible(true);
        }
    }


    public static void main(String [] args){
        new launch();
     }
}
