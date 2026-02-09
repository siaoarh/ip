package chatterbox;

import chatterbox.command.FindCommand;

public class ChatterBot {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        Storage storage = new Storage();

        Task[] loadedTasks = new Task[100];
        int loadedCount = storage.load(loadedTasks);
        TaskList tasks = new TaskList(loadedTasks, loadedCount);

        while (true) {
            String input = ui.readCommand();

            try {
                Parser.validate(input);
            } catch (ChatterBotException e) {
                ui.showError(e.getMessage());
                continue;
            }

            if (input.equals("bye")) {
                ui.showBye();
                break;
            }

            if (input.equals("list")) {
                try {
                    ui.showList(tasks);
                } catch (ChatterBotException e) {
                    ui.showError(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim();
                FindCommand command = new FindCommand(keyword);
                command.execute(tasks, ui);
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                try {
                    tasks.mark(index);
                    ui.showMarked(tasks.get(index));
                    storage.save(tasks.getTasks(), tasks.size());
                } catch (ChatterBotException e) {
                    ui.showError(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                try {
                    tasks.unmark(index);
                    ui.showUnmarked(tasks.get(index));
                    storage.save(tasks.getTasks(), tasks.size());
                } catch (ChatterBotException e) {
                    ui.showError(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("delete ")) {
                int index;
                try {
                    index = Parser.parseIndex(input, "delete ");
                } catch (ChatterBotException e) {
                    ui.showError(e.getMessage());
                    continue;
                }

                try {
                    Task removed = tasks.delete(index);
                    ui.showDeleted(removed, tasks.size());
                    storage.save(tasks.getTasks(), tasks.size());
                } catch (ChatterBotException e) {
                    ui.showError(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("todo ")) {
                tasks.add(new Todo(input.substring(5)));
                ui.showAdded(tasks.getTasks()[tasks.size() - 1], tasks.size());
                storage.save(tasks.getTasks(), tasks.size());
                continue;
            }

            if (input.startsWith("deadline ")) {
                int byPos = input.indexOf(" /by ");
                String desc = input.substring(9, byPos);
                String by = input.substring(byPos + 5);
                tasks.add(new Deadline(desc, by));
                ui.showAdded(tasks.getTasks()[tasks.size() - 1], tasks.size());
                storage.save(tasks.getTasks(), tasks.size());
                continue;
            }

            if (input.startsWith("event ")) {
                int fromPos = input.indexOf(" /from ");
                int toPos = input.indexOf(" /to ");
                String desc = input.substring(6, fromPos);
                String from = input.substring(fromPos + 7, toPos);
                String to = input.substring(toPos + 5);
                tasks.add(new Event(desc, from, to));
                ui.showAdded(tasks.getTasks()[tasks.size() - 1], tasks.size());
                storage.save(tasks.getTasks(), tasks.size());
            }
        }

        ui.close();
    }
}