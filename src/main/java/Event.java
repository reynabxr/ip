public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s", super.toFileString(), this.from, this.to);
    }
}
