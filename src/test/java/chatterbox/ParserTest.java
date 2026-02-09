package chatterbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void validate_todoEmpty_throws() {
        assertThrows(ChatterBotException.class, () -> Parser.validate("todo"));
        assertThrows(ChatterBotException.class, () -> Parser.validate("todo   "));
    }

    @Test
    public void validate_deadlineMissingBy_throws() {
        assertThrows(ChatterBotException.class, () -> Parser.validate("deadline return book"));
    }

    @Test
    public void validate_deadlineValidDate_ok() {
        assertDoesNotThrow(() -> Parser.validate("deadline return book /by 2019-12-02"));
        assertDoesNotThrow(() -> Parser.validate("deadline return book /by 2019-12-02 1800"));
    }

    @Test
    public void validate_eventMissingParts_throws() {
        assertThrows(ChatterBotException.class, () -> Parser.validate("event project meeting /from 2019-12-02"));
        assertThrows(ChatterBotException.class, () -> Parser.validate("event project meeting /to 2019-12-02"));
    }

    @Test
    public void validate_eventValidDate_ok() {
        assertDoesNotThrow(() ->
                Parser.validate("event meeting /from 2019-12-02 1200 /to 2019-12-02 1400"));
    }

    @Test
    public void validate_unknown_throws() {
        assertThrows(ChatterBotException.class, () -> Parser.validate("asdfghjkl"));
    }
}