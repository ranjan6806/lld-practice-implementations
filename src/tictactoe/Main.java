package tictactoe;

import tictactoe.core.Board;
import tictactoe.core.Game;
import tictactoe.model.Player;
import tictactoe.model.Symbol;
import tictactoe.strategy.ColumnWinningStrategy;
import tictactoe.strategy.DiagonalWinningStrategy;
import tictactoe.strategy.RowWinningStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3, List.of(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy()));
        Queue<Player> players = new LinkedList<>();
        players.offer(new Player("Player1", Symbol.X));
        players.offer(new Player("Player2", Symbol.O));

        Game game = new Game(board, players);
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.makeMove(0, 2);
        game.makeMove(2, 2);

        System.out.println(game.getStatus());
    }
}
