package com.scaler.tictactoe.controllers;

import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.GameStatus;
import com.scaler.tictactoe.models.Player;

import java.util.List;

public class GameController {
    //GameController will have all the methods that a
    // client needs from the game.

    public void undo(Game game) {

    }

    public Game createGame(int dimension, List<Player> players) {
        try {
            Game game = Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
            return game;
        } catch (Exception e) {
            return null;
        }
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void displayBoard(Game game) {
        //Print the board.
        game.displayBoard();
        //Or
        //game.getBoard().displayBoard();
    }

    public void executeNextMove(Game game) {

    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }
}
