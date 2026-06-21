package taskmanagementsystem.state;

import taskmanagementsystem.model.Task;
import taskmanagementsystem.model.TaskStatus;

public class DoneState implements TaskState {
    public void startProgress(Task task) {
        throw new IllegalStateException("Completed task can not be restarted");
    }

    public void completeTask(Task task) {
        throw new IllegalStateException("Task already completed");
    }

    public void block(Task task) {
        throw new IllegalStateException("Completed task can not be blocked");
    }

    public void reopen(Task task) {
        task.setState(new TodoState());
        task.setStatus(TaskStatus.TODO);
    }

    public TaskStatus getStatus() {
        return TaskStatus.DONE;
    }
}
