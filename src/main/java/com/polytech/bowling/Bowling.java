package com.polytech.bowling;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Bowling.
 */
public class Bowling {

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
        System.out.println("Veuillez entrer le nom des joueurs à ajouter.");
        Bowling bowling = new Bowling();

        while (bowling.addNewPlayer()) {
            System.out.println("\n");
        }
        System.out.println("Voici les joueurs:");
        for (Player p : bowling.getPlayers()) {
            System.out.println(p.getNom());
        }
    }

    /**
     * Ajoute un nouveau joueur en entrée à la liste de joueurs.
     */
    public boolean addNewPlayer() {
        Scanner keyboard = new Scanner(System.in);
        String nom;

        System.out.print("Nom du joueur: ");

        nom = keyboard.nextLine();

        if ("".equals(nom)) {
            keyboard.close();
            return false;
        } else {
            players.add(new Player(nom, 0));
            return true;
        }
    }

}
