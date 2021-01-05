
package com.polytech.bowling;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
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
    JPanel panelLabelJoueurCourant = new JPanel();


    private int nbJoueurs = 0;
    JLabel labelNbJoueurs = new JLabel("Nombre de joueurs : 0");

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
        setSize(800,600);
        setVisible(true);

        panelBoutonAddRemove.add(addPlayer);
        panelBoutonAddRemove.add(removePlayer);
        panelBoutonAddRemove.add(labelNbJoueurs);

        panelLabelJoueurCourant.add(labelCurrentPlayer);


        //Listeners
        for(int i = 0; i < 11; i++){
            JButton b = new JButton("" + i);
            b.setSize(50, 50);
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
        panelPrincipal.add(panelLabelJoueurCourant, BorderLayout.CENTER);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < boutons.size(); i++){
            if(e.getSource().equals(boutons.get(i))){
                addPlayer.setEnabled(false);
                removePlayer.setEnabled(false);


                int nbQuillesTombees = Integer.valueOf(boutons.get(i).getText());
                bowling.getPlayers().get(currentPlayer).addPoints(bowling.getPlayers().get(currentPlayer).calculatePoint(nbQuillesTombees, 1)); 
                bowling.getPlayers().get(currentPlayer).incrementNbLancer();
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
                    bowling.getPlayers().get(currentPlayer).resetNbLancer();
                    bowling.getPlayers().get(currentPlayer).incrementNbTour();

                    currentPlayer = (currentPlayer + 1)%bowling.getPlayers().size(); //joueur suivant
                    labelCurrentPlayer.setText("Joueur " + (currentPlayer + 1) + ", à toi de jouer !");
                    nbQuillesTombees = 0;
                    //tous les boutons sont de nouveaux visibles (on remet les quilles droites)
                    for(int j = 0; j < boutons.size(); j++){
                        boutons.get(j).setVisible(true);
                    }
                }
            }
        }
        
        //GESTION DES AJOUTS ET SUPPRESSION DES UTILISATEURS
        if(e.getSource().equals(addPlayer)){
            System.out.println("add player");
            bowling.addNewPlayer("joueur " + bowling.getPlayers().size() + 1);
            nbJoueurs += 1;
            labelNbJoueurs.setText("Nombre de joueurs : " + nbJoueurs);

            removePlayer.setEnabled(true);

            if(nbJoueurs > 0){
                for(int i = 0; i < boutons.size(); i++){
                    boutons.get(i).setEnabled(true);
                }
            }

            labelCurrentPlayer.setText("Joueur " + (currentPlayer + 1) + ", à toi de jouer !");
        }
        if(e.getSource().equals(removePlayer)){
            System.out.println("remove player");
            bowling.removePlayer();
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
        }
    }


    public static void main(String [] args){
        new launch();
     }
}
