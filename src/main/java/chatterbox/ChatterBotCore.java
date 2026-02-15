package chatterbox;

import chatterbox.command.Command;
import chatterbox.command.CommandResult;

/**
 * Core logic handler for ChatterBot.
 * Responsible for loading storage and executing commands.
 */
public class ChatterBotCore {

    private final Storage storage;
    private final TaskList tasks;
    private boolean shouldExit;

    /**
     * Constructs the core and loads tasks from storage.
     */
    public ChatterBotCore() {
        this.storage = new Storage();

        Task[] loadedTasks = new Task[100];
        int loadedCount = storage.load(loadedTasks);

        this.tasks = new TaskList(loadedTasks, loadedCount);
        this.shouldExit = false;
    }

    /**
     * Processes user input and returns chatbot response.
     *
     * @param input User input string.
     * @return Response string for display.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            CommandResult result = command.execute(tasks, storage);

            if (result.isExit()) {
                shouldExit = true;
            }

            return result.getFeedbackToUser();

        } catch (ChatterBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns whether the application should exit.
     *
     * @return True if exit command was triggered.
     */
    public boolean shouldExit() {
        return shouldExit;
    }
}