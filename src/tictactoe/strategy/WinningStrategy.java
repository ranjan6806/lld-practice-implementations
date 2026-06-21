package tictactoe.strategy;

import tictactoe.core.Board;
import tictactoe.model.Symbol;

public interface WinningStrategy {
    boolean checkWinner(Board board, int row, int col, Symbol symbol);
}
