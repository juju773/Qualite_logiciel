
package com.polytech.bowling;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class launch extends JFrame implements ActionListener{


    List<JButton> boutons = new ArrayList<JButton>();
    Bowling bowling = new Bowling();
    Player p = new Player("test");

    JButton addPlayer = new JButton("+");
    JButton removePlayer = new JButton("-");

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

    //Init composants de la vue
    JPanel panel = new JPanel(); 

    panel.add(addPlayer);
    panel.add(removePlayer);


    //Listeners
    for(int i = 0; i < 11; i++){
        JButton b = new JButton("" + i);
        boutons.add(b);
        b.addActionListener(this);
        panel.add(b);
    }
    addPlayer.addActionListener(this);
    removePlayer.addActionListener(this);

    this.setContentPane(panel);
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
        
        if(e.getSource().equals(addPlayer)){
            bowling.addNewPlayer("joueur " + bowling.getListPlayers().size() + 1);
        }
        if(e.getSource().equals(removePlayer)){
            bowling.removePlayer();
        }

    }
}
