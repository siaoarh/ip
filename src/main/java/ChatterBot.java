import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args){
        System.out.println("Hello I am ChatterBot! I was created by github user Siaoarh");
        System.out.println("How can I help you today?");

        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();

            if (input.equals("bye")){
                System.out.println("Goodbye!");
                break;
            }
            System.out.println(input);
        }

        scanner.close();
    }
}
