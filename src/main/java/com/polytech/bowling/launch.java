
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

    JLabel currentPlayer = new JLabel("");

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

        panelLabelJoueurCourant.add(currentPlayer);


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
                
                //TODO WARNING NBTOUR !!!!!!!!!!!!!!!!!!!!!!!!!
                p.calculatePoint(nbQuillesTombees, 1); 
                //------------------------------------------------------
                
                //les boutons deviennent invisible pour que la somme soit inférieur ou égale à 10
                for(int j = boutons.size() - 1; j > boutons.size() - i - 1; j--){
                    boutons.get(j).setVisible(false);
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

            currentPlayer.setText("Joueur 1, à toi de jouer !");
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
