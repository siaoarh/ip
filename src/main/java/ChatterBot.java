import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args){
        System.out.println("Hello I am ChatterBot! I was created by github user Siaoarh");
        System.out.println("How can I help you today?");

        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int taskCount = 0;

        while (true){
            String input = scanner.nextLine();

            if (input.equals("bye")){
                System.out.println("Goodbye!");
                break;
            }

            if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++){
                    System.out.print((i + 1) + ".[" + (isDone[i] ? "X" : " ") + "] " + tasks[i] + "\n");
                }
                continue;
            }

             if (input.startsWith("mark ")) {
                int index = parseIndex(input, "mark ");
                if (index >= 1 && index <= taskCount) {
                    isDone[index - 1] = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + (isDone[index - 1] ? "X" : " ") + "] " + tasks[index - 1]);
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = parseIndex(input, "unmark ");
                if (index >= 1 && index <= taskCount) {
                    isDone[index - 1] = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + (isDone[index - 1] ? "X" : " ") + "] " + tasks[index - 1]);
                }
                continue;
            }

            tasks[taskCount] = input;
            isDone[taskCount] = false;
            taskCount++;

            System.out.println("added: " + input);
        }

        scanner.close();
    }

    private static int parseIndex(String input, String command) {
        try {
            return Integer.parseInt(input.substring(command.length()).trim());
        } catch (NumberFormatException e) {
            return -1; // Invalid index
        }
    }

}
