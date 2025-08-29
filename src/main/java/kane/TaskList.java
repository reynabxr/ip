package kane;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIndex) throws KaneException {
        validateIndex(taskIndex);
        return tasks.remove(taskIndex);
    }

    public Task markTask(int taskIndex) throws KaneException {
        validateIndex(taskIndex);
        Task t = tasks.get(taskIndex);
        t.markDone();
        return t;
    }

    public Task unmarkTask(int taskIndex) throws KaneException {
        validateIndex(taskIndex);
        Task t = tasks.get(taskIndex);
        t.markUndone();
        return t;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void validateIndex(int index) throws KaneException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaneException("OOPS!!! The task number is invalid.");
        }
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The string to search for within the task descriptions.
     * @return An ArrayList of tasks that match the search criteria.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // For case-insensitive search

        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(lowerCaseKeyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}