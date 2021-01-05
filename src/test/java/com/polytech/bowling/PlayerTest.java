package com.polytech.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {

    @Test
    void testCalculatePoints(){
        Player p = new Player("test"); 
        Player p2 = new Player("test"); 
        //Test des strike en chaines
        p.getScore().addPoint(10, 0, 1);
        p.getScore().addPoint(10, 1, 1);
        p.getScore().addPoint(10, 2, 1);
        p.getScore().addPoint(10, 3, 1);
        p.getScore().addPoint(10, 4, 1); //T5
        p.getScore().addPoint(10, 5, 1);
        p.getScore().addPoint(10, 6, 1);
        p.getScore().addPoint(10, 7, 1);
        p.getScore().addPoint(10, 8, 1);
        p.getScore().addPoint(10, 9, 1); //T10
        p.getScore().addPoint(10, 9, 1); //T10 bis
        p.getScore().addPoint(10, 9, 1); //T10 ter
        assertEquals(300, p.getScore().getScoreTotal());

        p2.getScore().addPoint(9, 0, 1);
        p2.getScore().addPoint(1, 0, 2);
        p2.getScore().addPoint(2, 1, 1);
        p2.getScore().addPoint(0, 1, 2);
        p2.getScore().addPoint(10, 2, 1);
        p2.getScore().addPoint(2, 3, 1);
        p2.getScore().addPoint(3, 3, 2);
        p2.getScore().addPoint(8, 4, 1); //T5
        p2.getScore().addPoint(2, 4, 2); //T5
        p2.getScore().addPoint(10, 5, 1);
        p2.getScore().addPoint(7, 6, 1);
        p2.getScore().addPoint(2, 6, 2);
        p2.getScore().addPoint(1, 7, 1);
        p2.getScore().addPoint(0, 7, 2);
        p2.getScore().addPoint(10, 8, 1);
        p2.getScore().addPoint(8, 9, 1); //T10
        p2.getScore().addPoint(2, 9, 2); //T10 bis
        p2.getScore().addPoint(7, 9, 1); //T10 ter
        assertEquals(120, p2.getScore().getScoreTotal());

    }
    
}
