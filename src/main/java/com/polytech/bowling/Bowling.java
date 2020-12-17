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
        // .
    }

    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Récupère la liste de joueurs.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getPlayersThatCanPlay(int tour) {
        ArrayList<Player> alivePlayers = new ArrayList<Player>();
        for (Player p : players) {
            if (p.canPlay(tour))
                alivePlayers.add(p);
        }
        return alivePlayers;
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
        do {
            System.out.print("Nom du joueur: ");
            nom = keyboard.nextLine();
            ok = bowling.addNewPlayer(nom);
            System.out.println("\n");
        } while (ok);

        System.out.println("Voici les joueurs:");
        for (Player p : bowling.getPlayers()) {
            System.out.println(p.getNom());
        }
        System.out.print("\n");

        boolean doBreak = false;
        int tour = 0;
        while (true) {
            tour++;
            for (Player p : bowling.getPlayersThatCanPlay(tour)) {
                if (bowling.getPlayersThatCanPlay(tour).isEmpty())
                    doBreak = true;
                else {
                    System.out.println("Tour n°"+tour);
                    p.joue(keyboard);
                }
            }
            if (doBreak)
                break;
        }

        int position = 1;
        for (Player p : bowling.getPlayers()) {
            System.out.println(position + ". " + p.getNom() + " - " + p.getPoints() + "pts");
            position++;
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
            Player p = new Player(name, 0);
            players.add(p);
            return true;
        }
    }

}
