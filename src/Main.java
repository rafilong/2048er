import java.util.Scanner;

/**
 * Runs basic commands
 */
public class Main {
    /**
     * A super-spiffy robot
     */
    public static JFenrir fenrir = new JFenrir();

    /**
     * Runs the commands
     * @param args unused parameter
     */
    public static void main(String args[]) {
        initialize();
        while (true) {
            Grid.reset();
            Calculate.calculateMove();
            if (Grid.lastLargest < Grid.getBiggest() && Grid.getBiggest() > 2048) {
                boolean wait = false;
                while (!wait) {
                    wait = input("Do you want to continue [Yes/No]");
                }
            }
        }
    }

    /**
     * Initializes the game
     */
    public static void initialize() {
        Grid.corner = fenrir.getCorner();
        System.out.println(Grid.corner);
    }

    /**
     * Prints a question and gets user input by using a scanner.
     *
     * @param question Prints the question before opening a scanner
     * @return The scanner
     */
    public static boolean input(String question) {
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        try {
            return Boolean.parseBoolean(text);
        } catch (NumberFormatException e) {
            System.err.println("Enter a valid boolean");
            return input(question);
        }
    }
}
