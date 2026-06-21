package taskmanagementsystem.model;

import java.time.LocalDateTime;

public class ActivityLog {
    private final String id;
    private final ActivityType type;
    private final String description;
    private final User actor;
    private final LocalDateTime createdAt;

    public ActivityLog(String id, ActivityType type, String description, User actor, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.actor = actor;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public ActivityType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public User getActor() {
        return actor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
