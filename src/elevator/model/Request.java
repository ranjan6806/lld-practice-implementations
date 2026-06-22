package elevator.model;

import elevator.enums.Direction;
import elevator.enums.RequestType;

import java.time.LocalDateTime;

public class Request {
    private final int floor;
    private final Direction direction;
    private final RequestType type;
    private final LocalDateTime timestamp;

    public Request(RequestType type, Direction direction, int floor) {
        this.type = type;
        this.direction = direction;
        this.floor = floor;
        this.timestamp = LocalDateTime.now();
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
