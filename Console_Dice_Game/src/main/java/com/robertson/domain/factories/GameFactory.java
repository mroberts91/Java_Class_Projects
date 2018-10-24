package com.robertson.domain.factories;

import com.robertson.domain.Die;
import com.robertson.domain.Game;
import com.robertson.domain.Player;
import com.robertson.domain.interfaces.IPlayable;
/*
    Return a new IPlayable to the caller, with 2 new players.
 */
public class GameFactory {
    public static IPlayable newGame(String player1Name, String player2Name){
        return new Game(
                new Player(player1Name, new Die()),
                new Player(player2Name, new Die())
        );
    }
}
