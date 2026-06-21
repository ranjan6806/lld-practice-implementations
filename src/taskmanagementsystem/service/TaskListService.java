package taskmanagementsystem.service;

import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.TaskList;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TaskListService {
    private final Map<String, TaskList> taskLists;

    public TaskListService() {
        this.taskLists = new ConcurrentHashMap<>();
    }

    public TaskList createTaskList(String name) {
        TaskList taskList = new TaskList(UUID.randomUUID().toString(), name);

        taskLists.put(taskList.getId(), taskList);
        return taskList;
    }

    public void addTask(String taskListId, Task task) {
        taskLists.get(taskListId).addTask(task);
    }

    public TaskList getTaskList(String taskListId) {
        return taskLists.get(taskListId);
    }
}
