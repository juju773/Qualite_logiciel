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
     * Récupère le nombre de point du joueur.
     */
    public int getPoints() {
        return pointsTotal;
    }

    public boolean canPlay() {
        return nbTour < maxNbTour;
    }

    /**
     * Joue avec la console
     * @param sc
     */
    public void joue(Scanner sc) {
        if (getNbTour() <= maxNbTour) {
            int nbQuillesLancer = lancer(sc);
            int points = calculatePoint(nbQuillesLancer);
            addPoints(points); // On ajoute le nombre de quilles
            printPoints();

            if(nbTour == 10 && nbQuillesLancer == 10 && nbLancer == 1){
                addPoints(calculatePoint(lancer(sc)));
                printPoints();
                addPoints(calculatePoint(lancer(sc)));
                printPoints();
            }
            else if(nbTour == 10 && nbQuillesLancer == 10 && nbLancer == 2){
                addPoints(calculatePoint(lancer(sc)));
                printPoints();
            }

            if (nbQuillesLancer != 10 && nbLancer == 1){
                addPoints(calculatePoint(lancer(sc)));
                printPoints();
            }
                
            
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
     * Détermine le nombre de pointsTotal du joueur ce tour.
     */
    public int calculatePoint(int nbQuillesLancer) {

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
        if (nbQuillesLancer == 10 && nbLancer == 1) { // Strike
            strike();
        } else if (nbQuillesTour == 10 && nbLancer == 2) { // Spare
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

    public void printPoints(){
        System.out.println(nom + " a " + pointsTotal + " points.");
    }
}
