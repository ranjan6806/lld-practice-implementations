package taskmanagementsystem;

import taskmanagementsystem.model.*;
import taskmanagementsystem.service.ActivityLogger;
import taskmanagementsystem.service.TaskListService;
import taskmanagementsystem.service.TaskService;
import taskmanagementsystem.service.UserService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ActivityLogger activityLogger = new ActivityLogger();

        UserService userService = new UserService();

        TaskService taskService = new TaskService(activityLogger);

        TaskListService taskListService = new TaskListService();

        TaskManagementSystem taskManagementSystem = new TaskManagementSystem(userService, taskService, taskListService);

        // Create Users
        User raja = taskManagementSystem.createUser("Raja", "raja@test.com");
        User john = taskManagementSystem.createUser("John", "john@test.com");

        // Create Task List
        TaskList sprintBoard = taskManagementSystem.createTaskList("Sprint Board");
        // Create Parent Task
        Task parentTask = taskManagementSystem.createTask("Build Task Manager", "Machine coding implementation", raja.getId());
        taskManagementSystem.addTaskToList(sprintBoard.getId(), parentTask);

        // Create Sub Task
        Task statePatternTask = taskManagementSystem.createTask("Implement State Pattern", "Status transition handling", raja.getId());
        taskManagementSystem.addSubTask(parentTask.getId(), statePatternTask.getId(), raja.getId());

        // Assign Task
        taskManagementSystem.assignTask(parentTask.getId(), john.getId(), raja.getId());

        // Start Task
        taskManagementSystem.changeStatus(parentTask.getId(), TaskStatus.IN_PROGRESS, john.getId());

        // Add Comment
        Comment comment = new Comment("c1", "Started implementation", john, LocalDateTime.now());

        taskManagementSystem.addComment(parentTask.getId(), comment);

        // Print
        Task task = taskManagementSystem.getTask(parentTask.getId());

        System.out.println("Title : " + task.getTitle());
        System.out.println("Status : " + task.getStatus());
        System.out.println("Subtasks : " + task.getSubtasks().size());
        System.out.println("Comments : " + task.getComments().size());
        System.out.println("\nActivity Logs");
        task.getActivityLogs().forEach(log -> System.out.println(log.getType() + " -> " + log.getDescription()));
    }
}
