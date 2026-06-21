package taskmanagementsystem.model;

import taskmanagementsystem.state.TaskState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task {
    private final String id;
    private final User createdBy;
    private final LocalDateTime createdAt;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private User assignee;
    private Set<Tag> tags;
    private List<Comment> comments;
    private List<ActivityLog> activityLogs;
    private Task parentTask;
    private List<Task> subtasks;
    private TaskState state;

    public Task(String id, User createdBy, String title, String description) {
        this.id = id;
        this.createdBy = createdBy;
        this.title = title;
        this.description = description;

        this.createdAt = LocalDateTime.now();

        this.tags = new HashSet<>();
        this.comments = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
        this.subtasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public List<ActivityLog> getActivityLogs() {
        return activityLogs;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
