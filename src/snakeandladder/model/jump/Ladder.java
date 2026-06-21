package snakeandladder.model.jump;

public class Ladder extends Jump {

    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) {
            throw new IllegalArgumentException("Ladder start must be less than end");
        }
    }
}
