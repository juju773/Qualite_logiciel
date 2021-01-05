package com.polytech.bowling;

import java.util.ArrayList;


/**
 * Classe Bowling.
 */
public class Bowling {

    /**
     * Empty constructor.
     */
    public Bowling() {
        // .
    }

    private ArrayList<Player> players = new ArrayList<>();
    

    /**
     * Récupère la liste de joueurs.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }


    /**
     * Player qui n'ont pas fini le tour
     * @param tour
     * @return
     */
    public ArrayList<Player> getPlayersThatCanPlay() {
        ArrayList<Player> alivePlayers = new ArrayList<Player>();
        for (Player p : players) {
            if (p.canPlay())
                alivePlayers.add(p);
        }
        return alivePlayers;
    }

    public static Bowling getPlayersForGame(ArrayList<Player> players){
        Bowling bowling = new Bowling();
        bowling.players = players;
        return bowling;
    }

    /**
     * Ajoute un nouveau joueur en entrée à la liste de joueurs.
     */
    public boolean addNewPlayer(String name) {
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            return false;
        } else {
            Player p = new Player(name);
            players.add(p);
            return true;
        }
    }

    public boolean removePlayer(){
        players.remove(players.size() - 1);
        return true;
    }

}
