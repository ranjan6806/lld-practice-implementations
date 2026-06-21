package tictactoe.core;

import tictactoe.model.GameStatus;
import tictactoe.model.Player;
import tictactoe.model.Symbol;

import java.util.Queue;

public class Game {
    private final Board board;
    private final Queue<Player> players;

    private GameStatus status;

    public Game(Board board, Queue<Player> players) {
        this.board = board;
        this.players = players;
        this.status = GameStatus.IN_PROGRESS;
    }

    public void makeMove(int row, int col) {
        Player currentPlayer = players.poll();
        if (!board.placeMove(row, col, currentPlayer.getSymbol())) {
            System.out.println("Invalid Move");
            players.offer(currentPlayer);
            return;
        }

        if (board.hasWinner(row, col, currentPlayer.getSymbol())) {
            this.status = currentPlayer.getSymbol() == Symbol.X ? GameStatus.WINNER_X : GameStatus.WINNER_O;
            return;
        }

        if (board.isFull()) {
            status = GameStatus.DRAW;
            return;
        }

        players.offer(currentPlayer);
    }

    public GameStatus getStatus() {
        return status;
    }
}
