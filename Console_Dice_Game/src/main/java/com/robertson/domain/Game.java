package com.robertson.domain;

import com.robertson.domain.interfaces.IPlayable;
import com.robertson.domain.interfaces.IPlayer;
/*
    Purpose of the Game class is to orchestrate First to One game being played
 */
public class Game implements IPlayable {
    private int roundCounter;
    private IPlayer playerOne;
    private IPlayer playerTwo;
    private boolean hasWinner;
    private boolean isTied;
    private IPlayer gameWinner;
    private IPlayer gameLoser;

    // Constructor
    public Game(IPlayer playerOne, IPlayer playerTwo){
        this.roundCounter = 0;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.hasWinner = false;
        this.isTied = false;
        this.gameWinner = null;
        this.gameLoser = null;
    }
    // Orchestrates the playing of a round in the game
    public void playRound(){
        roundCounter++;
        playerOne.roll();
        playerTwo.roll();
        checkForWinner();
    }

    // Check if either player has won or the if the game ended in a tie
    public void checkForWinner(){
        int p1Points = playerOne.getPoints();
        int p2Points = playerTwo.getPoints();

        // Check if any player has 1 point
        if (p1Points == 1 || p2Points == 1){
            // Check if the game is tied
            if (p1Points == 1 && p2Points == 1){
                this.isTied = true;
                this.hasWinner = false;
            }
            // Check is player 1 is the winner
            else if (p1Points < p2Points){
                this.hasWinner = true;
                this.gameWinner = playerOne;
                this.gameLoser = playerTwo;
            }
            // Player 2 is the winner.
            else {
                this.hasWinner = true;
                this.gameWinner = playerTwo;
                this.gameLoser = playerOne;
            }
        }
    }

    // Displays the resulting points after each round
    public String displayRound(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nRound ")
            .append(roundCounter)
            .append("\nPlayer 1 points:  ").append(playerOne.getPoints())
            .append("\nPlayer 2 points:  ").append(playerTwo.getPoints())
            .append("\n");
        return sb.toString();
    }


    // Getters
    public IPlayer getPlayerOne() {
        return playerOne;
    }

    public IPlayer getPlayerTwo() {
        return playerTwo;
    }

    public boolean hasWinner() {
        return hasWinner;
    }

    public IPlayer getGameWinner() {
        return gameWinner;
    }
    public String getWinnerName(){
        return gameWinner.getName();
    }

    public IPlayer getGameLoser() {
        return gameLoser;
    }
    public String getLoserName(){
        return gameLoser.getName();
    }

    public boolean isTied() {
        return isTied;
    }

    public int getRoundCounter() {
        return roundCounter;
    }
}
