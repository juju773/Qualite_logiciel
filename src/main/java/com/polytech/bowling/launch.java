
package com.polytech.bowling;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class launch extends JFrame implements ActionListener{


    List<JButton> boutons = new ArrayList<JButton>();

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

      JPanel panel = new JPanel(); 

        for(int i = 0; i < 10; i++){
          JButton b = new JButton("" + i);
          boutons.add(b);
          b.addActionListener(this);
          panel.add(b);
        }
      this.setContentPane(panel);
   }

   public static void main(String [] args){
      new launch();
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 10; i++){
            if(e.getSource().equals(boutons.get(i))){
                System.out.println(boutons.get(i).getText());
            }
        }
        
    }
}
