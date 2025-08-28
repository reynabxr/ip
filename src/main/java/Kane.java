import java.util.ArrayList;
import java.util.Scanner;

public class Kane {
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static Storage storage;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // initialise storage
        storage = new Storage("data/tasks.txt");
        allTasks = storage.load();

        System.out.println("Hello! I'm Kane.\n" + "What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                handleCommand(input);
            } catch (KaneException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("OOPS!!! Something went wrong: " + e.getMessage());
            }
            input = sc.nextLine();
        }

        System.out.println("Bye! Hope to see you soon!");
        sc.close();
    }

    private static void handleCommand(String input) throws KaneException {
        if (input.equals("list")) {
            handleList();
        } else if (input.startsWith("mark ")) {
            handleMark(input);
        } else if (input.startsWith("unmark ")) {
            handleUnmark(input);
        } else if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else {
            throw new KaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println((i + 1) + "." + allTasks.get(i));
        }
    }

    private static void handleMark(String input) throws KaneException {
        int ind = Integer.parseInt(input.substring(5).trim()) - 1;
        if (ind < 0 || ind >= allTasks.size()) {
            throw new KaneException("OOPS!!! The task number is invalid.");
        }
        Task t = allTasks.get(ind);
        t.markDone();
        storage.save(allTasks);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
    }

    private static void handleUnmark(String input) throws KaneException {
        int ind = Integer.parseInt(input.substring(7).trim()) - 1;
        if (ind < 0 || ind >= allTasks.size()) {
            throw new KaneException("OOPS!!! The task number is invalid.");
        }
        Task t = allTasks.get(ind);
        t.markUndone();
        storage.save(allTasks);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    private static void handleTodo(String input) throws KaneException {
        if (input.trim().equals("todo")) {
            throw new KaneException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDo(input.substring(5).trim());
        addTask(task);
        storage.save(allTasks);
    }

    private static void handleDeadline(String input) throws KaneException {
        if (!input.contains("/by")) {
            throw new KaneException("OOPS!!! The deadline must have a /by time.");
        }
        String[] parts = input.substring(9).split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new KaneException("OOPS!!! The description and deadline cannot be empty.");
        }

        Task task = new Deadline(description, by);
        addTask(task);
        storage.save(allTasks);
    }

    private static void handleEvent(String input) throws KaneException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new KaneException("OOPS!!! The event must have both /from and /to times.");
        }
        String[] firstSplit = input.substring(6).split("/from", 2);
        String description = firstSplit[0].trim();
        String[] secondSplit = firstSplit[1].split("/to", 2);
        String from = secondSplit[0].trim();
        String to = secondSplit[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new KaneException("OOPS!!! The description, from, and to cannot be empty.");
        }

        Task task = new Event(description, from, to);
        addTask(task);
        storage.save(allTasks);
    }

    private static void addTask(Task task) {
        allTasks.add(task);
        storage.save(allTasks);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.", allTasks.size()));
    }

    private static void deleteTask(String input) throws KaneException {
        int ind = Integer.parseInt(input.substring(7).trim()) - 1;
        if (ind < 0 || ind >= allTasks.size()) {
            throw new KaneException("OOPS!!! The task number is invalid.");
        }
        Task t = allTasks.get(ind);
        allTasks.remove(ind);
        storage.save(allTasks);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println(String.format("Now you have %d tasks in the list.", allTasks.size()));
    }
}

