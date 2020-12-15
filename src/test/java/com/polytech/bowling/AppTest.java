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





}
