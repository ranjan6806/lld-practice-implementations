package tictactoe.core;

import tictactoe.model.Cell;
import tictactoe.model.Symbol;
import tictactoe.strategy.WinningStrategy;

import java.util.List;

public class Board {
    private final int size;
    private final Cell[][] cells;
    private final List<WinningStrategy> strategies;

    public Board(int size, List<WinningStrategy> strategies) {
        this.size = size;
        this.strategies = strategies;
        this.cells = new Cell[size][size];

        initializeBoard();
    }

    private void initializeBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && cells[row][col].getSymbol() == Symbol.EMPTY;
    }

    public boolean placeMove(int row, int col, Symbol symbol) {
        if (!isValidMove(row, col)) {
            return false;
        }

        cells[row][col].setSymbol(symbol);
        return true;
    }

    public boolean hasWinner(int row, int col, Symbol symbol) {
        for (WinningStrategy strategy : strategies) {
            if (strategy.checkWinner(this, row, col, symbol)) {
                return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getSymbol() == Symbol.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public Symbol getSymbol(int row, int col) {
        return cells[row][col].getSymbol();
    }

    public int getSize() {
        return size;
    }
}
