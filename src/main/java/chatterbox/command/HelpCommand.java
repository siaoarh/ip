package chatterbox.command;

import chatterbox.Storage;
import chatterbox.TaskList;

/**
 * A command that shows available commands.
 */
public class HelpCommand implements Command {

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String helpMessage = """
                Available commands:
                - list
                - todo <description>
                - deadline <description> /by <date>
                - event <description> /from <date> /to <date>
                - mark <index>
                - unmark <index>
                - delete <index>
                - find <keyword>
                - cheer
                - bye
                """;

        return new CommandResult(helpMessage.trim());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}