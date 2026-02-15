package chatterbox.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final boolean exit;

    /**
     * Creates a CommandResult with feedback and no exit.
     *
     * @param feedbackToUser Message to display.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false);
    }

    /**
     * Creates a CommandResult with feedback and exit flag.
     *
     * @param feedbackToUser Message to display.
     * @param exit           True if application should exit.
     */
    public CommandResult(String feedbackToUser, boolean exit) {
        this.feedbackToUser = feedbackToUser;
        this.exit = exit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isExit() {
        return exit;
    }
}