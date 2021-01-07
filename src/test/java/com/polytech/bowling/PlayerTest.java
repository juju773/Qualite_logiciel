package com.polytech.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {

    @Test
    void testCalculatePoints(){
        Player p = new Player("test"); 
        Player p2 = new Player("test"); 
        //Test des strike en chaines
        p.getScore().addPoint(10, 1, 1);
        p.getScore().addPoint(10, 2, 1);
        p.getScore().addPoint(10, 3, 1);
        p.getScore().addPoint(10, 4, 1);
        p.getScore().addPoint(10, 5, 1); //T5
        p.getScore().addPoint(10, 6, 1);
        p.getScore().addPoint(10, 7, 1);
        p.getScore().addPoint(10, 8, 1);
        p.getScore().addPoint(10, 9, 1);
        p.getScore().addPoint(10, 10, 1); //T10
        p.getScore().addPoint(10, 10, 1); //T10 bis
        p.getScore().addPoint(10, 10, 1); //T10 ter
        assertEquals(300, p.getScore().getScoreTotal());

        p2.getScore().addPoint(9, 1, 1);
        p2.getScore().addPoint(1, 1, 2);
        p2.getScore().addPoint(2, 2, 1);
        p2.getScore().addPoint(0, 2, 2);
        p2.getScore().addPoint(10, 3, 1);
        p2.getScore().addPoint(2, 4, 1);
        p2.getScore().addPoint(3, 4, 2);
        p2.getScore().addPoint(8, 5, 1); //T5
        p2.getScore().addPoint(2, 5, 2); //T5
        p2.getScore().addPoint(10, 6, 1);
        p2.getScore().addPoint(7, 7, 1);
        p2.getScore().addPoint(2, 7, 2);
        p2.getScore().addPoint(1, 8, 1);
        p2.getScore().addPoint(0, 8, 2);
        p2.getScore().addPoint(10, 9, 1);
        p2.getScore().addPoint(8, 10, 1); //T10
        p2.getScore().addPoint(2, 10, 2); //T10 bis
        p2.getScore().addPoint(7, 10, 1); //T10 ter
        assertEquals(120, p2.getScore().getScoreTotal());

    }
    
}
