package taskmanagementsystem.filter;

import taskmanagementsystem.model.TaskPriority;
import taskmanagementsystem.model.TaskStatus;

public class TaskFilter {
    private TaskStatus status;
    private TaskPriority priority;
    private String assigneeId;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }
}
