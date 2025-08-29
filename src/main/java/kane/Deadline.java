package kane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Please use yyyy-MM-dd HHmm, e.g. 2019-12-02 1800.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by.format(INPUT_FORMAT);
    }
}

