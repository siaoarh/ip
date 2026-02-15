package chatterbox;

import chatterbox.command.Command;
import chatterbox.command.CommandResult;

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
    try {
        Command command = Parser.parse(input);
        CommandResult result = command.execute(tasks, storage);
        return result.getFeedbackToUser();
    } catch (ChatterBotException e) {
        return e.getMessage();
    }
}
}