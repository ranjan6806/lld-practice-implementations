package taskmanagementsystem.strategy;

import taskmanagementsystem.model.Task;

import java.util.Comparator;
import java.util.List;

public class SortByPriority implements TaskSortStrategy {
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream().sorted(Comparator.comparing(Task::getPriority)).toList();
    }
}
