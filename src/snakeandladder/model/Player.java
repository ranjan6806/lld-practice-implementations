package snakeandladder.model;

public class Player {
    private final String id;
    private final String name;

    private int currentPosition;
    private int consecutiveSixCount;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getConsecutiveSixCount() {
        return consecutiveSixCount;
    }

    public void incrementConsecutiveSixCount() {
        consecutiveSixCount++;
    }

    public void resetConsecutiveSixCount() {
        consecutiveSixCount = 0;
    }
}
