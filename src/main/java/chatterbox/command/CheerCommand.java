package chatterbox.command;

import chatterbox.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

/**
 * Displays cheer text from text file.
 */
public class CheerCommand {
    private static final Path QUOTES_PATH = Path.of("data", "cheer.txt");
    private static final Random RNG = new Random();

    /**
     * Executes the cheer command.
     *
     * @param ui Ui used for displaying the quote.
     */
    public void execute(Ui ui) {
        ui.showCheer(getRandomQuote());
    }

    private String getRandomQuote() {
        List<String> lines;
        try {
            lines = Files.readAllLines(QUOTES_PATH);
        } catch (IOException e) {
            return "Keep going — even small progress counts.";
        }

        if (lines.isEmpty()) {
            return "Keep going — even small progress counts.";
        }

        String quote = lines.get(RNG.nextInt(lines.size())).trim();
        return quote.isEmpty() ? "Keep going — even small progress counts." : quote;
    }
}