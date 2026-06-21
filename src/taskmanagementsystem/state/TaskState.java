package taskmanagementsystem.state;

import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.TaskStatus;

public interface TaskState {
    void startProgress(Task task);

    void completeTask(Task task);

    void block(Task task);

    void reopen(Task task);

    TaskStatus getStatus();
}
