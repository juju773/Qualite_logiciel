package com.polytech.bowling;

import java.util.Scanner;

/**
 * Classe Player.
 */
public class Player {
    private String nom;
    private int nbLancer;
    private int scoreDouble = 0;
    private int nbTour;
    private int maxNbTour = 10;
    private int points;

    public Player(String pNom, int pPoints) {
        nom = pNom;
        points = pPoints;
    }

    /**
     * Récupère le nom du joueur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le nombre de lancer ce tour.
     */
    public int getNbLancer() {
        return nbLancer;
    }

    /**
     * Récupère le nombre de tours du joueur.
     */
    public int getNbTour() {
        return nbLancer;
    }

    /**
     * Incrémente le nombre de lancers du joueur.
     */
    public int incrementLancer() {
        if (nbLancer == 2) {
            nbTour++;
            nbLancer = 0;
        } else {
            nbLancer++;
        }
        return nbLancer;
    }

    /**
     * Détermine le nombre de points du joueur ce tour.
     */
    public void setNombreQuilles() {
        Scanner keyboard = new Scanner(System.in);
        int nbQuilles;

        System.out.print("Nombre de quilles tombées: ");

        nbQuilles = keyboard.nextInt();
        keyboard.close();

        if (scoreDouble > 0) {
            nbQuilles *= 2;
            scoreDouble--;
        }
        addPoints(nbQuilles); // On ajoute le nombre de quilles
        if (nbQuilles == 10 && nbLancer == 1) { // Strike
            System.out.println("Strike!");
            scoreDouble += 2;
            nbLancer = 2; // Pour passer directement au prochain tour
            if (nbTour == 10) { // Règle du 10e tour
                maxNbTour += 2;
            }
        } else if (nbQuilles == 10 && nbLancer == 2) { // Spare
            System.out.println("Spare!");
            scoreDouble++;
            if (nbTour == 10) { // Règle du 10e tour
                maxNbTour++;
            }
        }
        incrementLancer();
    }

    /**
     * Définit le nom du joueur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère les points du joueur.
     */
    public String getPoints() {
        return nom;
    }

    /**
     * Ajoute une valeur aux points du joueur.
     */
    public void addPoints(int pts) {
        this.points += pts;
    }
}
