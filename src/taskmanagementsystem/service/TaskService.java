package taskmanagementsystem.service;

import taskmanagementsystem.filter.TaskFilter;
import taskmanagementsystem.model.*;
import taskmanagementsystem.state.TodoState;
import taskmanagementsystem.strategy.TaskSortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskService {
    private final Map<String, Task> tasks;
    private final ActivityLogger activityLogger;

    public TaskService(ActivityLogger activityLogger) {
        this.tasks = new ConcurrentHashMap<>();
        this.activityLogger = activityLogger;
    }

    public Task createTask(String title, String description, User creator) {
        Task task = new Task(UUID.randomUUID().toString(), creator, title, description);
        task.setStatus(TaskStatus.TODO);
        task.setState(new TodoState());

        tasks.put(task.getId(), task);
        activityLogger.log(task, ActivityType.CREATED, "Task Created", creator);
        return task;
    }

    public void updateTask(String taskId, String title, String description, User actor) {
        Task task = tasks.get(taskId);
        task.setTitle(title);
        task.setDescription(description);

        activityLogger.log(task, ActivityType.UPDATED, "Task updated", actor);
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    public void assignTask(String taskId, User assignee, User actor) {
        Task task = tasks.get(taskId);
        task.setAssignee(assignee);

        activityLogger.log(task, ActivityType.ASSIGNED, "Assigned to " + assignee.getName(), actor);
    }

    public void changeStatus(String taskId, TaskStatus targetStatus, User actor) {
        Task task = tasks.get(taskId);

        switch (targetStatus) {
            case IN_PROGRESS -> task.getState().startProgress(task);

            case DONE -> task.getState().completeTask(task);

            case BLOCKED -> task.getState().block(task);

            case TODO -> task.getState().reopen(task);
        }

        activityLogger.log(task, ActivityType.STATUS_CHANGED, "Status changed to " + targetStatus, actor);
    }

    public void addSubTask(String parentTaskId, String childTaskId, User actor) {
        Task parent = tasks.get(parentTaskId);

        Task child = tasks.get(childTaskId);
        parent.getSubtasks().add(child);
        child.setParentTask(parent);

        activityLogger.log(parent, ActivityType.SUBTASK_ADDED, "Subtask added", actor);
    }

    public void addComment(String taskId, Comment comment) {
        Task task = tasks.get(taskId);
        task.getComments().add(comment);

        activityLogger.log(task, ActivityType.COMMENT_ADDED, "Comment added", comment.getAuthor());
    }

    public void addTag(String taskId, Tag tag, User actor) {
        Task task = tasks.get(taskId);

        task.getTags().add(tag);
        activityLogger.log(task, ActivityType.TAG_ADDED, "Tag added: " + tag.getName(), actor);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    public List<Task> filterTasks(TaskFilter filter) {
        return tasks.values().stream()
                .filter(task -> filter.getStatus() == null
                        || task.getStatus() == filter.getStatus())
                .filter(task -> filter.getPriority() == null
                        || task.getPriority() == filter.getPriority())
                .filter(task -> filter.getAssigneeId() == null
                        || (task.getAssignee() != null && task.getAssignee().getId().equals(filter.getAssigneeId())))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasks(TaskSortStrategy strategy) {
        return strategy.sort(new ArrayList<>(tasks.values()));
    }
}
