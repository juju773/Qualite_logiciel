package com.polytech.bowling;

/**
 * Classe Player.
 */
public class Player {
    private String nom;
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
