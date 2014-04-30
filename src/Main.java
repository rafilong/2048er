import java.awt.*;

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
    }

    /**
     * Initializes the game
     */
    public static void initialize() {
        System.out.println(fenrir.getColor(1, 1));
        Grid.corner = fenrir.getCorner();
        System.out.println(Grid.corner);
    }
}
