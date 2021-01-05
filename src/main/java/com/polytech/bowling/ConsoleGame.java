package com.polytech.bowling;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleGame {

    public static void game(Scanner keyboard,Bowling bowling){
        System.out.println("Voici les joueurs:");
        for (Player p : bowling.getPlayers()) {
            System.out.println(p.getNom());
        }
        System.out.print("\n");

        int tour = 0;
        while (true) {
            tour++;
            for (Player p : bowling.getPlayersThatCanPlay()) {
                    System.out.println("Tour n°"+tour);
                    p.joue(keyboard);
            }
            if (bowling.getPlayersThatCanPlay().isEmpty())
                break;
        }

        int position = 1;
        for (Player p : bowling.getPlayers()) {
            System.out.println(position + ". " + p.getNom() + " - " + p.getScore().getScoreTotal() + "pts");
            position++;
            p.reset();
        }

        restartGame(keyboard,bowling);
    }

    public static void game(Bowling bowling){
        System.out.println("Voici les joueurs:");
        for (Player p : bowling.getPlayers()) {
            System.out.println(p.getNom());
        }
        System.out.print("\n");
        Random r = new Random();
        int tour = 0;
        while (true) {
            tour++;
            for (Player p : bowling.getPlayersThatCanPlay()) {
                    System.out.println("Tour n°"+tour);
                    
                    int randint = Math.abs(r.nextInt()) % 11;
                    p.joue(randint);
            }
            if (bowling.getPlayersThatCanPlay().isEmpty())
                break;
        }

        int position = 1;
        for (Player p : bowling.getPlayers()) {
            System.out.println(position + ". " + p.getNom() + " - " + p.getScore().getScoreTotal() + "pts");
            position++;
            p.reset();
        }

        restartGame(3,bowling, bowling.getPlayers());
    }

    /**
     * Fonction main du bowling en mode console
     */
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        game(keyboard,getPlayersForGame(keyboard));
        keyboard.close();
    }

    
    public static Bowling getPlayersForGame(Scanner keyboard){
        System.out.println("Veuillez entrer le nom des joueurs à ajouter.");
        Bowling bowling = new Bowling();
        String nom;
        boolean ok;
        do {
            System.out.print("Nom du joueur: ");
            nom = keyboard.nextLine();
            ok = bowling.addNewPlayer(nom);
            System.out.println("\n");
        } while (ok);
        return bowling;
    }

    public static void restartGame(Scanner keyboard, Bowling bowling){
        int choix;
        do {
            System.out.print("Voulez vous rejouer ?"+"\n1 - Oui avec les mêmes joueurs\n2 - Oui avec de nouveaux joueurs\n3 - Non\n> ");
            choix = keyboard.nextInt();
            switch(choix){
                case 1:
                    game(keyboard,Bowling.getPlayersForGame(bowling.getPlayers()));
                break;
                case 2:
                    keyboard.nextLine();
                    game(keyboard,getPlayersForGame(keyboard));
                break;
                case 3:
                    System.out.println("Bien joué, au revoir!");
                break;
                default:
                break;
            }
            System.out.println("\n");
        } while (choix<1 || choix>3);
    }

    public static void restartGame(int choix, Bowling bowling, ArrayList<Player> players){
        switch(choix){
            case 1:
                game(Bowling.getPlayersForGame(bowling.getPlayers()));
            break;
            case 2:
                game(Bowling.getPlayersForGame(players));
            break;
            case 3:
                System.out.println("Bien joué, au revoir!");
            break;
            default:
            break;
        }
    }
}
