import java.util.ArrayList;
import java.util.Scanner;

public class Kane {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        int end = 0;

        System.out.println("Hello! I'm Kane.\n" + "What can I do for you?\n");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < end; i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                input = sc.nextLine();
            } else {
                tasks.add(input);
                end++;
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }
        System.out.println("Bye! Hope to see you soon!");
        sc.close();
    }
}
