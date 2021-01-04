
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

    List<JLabel> listLabelJoueurs = new ArrayList<JLabel>();

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


        //Listeners
        for(int i = 0; i < 11; i++){
            JButton b = new JButton("" + i);
            boutons.add(b);
            b.addActionListener(this);
            panelBoutonsQuilles.add(b);
        }
        addPlayer.addActionListener(this);
        removePlayer.addActionListener(this);


        
        this.setContentPane(panelPrincipal);
        panelPrincipal.add(panelBoutonAddRemove, BorderLayout.WEST);
        panelPrincipal.add(panelBoutonsQuilles, BorderLayout.CENTER);
}

   public static void main(String [] args){
      new launch();
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < boutons.size() - 1; i++){
            if(e.getSource().equals(boutons.get(i))){
                

                int nbQuillesTombees = Integer.valueOf(boutons.get(i).getText());
                p.calculatePoint(nbQuillesTombees);
                //les boutons deviennent invisible pour que la somme soit inférieur ou égale à 10
                for(int j = boutons.size() - 1; j > boutons.size() - i - 1; j--){
                    boutons.get(j).setVisible(false);
                }
            }
        }
        
        //LES LABELS NE SAFFICHENT PAS ENCORE
        if(e.getSource().equals(addPlayer)){
            System.out.println("add player");
            bowling.addNewPlayer("joueur " + bowling.getPlayers().size() + 1);
            JLabel l = new JLabel("joueur " + bowling.getPlayers().size() + 1);
            l.setVisible(true);
            panelBoutonsQuilles.add(l);
            listLabelJoueurs.add(l);
        }
        if(e.getSource().equals(removePlayer)){
            System.out.println("remove player");
            bowling.removePlayer();
            panelBoutonsQuilles.remove(listLabelJoueurs.get(listLabelJoueurs.size() - 1));
            listLabelJoueurs.remove(listLabelJoueurs.size() - 1);
        }
    }
}
