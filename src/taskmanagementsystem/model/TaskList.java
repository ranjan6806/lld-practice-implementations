package taskmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final String id;
    private final String name;
    private List<Task> tasks;

    public TaskList(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
