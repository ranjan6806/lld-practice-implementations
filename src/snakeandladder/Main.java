package snakeandladder;

import snakeandladder.builder.GameBuilder;
import snakeandladder.core.Board;
import snakeandladder.core.Game;
import snakeandladder.model.Dice;
import snakeandladder.model.Player;
import snakeandladder.model.jump.Ladder;
import snakeandladder.model.jump.Snake;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(100);

        board.addJump(new Ladder(4, 25));
        board.addJump(new Ladder(13, 46));
        board.addJump(new Ladder(33, 49));

        board.addJump(new Snake(40, 3));
        board.addJump(new Snake(56, 18));
        board.addJump(new Snake(99, 41));

        Game game = new GameBuilder()
                .setBoard(board)
                .setDice(new Dice(1, 6))
                .addPlayer(new Player("1", "Alice"))
                .addPlayer(new Player("2", "Bob"))
                .build();

        game.start();
    }
}
