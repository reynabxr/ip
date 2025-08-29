import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Please use yyyy-MM-dd HHmm, e.g. 2019-12-02 1800.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), from.format(OUTPUT_FORMAT), to.format(OUTPUT_FORMAT));
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s", super.toFileString(), from.format(INPUT_FORMAT), to.format(INPUT_FORMAT));
    }
}
