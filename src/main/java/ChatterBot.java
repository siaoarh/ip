import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args){
        System.out.println("Hello I am ChatterBot! I was created by github user Siaoarh");
        System.out.println("How can I help you today?");

        Scanner scanner = new Scanner(System.in);


        Task[] tasks = new Task[100];
        int taskCount = 0;



        while (true) {
            String input = scanner.nextLine();

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
                for (int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + "." + tasks[i] + "\n");
                }
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                tasks[index - 1].markDone();
                System.out.println("OK. I've marked this task as done:");
                System.out.println("  " + tasks[index - 1]);
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                tasks[index - 1].markNotDone();
                System.out.println("OK. I've marked this task as not done yet:");
                System.out.println("  " + tasks[index - 1]);
                continue;
            }

            if (input.startsWith("todo ")) {
                tasks[taskCount++] = new Todo(input.substring(5));
                printAdded(tasks[taskCount - 1], taskCount);
                continue;
            }

            if (input.startsWith("deadline ")) {
                int byPos = input.indexOf(" /by ");
                String desc = input.substring(9, byPos);
                String by = input.substring(byPos + 5);
                tasks[taskCount++] = new Deadline(desc, by);
                printAdded(tasks[taskCount - 1], taskCount);
                continue;
            }

            if (input.startsWith("event ")) {
                int fromPos = input.indexOf(" /from ");
                int toPos = input.indexOf(" /to ");
                String desc = input.substring(6, fromPos);
                String from = input.substring(fromPos + 7, toPos);
                String to = input.substring(toPos + 5);
                tasks[taskCount++] = new Event(desc, from, to);
                printAdded(tasks[taskCount - 1], taskCount);
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
