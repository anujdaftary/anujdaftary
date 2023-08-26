package com.scaler.tictactoe.models;

import com.scaler.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import com.scaler.tictactoe.strategies.gamewinningstrategy.OrderOneWinningStrategy;
import exceptions.InvalidDimensionException;
import exceptions.InvalidNumberOfPlayers;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void makeNextMove() {
        //1. Get the next player to move.
        Player currentMovePlayer = players.get(nextPlayerIndex);

        //2. Player should decide the move.
        Move move = currentMovePlayer.decideMove(this.getBoard());

        //3. Validate the move. TODO
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        //4. If move is valid, then make the move.
        System.out.println("Move happened at (" + row + ", " + col + ")");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(currentMovePlayer);

        //5. Add this move in the final list of Moves.
        moves.add(move);

        //6. Check if the player has WON the game.
        if (gameWinningStrategy.checkWinner(board,
                currentMovePlayer,
                move.getCell())) {
            gameStatus = GameStatus.ENDED;
            winner = currentMovePlayer;
        }

        //7. Move to the next player.
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
    }

    public Player getWinner() {
        return winner;
    }
    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }



    public static class GameBuilder {
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidDimensionException, InvalidNumberOfPlayers {
            // Game validations will come here.
            if (this.dimension < 3) {
                throw new InvalidDimensionException("Dimension can't be less than 3");
            }

            if (this.players.size() != dimension - 1) {
                throw new InvalidNumberOfPlayers("Number of players should be 1 less than the game dimension");
            }
            //Check if each player has different symbol or not.

            return true;
        }

        public Game build() {
            //validation.
            try {
                isValid();
            } catch (Exception exception) {
                System.out.println("Exception occured during Game creation");
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));
            return game;
        }
    }
}

// Assignment :-

/*
1. Validate the move. - 5 min
2. Undo
3. DRAW.
 */
