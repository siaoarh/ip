package chatterbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to disk.
 */
public class Storage {

    private static final Path DEFAULT_FILE_PATH = Paths.get("data", "duke.txt");
    private final Path filePath;

    /**
     * Creates a Storage using the default data file path.
     */
    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Creates a Storage using a custom file path (useful for testing).
     *
     * @param filePath Path to the storage file.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            // in case storage fails, the app will not crash
            System.out.println("Warning: I cannot initialise storage file: " + e.getMessage());
        }
    }

    public int load(Task[] tasks) {
        ensureFileExists();

        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Warning: I cannot read data file. Starting with empty list.");
            return 0;
        }

        int count = 0;

        for (String raw : lines) {
            if (raw == null) {
                continue;
            }

            String line = raw.trim();
            if (line.isEmpty()) {
                continue;
            }

            Task task = parseLineToTask(line);
            if (task == null) {
                // if corrupted, skip
                continue;
            }

            if (count >= tasks.length) {
                break;
            }

            tasks[count++] = task;
        }

        return count;
    }

    private Task parseLineToTask(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        String doneStr = parts[1];
        String desc = parts[2];

        boolean isDone = "1".equals(doneStr);

        switch (type) {
        case "T": {
            Todo t = new Todo(desc);
            t.setDone(isDone);
            return t;
        }
        case "D": {
            if (parts.length < 4) {
                return null;
            }
            String by = parts[3];
            Deadline d = new Deadline(desc, by);
            d.setDone(isDone);
            return d;
        }
        case "E": {
            if (parts.length < 5) {
                return null;
            }
            String from = parts[3];
            String to = parts[4];
            Event e = new Event(desc, from, to);
            e.setDone(isDone);
            return e;
        }
        default:
            return null;
        }
    }

    public void save(Task[] tasks, int taskCount) {
        ensureFileExists();

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            if (t != null) {
                lines.add(t.toStorageString());
            }
        }

        try {
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Warning: I cannot save tasks: " + e.getMessage());
        }
    }
}