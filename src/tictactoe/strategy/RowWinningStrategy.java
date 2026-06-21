package tictactoe.strategy;

import tictactoe.core.Board;
import tictactoe.model.Symbol;

public class RowWinningStrategy implements WinningStrategy {
    public boolean checkWinner(Board board, int row, int col, Symbol symbol) {
        for (int c = 0; c < board.getSize(); c++) {
            if (board.getSymbol(row, c) != symbol) {
                return false;
            }
        }

        return true;
    }
}
