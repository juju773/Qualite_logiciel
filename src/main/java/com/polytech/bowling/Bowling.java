package com.polytech.bowling;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Bowling.
 */
public class Bowling {

    /**
    * Empty constructor.
    */
    public Bowling() { 
        //.
     }

    private ArrayList<Player> players = new ArrayList<>();



    /**
     * Récupère la liste de joueurs.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Fonction main.
     */
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Veuillez entrer le nom des joueurs à ajouter.");
        Bowling bowling = new Bowling();

        String nom;
        boolean ok;
        do{
            System.out.print("Nom du joueur: ");
            nom = keyboard.nextLine();
            ok = bowling.addNewPlayer(nom);
            System.out.println("\n");
        }while(ok);

        System.out.println("Voici les joueurs:");
        for (Player p : bowling.getPlayers()) {
            System.out.println(p.getNom());
        }

        for (Player p : bowling.getPlayers()) {
            System.out.println(p.getNom() + ", à toi de jouer!");
            if (p.getNbTour() <= p.getNbMaxTour()) {
                System.out.print("Nombre de quilles tombées: ");
                int nbQuilles = keyboard.nextInt();
                p.setNombreQuilles(nbQuilles);
            }
        }
        keyboard.close();
    }

    /**
     * Ajoute un nouveau joueur en entrée à la liste de joueurs.
     */
    public boolean addNewPlayer(String name) {
        if ("".equals(name)) {
            return false;
        } else {
            Player p = new Player(name,0);
            players.add(p);
            return true;
        }
    }

}
