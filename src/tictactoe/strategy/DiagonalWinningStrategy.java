package tictactoe.strategy;

import tictactoe.core.Board;
import tictactoe.model.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    public boolean checkWinner(Board board, int row, int col, Symbol symbol) {
        boolean mainDiagonal = true;

        for (int i = 0; i < board.getSize(); i++) {
            if (board.getSymbol(i, i) != symbol) {
                mainDiagonal = false;
                break;
            }
        }

        if (mainDiagonal) {
            return true;
        }

        boolean antiDiagonal = true;
        int n = board.getSize();
        for (int i = 0; i < n; i++) {
            if (board.getSymbol(i, n - 1 - i) != symbol) {
                antiDiagonal = false;
                break;
            }
        }

        return antiDiagonal;
    }
}
