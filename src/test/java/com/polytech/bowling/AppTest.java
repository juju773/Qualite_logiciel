package com.polytech.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testAddPlayer() {
        Bowling bowling = new Bowling();
        assertTrue(bowling.addNewPlayer("Lucas"));
        assertTrue(bowling.addNewPlayer("Julien"));

        assertEquals(2, bowling.getPlayers().size());
        assertEquals(0, bowling.getPlayers().get(0).getScore().getScoreTotal());
        assertEquals(0, bowling.getPlayers().get(1).getScore().getScoreTotal());
        assertEquals("Lucas", bowling.getPlayers().get(0).getNom());
        assertEquals("Julien", bowling.getPlayers().get(1).getNom());
    }
    @Test
    void testNbQuilles(){
        Player p = new Player("test");
        p.getScore().addPoint(4, 1, 1);
        assertEquals(4, p.getScore().getScoreTotal()); 
    }

    //TODO 
    // @Test
    // void testReplayConsole(){
    //     Bowling bowling = new Bowling();
    
    //     Player p1 = new Player("test");
    //     ArrayList<Player> players = new ArrayList<Player>();
    //     players.add(p1);
    //     ConsoleGame.restartGame(new Scanner(System.in),bowling);
    //     assertEquals(1,p1.getNbLancer());
    // }
    
}
