package com.polytech.bowling;

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

    public Player(String pNom, int pPoints) {
        nom = pNom;
        pointsTotal = pPoints;
        nbTour = 0;
        maxNbTour = 10;
        nbLancer = 1;
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

    public boolean canPlay(int tour){
        if (getNbTour() <= tour)
            return true;
        return false;
    }


    public boolean joue(Scanner sc){
        System.out.println(getNom() + ", à toi de jouer!");
        if (getNbTour() <= getNbMaxTour()) {
            System.out.print("Nombre de quilles tombées: ");
            int nbQuilles = sc.nextInt();
            setNombreQuilles(nbQuilles);
            System.out.println(getNom()+" a "+getPoints()+" points.");
            return true;
        }else{
            return false;
        }
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

    public int getNbQuillesTour(){
        return nbQuillesTour;
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
