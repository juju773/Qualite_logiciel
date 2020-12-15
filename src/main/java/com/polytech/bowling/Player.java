package com.polytech.bowling;

import java.util.Scanner;

/**
 * Classe Player.
 */
public class Player {
    private static int maxTours = 10;
    private String nom;
    private int nbLancer;
    private int scoreDouble;
    private int nbTour;
    private int maxNbTour = maxTours;
    private int pointsTotal;
    private int nbQuillesTour; // Nombre de quilles tombées ce tour

    public Player(String pNom, int pPoints) {
        nom = pNom;
        pointsTotal = pPoints;
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
        return nbTour;
    }

    /**
     * Récupère le nombre de tours max du joueur.
     */
    public int getNbMaxTour() {
        return maxNbTour;
    }

    /**
     * Récupère le nombre de point du joueur.
     */
    public int getPoints() {
        return pointsTotal;
    }

    /**
     * Incrémente le nombre de lancers du joueur.
     */
    public int incrementLancer() {
        if (nbLancer == 2) {
            nbTour++;
            nbLancer = 0;
            nbQuillesTour = 0;
        } else {
            nbLancer++;
        }
        return nbLancer;
    }

    /**
     * Détermine le nombre de pointsTotal du joueur ce tour.
     */
    public void setNombreQuilles(Scanner keyboard) {
        System.out.print("Nombre de quilles tombées: ");

        int nbQuilles = keyboard.nextInt();

        if (nbQuilles > 10) {
            nbQuilles = 10;
        } else if (nbQuilles < 0) {
            nbQuilles = 0;
        }

        if (scoreDouble > 0) {
            nbQuilles *= 2;
            scoreDouble--;
        }
        addPoints(nbQuilles); // On ajoute le nombre de quilles
        nbQuillesTour += nbQuilles;
        if (nbQuilles == 10 && nbLancer == 1) { // Strike
            System.out.println("Strike!");
            scoreDouble += 2;
            nbLancer = 2; // Pour passer directement au prochain tour
            if (nbTour == 10) { // Règle du 10e tour
                maxNbTour += 2;
            }
        } else if (nbQuillesTour == 10 && nbLancer == 2) { // Spare
            System.out.println("Spare!");
            scoreDouble++;
            if (nbTour == 10) { // Règle du 10e tour
                maxNbTour++;
            }
        }
        incrementLancer();
    }

    public void setNombreQuilles(int nbQuilles) {
        if (nbQuilles > 10) {
            nbQuilles = 10;
        } else if (nbQuilles < 0) {
            nbQuilles = 0;
        }

        if (scoreDouble > 0) {
            nbQuilles *= 2;
            scoreDouble--;
        }
        addPoints(nbQuilles); // On ajoute le nombre de quilles
        nbQuillesTour += nbQuilles;
        if (nbQuilles == 10 && nbLancer == 1) { // Strike
            System.out.println("Strike!");
            scoreDouble += 2;
            nbLancer = 2; // Pour passer directement au prochain tour
            if (nbTour == 10) { // Règle du 10e tour
                maxNbTour += 2;
            }
        } else if (nbQuillesTour == 10 && nbLancer == 2) { // Spare
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
     * Ajoute une valeur aux pointsTotal du joueur.
     */
    public void addPoints(int pts) {
        this.pointsTotal += pts;
    }
}
