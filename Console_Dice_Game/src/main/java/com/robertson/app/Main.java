/*
    Michael Robertson
    IST-271 -- Fall 2018
    Robertson_8_11
    The purpose of this program is to demonstrate the collaboration of classes that are used to play a simple game
 */

package com.robertson.app;
import com.robertson.domain.factories.GameFactory;
import com.robertson.domain.interfaces.IPlayable;

import java.util.Scanner;

public class Main {

    private static boolean verbose;

    public static void main(String[] args){
        displayBanner();
        setVerbosityLevel();
        // Create a new Game Object
        IPlayable game = GameFactory.newGame("Player 1", "Player 2");
        // Play until there is a winner or the game is tied.
        do {
            game.playRound();
            if (verbose) displayRoundResults(game.displayRound());
        } while (!game.hasWinner() || !game.isTied());

        displayRoundCount(game);
        displayGameResults(game);
        System.exit(0);
    }

    // Display a small banner at the start of the program
    private static void displayBanner(){
        System.out.println("Welcome to the 'First to One' Game!");
        System.out.println("---------------------------------");
    }
    // Displays how many rounds were played
    private static void displayRoundCount(IPlayable game) {
        System.out.println("The game has finished after " + game.getRoundCounter() + " rounds.");
    }

    // Displays the result of each round if the user wants verbose output
    private static void displayRoundResults(String resultString){
        System.out.println(resultString);
    }

    // Displays the result of the game
    public static void displayGameResults(IPlayable game){
        if (game.hasWinner()){
            System.out.println(game.getWinnerName() + " won the game. Beating " + game.getLoserName());
        } else if (game.isTied()){
            System.out.println("The game ended in a Tie! Both players finished with 1");
        }
    }

    // Set the user's wanted verbosity level
    private static void setVerbosityLevel(){
        String input;
        try (Scanner kb = new Scanner(System.in)){
            System.out.println("Would you like the game output to be verbose?");
            System.out.print("Enter 'Y' for verbose:  ");
            input = kb.nextLine();
            verbose = input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES");
        } catch (Exception e){ 
            System.out.println(e.getMessage());
        }

    }
}
