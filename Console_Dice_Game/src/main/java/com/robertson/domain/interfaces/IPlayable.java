package com.robertson.domain.interfaces;

public interface IPlayable {

        void playRound();
        void checkForWinner();
        String displayRound();
        boolean hasWinner();
        boolean isTied();
        IPlayer getGameWinner();
        IPlayer getGameLoser();
        String getWinnerName();
        String getLoserName();
        int getRoundCounter();
}
