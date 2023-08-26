package com.scaler.tictactoe.strategies.botplayingstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Bot;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board);
}


/*
Player <- Bot

Player player = new PLayer;
Player player = new Bot();
 */
