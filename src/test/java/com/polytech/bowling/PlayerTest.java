package com.polytech.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlayerTest {

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
