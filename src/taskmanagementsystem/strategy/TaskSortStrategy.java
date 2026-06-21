package taskmanagementsystem.strategy;

import taskmanagementsystem.model.Task;

import java.util.List;

public interface TaskSortStrategy {
    List<Task> sort(List<Task> tasks);
}
