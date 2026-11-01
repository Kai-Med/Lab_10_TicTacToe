import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value;

        do {
            System.out.print(prompt + " (" + low + " - " + high + "): ");
            while (!console.hasNextInt()) {
                System.out.print("Invalid input. Please enter a whole number. ");
                console.next();
                System.out.print(prompt + " (" + low + " - " + high + "): ");
            }

            value = console.nextInt();

            if (value < low || value > high) {
                System.out.println("Invalid input. Please enter a number between " + low + " and " + high + ".");
            }
        } while (value < low || value > high);

        return value;
    }

    public static boolean getYNConfirm(
            Scanner console, String prompt) {
        String answer;

        do {
            System.out.print(prompt + " (Y/N): ");
            answer = console.next().trim();

            if (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter Y or N.");
            }

        } while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"));

        return answer.equalsIgnoreCase("Y");
    }
}