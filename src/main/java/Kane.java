import java.util.ArrayList;
import java.util.Scanner;

public class Kane {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> allTasks = new ArrayList<>();
        int end = 0;

        System.out.println("Hello! I'm Kane.\n" + "What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < end; i++) {
                    System.out.println(i + 1 + "." + allTasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int ind = Integer.parseInt(input.substring(5));
                if (ind >= 0 && ind < end) {
                    Task t = allTasks.get(ind);
                    t.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(("  " + t));
                }

            } else if (input.startsWith("unmark ")) {
                int ind = Integer.parseInt(input.substring(7));
                if (ind >= 0 && ind < end) {
                    Task t = allTasks.get(ind);
                    t.markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + t);
                }
            } else {
                Task task = null;
                if (input.startsWith("todo ")) {
                    task = new ToDo(input);
                } else if (input.startsWith("deadline ")) {
                    // Remove the "deadline " prefix
                    input.replaceFirst("deadline ", "");

                    // Split on "/by"
                    String[] parts = input.split("/by", 2);

                    String description = parts[0].trim();
                    String by = parts[1].trim();

                    task = new Deadline(description, by);
                } else if (input.startsWith("event ")) {
                    // Remove the "event " prefix
                    input.replaceFirst("event ", "");
                    // First split on "/from"
                    String[] firstSplit = input.split("/from", 2);
                    String description = firstSplit[0].trim(); // "project meeting"

                    // Second split on "/to"
                    String[] secondSplit = firstSplit[1].split("/to", 2);
                    String from = secondSplit[0].trim();
                    String to = secondSplit[1].trim();

                    task = new Event(description, from, to);
                }

                allTasks.add(task);
                end++;
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println(String.format("Now you have %d tasks in the list.", end));
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you soon!");
        sc.close();
    }
}
