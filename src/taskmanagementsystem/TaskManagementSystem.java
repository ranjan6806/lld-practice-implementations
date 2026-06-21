package taskmanagementsystem;

import taskmanagementsystem.filter.TaskFilter;
import taskmanagementsystem.model.*;
import taskmanagementsystem.service.TaskListService;
import taskmanagementsystem.service.TaskService;
import taskmanagementsystem.service.UserService;
import taskmanagementsystem.strategy.TaskSortStrategy;

import java.util.List;

public class TaskManagementSystem {
    private final UserService userService;
    private final TaskService taskService;
    private final TaskListService taskListService;

    public TaskManagementSystem(UserService userService, TaskService taskService, TaskListService taskListService) {
        this.userService = userService;
        this.taskService = taskService;
        this.taskListService = taskListService;
    }

    // User methods
    public User createUser(String name, String email) {
        return userService.createUser(name, email);
    }

    public User getUser(String userId) {
        return userService.getUser(userId);
    }

    // Task methods
    public Task createTask(String title, String description, String creatorId) {
        User creator = userService.getUser(creatorId);
        return taskService.createTask(title, description, creator);
    }

    public void updateTask(String taskId, String title, String description, String actorId) {
        User actor = userService.getUser(actorId);
        taskService.updateTask(taskId, title, description, actor);
    }

    public void deleteTask(String taskId) {
        taskService.deleteTask(taskId);
    }

    public void assignTask(String taskId, String assigneeId, String actorId) {
        User assignee = userService.getUser(assigneeId);
        User actor = userService.getUser(actorId);
        taskService.assignTask(taskId, assignee, actor);
    }

    public void changeStatus(String taskId, TaskStatus status, String actorId) {
        User actor = userService.getUser(actorId);
        taskService.changeStatus(taskId, status, actor);
    }

    public void addSubTask(String parentTaskId, String childTaskId, String actorId) {
        User actor = userService.getUser(actorId);
        taskService.addSubTask(parentTaskId, childTaskId, actor);
    }

    public void addComment(String taskId, Comment comment) {
        taskService.addComment(taskId, comment);
    }

    public void addTag(String taskId, Tag tag, String actorId) {
        User actor = userService.getUser(actorId);
        taskService.addTag(taskId, tag, actor);
    }

    public Task getTask(String taskId) {
        return taskService.getTask(taskId);
    }

    public List<Task> filterTasks(TaskFilter filter) {
        return taskService.filterTasks(filter);
    }

    public List<Task> sortTasks(TaskSortStrategy strategy) {
        return taskService.sortTasks(strategy);
    }

    // Task list methods
    public TaskList createTaskList(String name) {
        return taskListService.createTaskList(name);
    }

    public void addTaskToList(String taskListId, Task task) {
        taskListService.addTask(taskListId, task);
    }

    public TaskList getTaskList(String taskListId) {
        return taskListService.getTaskList(taskListId);
    }
}
