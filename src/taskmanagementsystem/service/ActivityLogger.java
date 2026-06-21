package taskmanagementsystem.service;

import taskmanagementsystem.model.ActivityLog;
import taskmanagementsystem.model.ActivityType;
import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class ActivityLogger {
    public void log(Task task, ActivityType type, String description, User actor) {
        ActivityLog activityLog = new ActivityLog(UUID.randomUUID().toString(), type, description, actor, LocalDateTime.now());
        task.getActivityLogs().add(activityLog);
    }
}
