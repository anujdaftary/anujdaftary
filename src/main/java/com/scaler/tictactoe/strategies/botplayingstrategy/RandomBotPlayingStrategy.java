package com.scaler.tictactoe.strategies.botplayingstrategy;

import com.scaler.tictactoe.models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move decideMove(Player player, Board board) {
        //Just iterate through the board and make the move at the empty cell.

        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    //Bot will make a move here.
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null;
    }
}
