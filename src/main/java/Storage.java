import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create file and parent dirs if they don't exist
        try {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
                return tasks; // return empty list
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;

                switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }
                if (parts[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
