package kane;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations
 * to modify, query, and validate tasks within it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the given index.
     *
     * @param taskIndex index of the task to delete.
     * @return the deleted task.
     * @throws KaneException if the index is invalid.
     */
    public Task deleteTask(int taskIndex) throws KaneException {
        validateIndex(taskIndex);
        return tasks.remove(taskIndex);
    }

    /**
     * Marks a task as done at the given index.
     *
     * @param taskIndex index of the task to mark.
     * @return the marked task.
     * @throws KaneException if the index is invalid.
     */
    public Task markTask(int taskIndex) throws KaneException {
        validateIndex(taskIndex);
        Task t = tasks.get(taskIndex);
        t.markDone();
        return t;
    }

    /**
     * Marks a task as not done at the given index.
     *
     * @param taskIndex index of the task to unmark.
     * @return the unmarked task.
     * @throws KaneException if the index is invalid.
     */
    public Task unmarkTask(int taskIndex) throws KaneException {
        validateIndex(taskIndex);
        Task t = tasks.get(taskIndex);
        t.markUndone();
        return t;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void validateIndex(int index) throws KaneException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaneException("OOPS!!! The task number is invalid.");
        }
    }
}