package kane;

public class Parser {

    public static String getCommandWord(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public static int parseIndex(String fullCommand) throws KaneException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new KaneException("OOPS!!! Please provide a task number.");
        }
        try {
            // User input is 1-based, internal is 0-based
            return Integer.parseInt(parts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new KaneException("OOPS!!! The task number must be an integer.");
        }
    }

    public static ToDo parseTodo(String fullCommand) throws KaneException {
        if (fullCommand.trim().equals("todo")) {
            throw new KaneException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = fullCommand.substring(4).trim();
        return new ToDo(description);
    }

    public static Deadline parseDeadline(String fullCommand) throws KaneException {
        if (!fullCommand.contains("/by")) {
            throw new KaneException("OOPS!!! The deadline must have a /by time.");
        }
        String[] parts = fullCommand.substring(9).split("/by", 2);
        String description = parts[0].trim();
        if (parts.length < 2) {
            throw new KaneException("OOPS!!! The deadline time is missing after /by.");
        }
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new KaneException("OOPS!!! The description and deadline cannot be empty.");
        }
        return new Deadline(description, by);
    }

    public static Event parseEvent(String fullCommand) throws KaneException {
        if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) {
            throw new KaneException("OOPS!!! The event must have both /from and /to times.");
        }
        String[] firstSplit = fullCommand.substring(6).split("/from", 2);
        String description = firstSplit[0].trim();

        if (firstSplit.length < 2) {
            throw new KaneException("OOPS!!! Missing event times after /from.");
        }

        String[] secondSplit = firstSplit[1].split("/to", 2);
        String from = secondSplit[0].trim();
        if (secondSplit.length < 2) {
            throw new KaneException("OOPS!!! Missing event end time after /to.");
        }
        String to = secondSplit[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new KaneException("OOPS!!! The description, from, and to cannot be empty.");
        }
        return new Event(description, from, to);
    }

    /**
     * Parses the find command to extract the search keyword.
     *
     * @param fullCommand The full user input string, e.g., "find book".
     * @return The keyword to search for.
     * @throws KaneException If the keyword is missing.
     */
    public static String parseFind(String fullCommand) throws KaneException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new KaneException("OOPS!!! The keyword for find cannot be empty.");
        }
        return parts[1].trim();
    }
}