package taskmanagementsystem.state;

import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.TaskStatus;

public class InProgressState implements TaskState {
    public void startProgress(Task task) {
        throw new IllegalStateException("Task already in progress");
    }

    public void completeTask(Task task) {
        task.setState(new DoneState());
        task.setStatus(TaskStatus.DONE);
    }

    public void block(Task task) {
        task.setState(new BlockedState());
        task.setStatus(TaskStatus.BLOCKED);
    }

    public void reopen(Task task) {
        task.setState(new TodoState());
        task.setStatus(TaskStatus.TODO);
    }

    public TaskStatus getStatus() {
        return TaskStatus.IN_PROGRESS;
    }
}
