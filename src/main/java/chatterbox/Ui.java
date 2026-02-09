package chatterbox;

import java.util.Scanner;

/**
 * Handles all user interactions (input + output).
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello I am ChatterBot! I was created by github user Siaoarh");
        System.out.println("How can I help you today?");
    }

    /**
     * Reads a command from the user.
     *
     * @return Trimmed user input line.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the bye message.
     */
    public void showBye() {
        System.out.println("Goodbye!");
    }

    /**
     * Displays an error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks TaskList to display.
     * @throws ChatterBotException If an internal indexing issue occurs.
     */
    public void showList(TaskList tasks) throws ChatterBotException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTasks()[i] + "\n");
        }
    }

    /**
     * Displays the results of a find operation.
     *
     * @param result Find result containing matches.
     */
    public void showFindResults(TaskList.FindResult result) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < result.count; i++) {
            System.out.println((i + 1) + "." + result.matches[i]);
        }
    }

    public void showMarked(Task task) {
        System.out.println("OK. I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("OK. I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showDeleted(Task removed, int count) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public void showAdded(Task task, int count) {
        System.out.println("Okay can. I have added this.");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Closes input resources.
     */
    public void close() {
        scanner.close();
    }
}