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
}