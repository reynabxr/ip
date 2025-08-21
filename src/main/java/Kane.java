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
                Task task = new Task(input);
                allTasks.add(task);
                end++;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you soon!");
        sc.close();
    }
}
