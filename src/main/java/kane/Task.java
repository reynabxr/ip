package kane;

/**
 * Represents a general task with a description, completion status,
 * and type (e.g., ToDo, Deadline, Event).
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a {@code Task} with the given description and type.
     *
     * @param description description of the task.
     * @param type type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the task data in a format suitable for saving to a file.
     *
     * @return file-friendly representation of the task.
     */
    public String toFileString() {
        return String.format("%s | %d | %s", this.type.getCode(), (isDone ? 1 : 0), this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", this.type.getCode(), this.getStatusIcon(), this.description);
    }
}
