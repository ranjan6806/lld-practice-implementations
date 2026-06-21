package taskmanagementsystem.state;

import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.TaskStatus;

public class TodoState implements TaskState {
    public void startProgress(Task task) {
        task.setState(new InProgressState());
        task.setStatus(TaskStatus.IN_PROGRESS);
    }

    public void completeTask(Task task) {
        throw new IllegalStateException("TODO task cannot be completed directly");
    }

    public void block(Task task) {
        task.setState(new BlockedState());
        task.setStatus(TaskStatus.BLOCKED);
    }

    public void reopen(Task task) {
        throw new IllegalStateException("Task already in TODO");
    }

    public TaskStatus getStatus() {
        return TaskStatus.TODO;
    }
}
