package taskmanagementsystem.state;

import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.TaskStatus;

public class BlockedState implements TaskState {
    public void startProgress(Task task) {
        task.setState(new InProgressState());
        task.setStatus(TaskStatus.IN_PROGRESS);
    }

    public void completeTask(Task task) {
        throw new IllegalStateException("Blocked task can not be completed");
    }

    public void block(Task task) {
        throw new IllegalStateException("Task already blocked");
    }

    public void reopen(Task task) {
        task.setState(new TodoState());
        task.setStatus(TaskStatus.TODO);
    }

    public TaskStatus getStatus() {
        return TaskStatus.BLOCKED;
    }
}
