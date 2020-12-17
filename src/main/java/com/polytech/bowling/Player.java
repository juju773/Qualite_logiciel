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
    private int nbStrike;
    private List<Strike> listStrike;
    private List<Spare> listSpare;
    private Object object;

    public Player(String pNom, int pPoints) {
        nom = pNom;
        pointsTotal = pPoints;
        nbTour = 0;
        maxNbTour = 10;
        nbLancer = 1;
        nbQuillesTour = 0;
        nbStrike = 0;
        scoreDouble = 1;
        listStrike = new ArrayList<Strike>();
        listSpare = new ArrayList<Spare>();
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

    public boolean canPlay() {
        if (nbTour < maxNbTour)
            return true;
        return false;
    }

    public void joue(Scanner sc) {
        System.out.println(nom + ", à toi de jouer!");
        if (getNbTour() <= getNbMaxTour()) {
            int nbQuillesLancer=0;

            do{
                System.out.print("Nombre de quilles tombées: ");
                nbQuillesLancer = sc.nextInt();
                if(nbQuillesTour+nbQuillesLancer>10 || nbQuillesLancer<0)
                    System.out.println("Erreur: Veuillez renseigner une somme comprise entre 0 et 10");
            }while (nbQuillesTour+nbQuillesLancer>10 || nbQuillesLancer<0);
            
            calculatePoint(nbQuillesLancer);
            System.out.println(nom + " a " + getPoints() + " points.");
            if (nbQuillesLancer != 10 && nbLancer == 2)
                joue(sc);
        }
        System.out.println("\n");
    }

    /**
     * Incrémente le nombre de lancers du joueur.
     */
    public void incrementLancer() {
        if (nbLancer == 2) {
            nbTour++;
            nbLancer = 1;
            nbQuillesTour = 0;
        } else {
            nbLancer++;
        }
    }

    /**
     * Détermine le nombre de pointsTotal du joueur ce tour.
     */
    public void calculatePoint(int nbQuillesLancer) {
        if (nbQuillesLancer > 10) {
            nbQuillesLancer = 10;
        } else if (nbQuillesLancer < 0) {
            nbQuillesLancer = 0;
        }

        int pointsTour = nbQuillesLancer;
        if(!listStrike.isEmpty()){
            scoreDouble = 1;
            for(int i = 0; i < listStrike.size(); i++){
                if (((Strike) listStrike.get(i)).getTTL() > 0){
                    scoreDouble += 1;
                }
                ((Strike) listStrike.get(i)).decrement();
            } 
        }
        if(!listSpare.isEmpty()){
            for(int i = 0; i < listSpare.size(); i++){
                if (((Spare) listSpare.get(i)).getTTL() > 0){
                    scoreDouble += 1;
                }
                ((Spare) listSpare.get(i)).decrement();
            } 
        }
        pointsTour *= scoreDouble;
        

        
        addPoints(pointsTour); // On ajoute le nombre de quilles

        
        //Traitement Spare Strike 
        //-------------------------------------
        nbQuillesTour += nbQuillesLancer;
        if (nbQuillesLancer == 10 && nbLancer == 1) { // Strike
            strike();
        } else if (nbQuillesTour == 10 && nbLancer == 2) { // Spare
            spare();
        }
        incrementLancer();
    }

    public int getNbQuillesTour() {
        return nbQuillesTour;
    }

    public void strike() {
        System.out.println("Strike!");
        Strike s = new Strike();
        listStrike.add(s);
        nbLancer = 2; // Pour passer directement au prochain tour
        if (nbTour == 10) { // Règle du 10e tour
            maxNbTour += 2;
        }
    }

    public void spare() {
        System.out.println("Spare!");
        Spare s = new Spare();
        listSpare.add(s);
        if (nbTour == 10) { // Règle du 10e tour
            maxNbTour++;
        }
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
