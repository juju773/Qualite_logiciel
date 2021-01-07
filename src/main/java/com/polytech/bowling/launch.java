
package com.polytech.bowling;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class launch extends JFrame implements ActionListener{


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
    JPanel panelScores = new JPanel(new GridLayout(5,1));

    List<JLabel> listLabelScores = new ArrayList<JLabel>();


    private int nbJoueurs = 0;
    JLabel labelNbJoueurs = new JLabel("Nombre de joueurs : 0");
    JLabel labelNBTour = new JLabel("Tour n° : ");

    JLabel labelCurrentPlayer = new JLabel("");
    int currentPlayer = 0;

   public launch() {
        super("Bowling !");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(600,600);
        setVisible(true);

        panelBoutonAddRemove.add(addPlayer);
        panelBoutonAddRemove.add(removePlayer);
        panelBoutonAddRemove.add(labelNbJoueurs);

        panelLabelJoueurCourant.add(labelCurrentPlayer,1,0);
        panelLabelJoueurCourant.add(labelNBTour,0,0);


        //Listeners
        for(int i = 0; i < 11; i++){
            JButton b = new JButton("" + i);
            boutons.add(b);
            b.addActionListener(this);
            panelBoutonsQuilles.add(b);
            b.setEnabled(false);
        }
        addPlayer.addActionListener(this);
        removePlayer.addActionListener(this);
        removePlayer.setEnabled(false);

        
        this.setContentPane(panelPrincipal);
        panelPrincipal.add(panelBoutonAddRemove, BorderLayout.WEST);
        panelPrincipal.add(panelBoutonsQuilles, BorderLayout.NORTH);
        panelPrincipal.add(panelLabelJoueurCourant, BorderLayout.EAST);
        panelPrincipal.add(panelScores,BorderLayout.CENTER);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < boutons.size(); i++){
            if(e.getSource().equals(boutons.get(i))){
                addPlayer.setEnabled(false);
                removePlayer.setEnabled(false);

                int nbQuillesTombees = Integer.valueOf(boutons.get(i).getText());
                Player p = bowling.getPlayers().get(currentPlayer);
                bowling.getPlayers().get(currentPlayer).incrementNbLancer();
                p.getScore().addPoint(nbQuillesTombees, p.getNbTour(), p.getNbLancer());
                listLabelScores.get(currentPlayer).setText("Joueur " + (currentPlayer + 1) + " : " + p.getScore().getScoreTotal());


                if(nbQuillesTombees == 10){
                    bowling.getPlayers().get(currentPlayer).incrementNbLancer();
                }
                else{
                    //les boutons deviennent invisible pour que la somme soit inférieur ou égale à 10 (on enleve les quilles tombées)
                    for(int j = boutons.size() - 1; j > boutons.size() - i - 1; j--){
                        boutons.get(j).setVisible(false);
                    }
                }
                
                //CHANGEMENT DE JOUEUR
                if(bowling.getPlayers().get(currentPlayer).getNbLancer() == 2){
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
                }
                if(bowling.getPlayers().get(0).getNbTour() == Player.MAX_NB_TURN){
                    //FIN + REJOUER
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

            if(nbJoueurs > 0){
                for(int i = 0; i < boutons.size(); i++){
                    boutons.get(i).setEnabled(true);
                }
            }

            labelCurrentPlayer.setText("Joueur " + (currentPlayer + 1) + ", à toi de jouer !");

            if(nbJoueurs == 5){
                addPlayer.setEnabled(false);
            }
        }
        if(e.getSource().equals(removePlayer)){
            System.out.println("remove player");
            bowling.removePlayer();
            listLabelScores.get(listLabelScores.size() - 1).setVisible(false);
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
            if(nbJoueurs < 5){
                addPlayer.setEnabled(true);
            }
        }

    }


    public static void main(String [] args){
        new launch();
     }
}
