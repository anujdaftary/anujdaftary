package com.scaler.tictactoe;

import com.scaler.tictactoe.controllers.GameController;
import com.scaler.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("What should be the dimension of the game ?");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any Bot in the game ? y/n");
        String isBot = scanner.next();

        int numberOfPlayers = dimension - 1;
        int numberOfHumanPlayers = numberOfPlayers;
        List<Player> players = new ArrayList<>();

        if (isBot.charAt(0) == 'y') {
            numberOfHumanPlayers -= 1;

            System.out.println("Enter name of the bot");
            String botName = scanner.next();

            System.out.println("Enter symbol of the bot");
            String symbol = scanner.next();

            players.add(new Bot(botName, symbol.charAt(0), PlayerType.BOT, BotDifficultyLevel.EASY));
        }

        for (int i = 0; i < numberOfHumanPlayers; i++) {
            System.out.println("Enter the name of the player");
            String name = scanner.next();

            System.out.println("Enter the player symbol");
            String symbol = scanner.next();

            players.add(new Player(name, symbol.charAt(0), PlayerType.HUMAN));
        }

        Game game = gameController.createGame(dimension, players);

        //Start Playing.
        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            //While the game status is IN_PROGRESS, keep playing the game.

            //Display the board to the current player to make the move.
            System.out.println("This is the current board.");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String isUndo = scanner.next();
            if (isUndo.charAt(0) == 'y') {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }
        }

        //Someone has won the game or game is DRAW.
        if (gameController.getGameStatus(game).equals(GameStatus.ENDED)) {
            //Someone has won the game.
            game.displayBoard();
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        }
    }
}