package kane;

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
     * Starts the main loop of the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String commandWord = Parser.getCommandWord(fullCommand);

                switch (commandWord) {
                    case "bye":
                        isExit = true;
                        break;
                    case "list":
                        ui.showTaskList(tasks.getTasks());
                        break;
                    case "mark":
                        int markIndex = Parser.parseIndex(fullCommand);
                        Task markedTask = tasks.markTask(markIndex);
                        ui.showTaskMarked(markedTask);
                        storage.save(tasks.getTasks());
                        break;
                    case "unmark":
                        int unmarkIndex = Parser.parseIndex(fullCommand);
                        Task unmarkedTask = tasks.unmarkTask(unmarkIndex);
                        ui.showTaskUnmarked(unmarkedTask);
                        storage.save(tasks.getTasks());
                        break;
                    case "delete":
                        int deleteIndex = Parser.parseIndex(fullCommand);
                        Task deletedTask = tasks.deleteTask(deleteIndex);
                        ui.showTaskDeleted(deletedTask, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;
                    case "todo":
                        Task newTodo = Parser.parseTodo(fullCommand);
                        tasks.addTask(newTodo);
                        ui.showTaskAdded(newTodo, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;
                    case "deadline":
                        Task newDeadline = Parser.parseDeadline(fullCommand);
                        tasks.addTask(newDeadline);
                        ui.showTaskAdded(newDeadline, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;
                    case "event":
                        Task newEvent = Parser.parseEvent(fullCommand);
                        tasks.addTask(newEvent);
                        ui.showTaskAdded(newEvent, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;
                    default:
                        throw new KaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (KaneException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
        ui.close();
    }

    public static void main(String[] args) {

        new Kane("data/tasks.txt").run();
    }
}