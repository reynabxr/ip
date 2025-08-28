public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

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

    public String toFileString() {
        return String.format("%s | %d | %s", this.type.getCode(), (isDone ? 1 : 0), this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", this.type.getCode(), this.getStatusIcon(), this.description);
    }
}
