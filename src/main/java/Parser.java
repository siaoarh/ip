public class Parser {

    public static void validate(String input) throws ChatterBotException {
        String trimmed = input.trim();

        if (trimmed.equals("bye") || trimmed.equals("list")) {
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
            return;
        }


        if (trimmed.startsWith("mark ") || trimmed.startsWith("unmark ")) {
            String num = trimmed.replaceAll("\\D+", "");
            if (num.isEmpty()) {
                throw new ChatterBotException(Errors.INVALID_INDEX);
            }
            return;
        }


        throw new ChatterBotException(Errors.UNKNOWN);
    }
}
