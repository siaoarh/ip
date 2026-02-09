import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args){
        System.out.println("Hello I am ChatterBot! I was created by github user Siaoarh");
        System.out.println("How can I help you today?");

        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage();

        Task[] loadedTasks = new Task[100];
        int loadedCount = storage.load(loadedTasks);
        TaskList tasks = new TaskList(loadedTasks, loadedCount);

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                Parser.validate(input);
            } catch (ChatterBotException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (input.equals("bye")){
                System.out.println("Goodbye!");
                break;
            }

            if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++){
                    System.out.println((i + 1) + "." + tasks.getTasks()[i] + "\n");
                }
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                try {
                    tasks.mark(index);
                    System.out.println("OK. I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    storage.save(tasks.getTasks(), tasks.size());
                } catch (ChatterBotException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                try {
                    tasks.unmark(index);
                    System.out.println("OK. I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));
                    storage.save(tasks.getTasks(), tasks.size());
                } catch (ChatterBotException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("delete ")) {
                int index;
                try {
                    index = Parser.parseIndex(input, "delete ");
                } catch (ChatterBotException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                try {
                    Task removed = tasks.delete(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    storage.save(tasks.getTasks(), tasks.size());
                } catch (ChatterBotException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("todo ")) {
                tasks.add(new Todo(input.substring(5)));
                printAdded(tasks.getTasks()[tasks.size() - 1], tasks.size());
                storage.save(tasks.getTasks(), tasks.size());
                continue;
            }

            if (input.startsWith("deadline ")) {
                int byPos = input.indexOf(" /by ");
                String desc = input.substring(9, byPos);
                String by = input.substring(byPos + 5);
                tasks.add(new Deadline(desc, by));
                printAdded(tasks.getTasks()[tasks.size() - 1], tasks.size());
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
                printAdded(tasks.getTasks()[tasks.size() - 1], tasks.size());
                storage.save(tasks.getTasks(), tasks.size());
                continue;
            }
        }

        scanner.close();
    }

    private static void printAdded(Task task, int taskCount) {
        System.out.println("Okay can. I have added this.");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}