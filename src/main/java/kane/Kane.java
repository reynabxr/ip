package kane;

import java.util.ArrayList;

public class Kane {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the application.
     * @param filePath The path to the file where tasks are stored.
     */
    public Kane(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) { // Catching a broad exception for file loading issues.
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes a single user command and returns a response string for the GUI.
     * @param input The full command from the user.
     * @return A string containing the chatbot's response.
     */
    public String getResponse(String input) {
        try {
            String commandWord = Parser.getCommandWord(input);

            switch (commandWord) {
            case "bye":
                return "Goodbye. Hope to see you again soon!";
            case "list":
                StringBuilder listBuilder = new StringBuilder("Here are the tasks in your list:\n");
                ArrayList<Task> currentTasks = tasks.getTasks();
                for (int i = 0; i < currentTasks.size(); i++) {
                    listBuilder.append(i + 1).append(". ").append(currentTasks.get(i).toString()).append("\n");
                }
                return listBuilder.toString();
            case "mark":
                int markIndex = Parser.parseIndex(input);
                Task markedTask = tasks.markTask(markIndex);
                storage.save(tasks.getTasks());
                return "Nice! I've marked this task as done:\n  " + markedTask;
            case "unmark":
                int unmarkIndex = Parser.parseIndex(input);
                Task unmarkedTask = tasks.unmarkTask(unmarkIndex);
                storage.save(tasks.getTasks());
                return "OK, I've marked this task as not done yet:\n  " + unmarkedTask;
            case "delete":
                int deleteIndex = Parser.parseIndex(input);
                Task deletedTask = tasks.deleteTask(deleteIndex);
                storage.save(tasks.getTasks());
                return "Noted. I've removed this task:\n  " + deletedTask
                        + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            case "todo":
                Task newTodo = Parser.parseTodo(input);
                tasks.addTask(newTodo);
                storage.save(tasks.getTasks());
                return "Got it. I've added this task:\n  " + newTodo
                        + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            case "deadline":
                Task newDeadline = Parser.parseDeadline(input);
                tasks.addTask(newDeadline);
                storage.save(tasks.getTasks());
                return "Got it. I've added this task:\n  " + newDeadline
                        + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            case "event":
                Task newEvent = Parser.parseEvent(input);
                tasks.addTask(newEvent);
                storage.save(tasks.getTasks());
                return "Got it. I've added this task:\n  " + newEvent
                        + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            case "find":
                String keyword = Parser.parseFind(input);
                ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                // Build a string for the found tasks
                if (foundTasks.isEmpty()) {
                    return "Sorry, I couldn't find any matching tasks in your list.";
                } else {
                    StringBuilder findBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
                    for (int i = 0; i < foundTasks.size(); i++) {
                        findBuilder.append(i + 1).append(". ").append(foundTasks.get(i).toString()).append("\n");
                    }
                    return findBuilder.toString();
                }
            default:
                throw new KaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (KaneException e) {
            return e.getMessage();
        }
    }
}