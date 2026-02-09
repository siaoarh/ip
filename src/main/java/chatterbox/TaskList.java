package chatterbox;

public class TaskList {
    private final Task[] tasks;
    private int taskCount;

    public TaskList(int capacity) {
        this.tasks = new Task[capacity];
        this.taskCount = 0;
    }

    public TaskList(Task[] tasks, int taskCount) {
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    public int size() {
        return taskCount;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task get(int index) throws ChatterBotException {
        if (!isValidIndex(index)) {
            throw new ChatterBotException(Errors.INDEX_OUT_OF_RANGE);
        }
        return tasks[index - 1];
    }

    public void add(Task task) {
        tasks[taskCount++] = task;
    }

    public void mark(int index) throws ChatterBotException {
        get(index).markDone();
    }

    public void unmark(int index) throws ChatterBotException {
        get(index).markNotDone();
    }

    public Task delete(int index) throws ChatterBotException {
        if (!isValidIndex(index)) {
            throw new ChatterBotException(Errors.INDEX_OUT_OF_RANGE);
        }

        Task removed = tasks[index - 1];

        for (int i = index - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[taskCount - 1] = null;
        taskCount--;

        return removed;
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= taskCount;
    }
}