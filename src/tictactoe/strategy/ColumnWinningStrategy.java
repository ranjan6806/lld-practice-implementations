package tictactoe.strategy;

import tictactoe.core.Board;
import tictactoe.model.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {
    public boolean checkWinner(Board board, int row, int col, Symbol symbol) {
        for (int r = 0; r < board.getSize(); r++) {
            if (board.getSymbol(r, col) != symbol) {
                return false;
            }
        }

        return true;
    }
}
