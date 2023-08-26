package com.scaler.tictactoe.strategies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy {
    private List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();
    HashMap<Character, Integer> topLeftDiagonal = new HashMap<>();
    HashMap<Character, Integer> topRightDiagonal = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    private boolean isCellOnTopLeftDiagonal(int row, int col) {
        //Check if the cell is lying on top left diagonal.
        return row == col;
    }

    private boolean isCellOnTopRightDiagonal(int row, int col, int dimension) {
        //Check if the cell is lying on top right diagonal.
        return row + col == dimension - 1;
    }

    @Override
    public boolean checkWinner(Board board, Player player, Cell moveCell) {
        char symbol = player.getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int dimension = board.getBoard().size();

        //Increment the symbol count for the row, col and diagonal (if index lies on diagonal).
        if (!rowSymbolCounts.get(row).containsKey(symbol)) {
            rowSymbolCounts.get(row).put(symbol, 0);
        }
        rowSymbolCounts.get(row).put(symbol,
                rowSymbolCounts.get(row).get(symbol) + 1);

        if (!colSymbolCounts.get(col).containsKey(symbol)) {
            colSymbolCounts.get(col).put(symbol, 0);
        }
        colSymbolCounts.get(col).put(symbol,
                colSymbolCounts.get(col).get(symbol) + 1);


        if (isCellOnTopLeftDiagonal(row, col)) {
            if (!topLeftDiagonal.containsKey(symbol)) {
                topLeftDiagonal.put(symbol, 0);
            }
            topLeftDiagonal.put(symbol,
                    topLeftDiagonal.get(symbol) + 1);
        }

        if (isCellOnTopRightDiagonal(row, col, dimension)) {
            if (!topRightDiagonal.containsKey(symbol)) {
                topRightDiagonal.put(symbol, 0);
            }
            topRightDiagonal.put(symbol,
                    topRightDiagonal.get(symbol) + 1);
        }

        if (rowSymbolCounts.get(row).get(symbol) == dimension ||
            colSymbolCounts.get(col).get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopLeftDiagonal(row, col)) {
            return topLeftDiagonal.get(symbol) == dimension;
        }

        if (isCellOnTopRightDiagonal(row, col, dimension)) {
            return topRightDiagonal.get(symbol) == dimension;
        }

        return false;
    }
}
