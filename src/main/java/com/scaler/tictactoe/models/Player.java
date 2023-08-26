package com.scaler.tictactoe.models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType type;

    public Player(String name, char symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.type = playerType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    Move decideMove(Board board) {
        //Get the input from the user, where to make the move.
        Scanner scanner = new Scanner(System.in);

        System.out.println(this.getName() + "'s turn, " + "Please give the row to make the move: ");
        int row = scanner.nextInt();

        System.out.println(this.getName() + "'s turn, " + "Please give the col to make the move: ");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }
}
