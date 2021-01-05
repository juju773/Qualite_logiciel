package com.polytech.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe Player.
 */
public class Player {
    private String nom;
    private int nbLancer;
    private int nbTour;
    private int maxNbTour;
    private int pointsTotal;
    private int nbQuillesTour; // Nombre de quilles tombées ce tour
    private List<Strike> listStrike;
    private List<Spare> listSpare;
    private Score score;

    public Player(String pNom) {
        nom = pNom;
        pointsTotal = 0;
        nbTour = 0;
        maxNbTour = 10;
        nbLancer = 0;
        nbQuillesTour = 0;
        listStrike = new ArrayList<Strike>();
        listSpare = new ArrayList<Spare>();
        score = new Score();
    }

    public void reset(){
        pointsTotal = 0;
        nbTour = 0;
        maxNbTour = 10;
        nbLancer = 1;
        nbQuillesTour = 0;
        listStrike.clear();
        listSpare.clear();
        score = new Score();
    }

    /**
     * Récupère le nombre de tours max du joueur.
     */
    public int getNbMaxTour() {
        return maxNbTour;
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
    public void incrementNbLancer() {
        nbLancer++;
    }
    public void resetNbLancer(){
        nbLancer = 0;
    }

    /**
     * Récupère le nombre de tours du joueur.
     */
    public int getNbTour() {
        return nbTour;
    }
    public void incrementNbTour(){
        nbTour++;
    }
    public boolean canPlay() {
        return nbTour < maxNbTour;
    }

    public Score getScore(){
        return score;
    }

    /**
     * Joue avec la console
     * @param sc
     */
    public void joue(Scanner sc) {
        if (getNbTour() <= maxNbTour) {
            int nbQuillesLancer = lancer(sc);
            nbQuillesTour = nbQuillesLancer;
            score.addPoint(nbQuillesLancer, nbTour, 1);

            int nbQuillesLancer2 = -1;
            //2e Lancer
            if (nbQuillesLancer != 10 && nbLancer == 1){
                nbQuillesLancer2 = lancer(sc);
                score.addPoint(nbQuillesLancer2, nbTour, 2);
            }
            //Règle 10e lancer Strike
            if(nbTour == 9 && nbQuillesLancer == 10 && nbLancer != 2){
                nbQuillesTour = 0;
                nbLancer = 0;
                score.addPoint(lancer(sc), nbTour, 1);
                score.addPoint(lancer(sc), nbTour, 2);
            }
            //Règle 10e lancer Spare
            else if(nbTour == 9 && (nbQuillesLancer + nbQuillesLancer2) == 10 && nbLancer == 2){
                nbQuillesTour = 0;
                nbLancer = 0;
                score.addPoint(lancer(sc), nbTour, 1);
            }
            printPoints();
        }
        nbTour++;
        nbQuillesTour = 0;
        nbLancer = 0;
        System.out.println("\n");
    }

        public void joue(int nbQuillesLancer) {
        if (getNbTour() <= maxNbTour) {
            nbQuillesLancer = lancer(nbQuillesLancer);
            score.addPoint(nbQuillesLancer, nbTour, 1);

            int nbQuillesLancer2 = -1;
            //2e Lancer
            if (nbQuillesLancer != 10 && nbLancer == 1){
                nbQuillesLancer2 = lancer(nbQuillesLancer);
                score.addPoint(nbQuillesLancer2, nbTour, 2);
            }
            //Règle 10e lancer Strike
            if(nbTour == 9 && nbQuillesLancer == 10 && nbLancer != 2){
                nbQuillesTour = 0;
                nbLancer = 0;
                score.addPoint(lancer(nbQuillesLancer), nbTour, 1);
                score.addPoint(lancer(nbQuillesLancer), nbTour, 2);
            }
            //Règle 10e lancer Spare
            else if(nbTour == 9 && (nbQuillesLancer + nbQuillesLancer2) == 10 && nbLancer == 2){
                nbQuillesTour = 0;
                nbLancer = 0;
                score.addPoint(lancer(nbQuillesLancer), nbTour, 1);
            }
            printPoints();
        }
        nbTour++;
        nbQuillesTour = 0;
        nbLancer = 0;
        System.out.println("\n");
    }

    /**
     * Lancer
     * @param sc
     * @return
    */
    public int lancer(Scanner sc){
        System.out.println(nom + ", à toi de jouer!");
        int nbQuillesLancer=0;
        do{
            System.out.print("Nombre de quilles tombées: ");
            nbQuillesLancer = sc.nextInt();
            if(nbQuillesTour+nbQuillesLancer>10 || nbQuillesLancer<0)
                System.out.println("Erreur: Veuillez renseigner un nombre compris entre 0 et " + (10 - nbQuillesTour) );
        }while (nbQuillesTour+nbQuillesLancer>10 || nbQuillesLancer<0);
        nbLancer++;
        return nbQuillesLancer;
    }

    public int lancer(int nbQuillesLancer){
        if(nbQuillesLancer<0)
            nbQuillesLancer=0;
        else if(nbQuillesTour+nbQuillesLancer>10)
            nbQuillesLancer = 10-nbQuillesTour;

        nbLancer++;
        return nbQuillesLancer;
    }

    /**
     * Définit le nom du joueur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void printPoints(){
        System.out.println(nom + " a " + score.getScoreTotal() + " points.");
    }
}