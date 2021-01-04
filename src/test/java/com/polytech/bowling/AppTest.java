package com.polytech.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(0, bowling.getPlayers().get(0).getPoints());
        assertEquals(0, bowling.getPlayers().get(1).getPoints());
        assertEquals("Lucas", bowling.getPlayers().get(0).getNom());
        assertEquals("Julien", bowling.getPlayers().get(1).getNom());
    }
    @Test
    void testNbQuilles(){
        Player p = new Player("test");
        p.calculatePoint(4,1);
        assertEquals(4, p.getNbQuillesTour());
    }

    @Test
    void testCalculatePoints(){
        Player p = new Player("test"); 
        Player p2 = new Player("test"); 
        //Test des strike en chaines
        p.addPoints(p.calculatePoint(10,1));
        assertEquals(10, p.getPoints());
        p.addPoints(p.calculatePoint(10,1));
        assertEquals(30, p.getPoints());
        p.addPoints(p.calculatePoint(10,1));
        assertEquals(60, p.getPoints());

        //Test des spare en chaines
        p2.addPoints(p2.calculatePoint(5,1));
        p2.addPoints(p2.calculatePoint(5,2)); //1er spare
        p2.addPoints(p2.calculatePoint(5,1));
        assertEquals(20,p2.getPoints());
        p2.addPoints(p2.calculatePoint(5,2)); //2eme spare
        assertEquals(25,p2.getPoints());
    }
}
