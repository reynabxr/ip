import java.util.Scanner;

public class Kane {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Kane.\n" + "What can I do for you?\n");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you soon!");
        sc.close();
    }
}
