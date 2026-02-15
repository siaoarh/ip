package chatterbox;

/**
 * Validates user commands before execution.
 */
public class Parser {

    /**
     * Validates the user's input command. Throws a ChatterBotException if invalid.
     *
     * @param input Raw user input.
     * @throws ChatterBotException If the input is invalid.
     */
    public static void validate(String input) throws ChatterBotException {
        String trimmed = input.trim();

        if (trimmed.equals("bye") || trimmed.equals("list") || trimmed.equals("cheer")) {
            return;
        }

        if (trimmed.equals("find")) {
            throw new ChatterBotException(Errors.FIND_EMPTY);
        }
        if (trimmed.startsWith("find ")) {
            if (trimmed.substring(5).trim().isEmpty()) {
                throw new ChatterBotException(Errors.FIND_EMPTY);
            }
            return;
        }

        // ----- todo -----
        if (trimmed.equals("todo")) {
            throw new ChatterBotException(Errors.TODO_EMPTY);
        }
        if (trimmed.startsWith("todo ")) {
            if (trimmed.substring(5).trim().isEmpty()) {
                throw new ChatterBotException(Errors.TODO_EMPTY);
            }
            return;
        }

        if (trimmed.equals("deadline")) {
            throw new ChatterBotException(Errors.DEADLINE_EMPTY);
        }
        if (trimmed.startsWith("deadline ")) {
            int byPos = trimmed.indexOf(" /by ");
            if (byPos == -1) {
                throw new ChatterBotException(Errors.DEADLINE_FORMAT);
            }
            String desc = trimmed.substring(9, byPos).trim();
            String by = trimmed.substring(byPos + 5).trim();
            if (desc.isEmpty() || by.isEmpty()) {
                throw new ChatterBotException(Errors.DEADLINE_FORMAT);
            }
            if (DateTimeUtility.tryParseUserDateTime(by) == null) {
                throw new ChatterBotException(Errors.INVALID_DATE_FORMAT);
            }
            return;
        }

        if (trimmed.equals("event")) {
            throw new ChatterBotException(Errors.EVENT_EMPTY);
        }
        if (trimmed.startsWith("event ")) {
            int fromPos = trimmed.indexOf(" /from ");
            int toPos = trimmed.indexOf(" /to ");
            if (fromPos == -1 || toPos == -1 || toPos <= fromPos) {
                throw new ChatterBotException(Errors.EVENT_FORMAT);
            }
            String desc = trimmed.substring(6, fromPos).trim();
            String from = trimmed.substring(fromPos + 7, toPos).trim();
            String to = trimmed.substring(toPos + 5).trim();
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new ChatterBotException(Errors.EVENT_FORMAT);
            }
            if (DateTimeUtility.tryParseUserDateTime(from) == null
                    || DateTimeUtility.tryParseUserDateTime(to) == null) {
                throw new ChatterBotException(Errors.INVALID_DATE_FORMAT);
            }
            return;
        }

        if (trimmed.startsWith("mark ") || trimmed.startsWith("unmark ")) {
            String num = trimmed.replaceAll("\\D+", "");
            if (num.isEmpty()) {
                throw new ChatterBotException(Errors.INVALID_INDEX);
            }
            return;
        }

        if (trimmed.equals("delete")) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
        if (trimmed.startsWith("delete ")) {
            parseIndex(trimmed, "delete ");
            return;
        }

        throw new ChatterBotException(Errors.UNKNOWN);
    }

    /**
     * Parses a 1-based index from the input after the given prefix.
     *
     * @param input Full input string.
     * @param prefix Command prefix (e.g., "delete ").
     * @return Parsed integer index.
     * @throws ChatterBotException If the index is missing or invalid.
     */
    public static int parseIndex(String input, String prefix) throws ChatterBotException {
        String numPart = input.substring(prefix.length()).trim();
        if (numPart.isEmpty()) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
        try {
            return Integer.parseInt(numPart);
        } catch (NumberFormatException e) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
    }
}