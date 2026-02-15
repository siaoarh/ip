package chatterbox;

public class ChatterBotCore {
    private final Storage storage;
    private final TaskList tasks;

    public ChatterBotCore() {
        this.storage = new Storage();

        Task[] loadedTasks = new Task[100];
        int loadedCount = storage.load(loadedTasks);

        this.tasks = new TaskList(loadedTasks, loadedCount);
    }

    public String getResponse(String input) {
        // Placeholder for Checkpoint 2
        return "ChatterBot core wired: " + input;
    }
}