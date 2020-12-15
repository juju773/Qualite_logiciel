package com.polytech.bowling;

public class Player {
    private String nom;
    private int points;

    public Player(String pNom, int pPoints) {
        nom = pNom;
        points = pPoints;
    }

    public String getName() {
        return nom;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPoints(){
        return nom;
    }

    public void setPoints(int pts){
        this.points = pts;
    }
}
