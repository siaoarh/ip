package chatterbox.command;

import chatterbox.TaskList;
import chatterbox.Ui;

/**
 * Represents a find command that searches tasks by a keyword.
 */
public class FindCommand {
    private final String keyword;

    /**
     * Construct a FindCommand with the keyword to search for.
     *
     * @param keyword Keyword used to match task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command and displays matching tasks.
     *
     * @param tasks TaskList to search within.
     * @param ui Ui used for displaying the output.
     */
    public void execute(TaskList tasks, Ui ui) {
        TaskList.FindResult result = tasks.findByKeyword(keyword);
        ui.showFindResults(result);
    }
}