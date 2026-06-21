package snakeandladder.core;

import snakeandladder.model.Cell;
import snakeandladder.model.jump.Jump;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final Cell[] cells;
    private final Map<Integer, Jump> jumps;

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size + 1];
        this.jumps = new HashMap<>();
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 1; i <= size; i++) {
            cells[i] = new Cell(i);
        }
    }

    public void addJump(Jump jump) {
        jumps.put(jump.getStart(), jump);
    }

    public Jump getJump(int position) {
        return jumps.get(position);
    }

    public int getSize() {
        return size;
    }
}
