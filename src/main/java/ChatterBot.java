import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args){
        System.out.println("Hello I am ChatterBot! I was created by github user Siaoarh");
        System.out.println("How can I help you today?");

        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];
        int taskCount = 0;

        while (true){
            String input = scanner.nextLine();

            if (input.equals("bye")){
                System.out.println("Goodbye!");
                break;
            }

            if (input.equals("list")){
                for (int i = 0; i < taskCount; i++){
                    System.out.print((i + 1) + ". " + tasks[i] + "\n");
                }
                continue;
            }
            tasks[taskCount] = input;
            taskCount++;

            System.out.println("added: " + input);
        }

        scanner.close();
    }
}
