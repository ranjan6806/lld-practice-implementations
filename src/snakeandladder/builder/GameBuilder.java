package snakeandladder.builder;

import snakeandladder.core.Board;
import snakeandladder.core.Game;
import snakeandladder.model.Dice;
import snakeandladder.model.Player;

import java.util.LinkedList;
import java.util.Queue;

public class GameBuilder {
    private final Queue<Player> players = new LinkedList<>();
    private Board board;
    private Dice dice;

    public GameBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public GameBuilder setDice(Dice dice) {
        this.dice = dice;
        return this;
    }

    public GameBuilder addPlayer(Player player) {
        players.add(player);
        return this;
    }

    public Game build() {
        validate();
        return new Game(board, dice, players);
    }

    private void validate() {
        if (board == null) {
            throw new IllegalStateException("Board is required");
        }

        if (dice == null) {
            throw new IllegalStateException("Dice is required");
        }

        if (players.size() < 2) {
            throw new IllegalStateException("Minimum 2 players required");
        }

        if (players.size() > 4) {
            throw new IllegalStateException("Maximum 4 players allowed");
        }
    }
}
