package com.scaler.tictactoe.models;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, char symbol, PlayerType playerType, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = difficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
