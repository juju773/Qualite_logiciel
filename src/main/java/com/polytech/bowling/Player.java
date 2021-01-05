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
    private int scoreDouble;
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
        scoreDouble = 1;
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
        scoreDouble = 1;
        listStrike.clear();
        listSpare.clear();
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
    /**
     * Récupère le nombre de point du joueur.
     */
    public int getPoints() {
        return pointsTotal;
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
            score.addPoint(nbQuillesLancer, nbTour, 1);

            // int points = calculatePoint(nbQuillesLancer, nbLancer);
            // addPoints(points); // On ajoute le nombre de quilles

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
                System.out.println("Erreur: Veuillez renseigner une somme comprise entre 0 et 10");
        }while (nbQuillesTour+nbQuillesLancer>10 || nbQuillesLancer<0);
        nbLancer++;
        return nbQuillesLancer;
    }

    /**
     * Détermine le nombre de points total à ce tour du joueur ce tour.
     */
    public int calculatePoint(int nbQuillesLancer, int numLancer) {

        int pointsTour = nbQuillesLancer;
        scoreDouble = 1;
        for(int i = 0; i < listStrike.size(); i++){
            if (((Strike) listStrike.get(i)).getTTL() > 0){
                scoreDouble += 1;
                ((Strike) listStrike.get(i)).decrement();
            }
        } 
        for(int i = 0; i < listSpare.size(); i++){
            if (((Spare) listSpare.get(i)).getTTL() > 0){
                scoreDouble += 1;
                ((Spare) listSpare.get(i)).decrement();
            }
        } 
        pointsTour *= scoreDouble;
                
        //Traitement Spare Strike 
        //-------------------------------------
        nbQuillesTour += nbQuillesLancer;
        if (nbQuillesLancer == 10 && numLancer == 1) { // Strike
            strike();
        } else if (nbQuillesTour == 10 && numLancer == 2) { // Spare
            spare();
        }
        
        return pointsTour;
    }

    public int getNbQuillesTour() {
        return nbQuillesTour;
    }

    public void strike() {
        System.out.println("Strike!");
        Strike s = new Strike();
        listStrike.add(s);
        //Spécifique au 10e tour
        nbLancer = 0;
        nbQuillesTour = 0;
    }

    public void spare() {
        System.out.println("Spare!");
        Spare s = new Spare();
        listSpare.add(s);
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

    public void printPoints(){
        System.out.println(nom + " a " + score.getScoreTotal() + " points.");
    }
}