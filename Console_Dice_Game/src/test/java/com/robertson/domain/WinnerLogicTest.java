package com.robertson.domain;

import com.robertson.app.Main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


class WinnerLogicTest {
    private Player p1;
    private Player p2;
    private Game game;

    @BeforeEach
    void setUp(){
        p1 = new Player("P1", new Die());
        p2 = new Player("P2", new Die());
        game = new Game(p1, p2);
    }

    @AfterEach
    void tearDown(){
        p1 = null;
        p2 = null;
        game = null;
    }
    @Test
    void p1WinTest(){
        System.out.println("\nTest 1");
        p1.setPoints(1);
        p2.setPoints(20);
        game.checkForWinner();
        assertTrue(game.hasWinner());
        assertSame(game.getGameWinner(), p1);
        Main.displayGameResults(game);
    }

    @Test
    void p2WinTest(){
        System.out.println("\nTest 2");
        p1.setPoints(10);
        p2.setPoints(1);
        game.checkForWinner();
        assertTrue(game.hasWinner());
        assertSame(game.getGameWinner(), p2);
        Main.displayGameResults(game);
    }

    @Test
    void tiedTest(){
        System.out.println("\nTest 3");
        p1.setPoints(1);
        p2.setPoints(1);
        game.checkForWinner();
        assertTrue(game.isTied());
        Main.displayGameResults(game);
    }
}
