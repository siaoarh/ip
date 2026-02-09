package chatterbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.List;


public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "duke.txt");

    public Storage() {
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            Path parent = FILE_PATH.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
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
            lines = Files.readAllLines(FILE_PATH);
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
        // Expected formats:
        // T | 1 | desc
        // D | 0 | desc | by
        // E | 0 | desc | from | to
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
            Files.write(FILE_PATH, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Warning: I cannot save tasks: " + e.getMessage());
        }
    }
}
