import java.awt.*;

/**
 * Runs the robot
 */
public class JFenrir {
    /**
     * The robot class
     */
    public Robot jFenrir;

    /**
     * Initializes the robot
     */
    public JFenrir() {
        try {
            jFenrir = new Robot();
        } catch (AWTException e) {
            System.err.println("Failed to create robot");
            e.printStackTrace();
        }
    }

    /**
     * Gets the corner of the board
     * @return the point of the corner of the board
     */
    public Point getCorner() {
        Color edge = new Color(0xbbada0);
        int total = 500;
        int current = 0;
        for (int x = 228; x < GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(); x++) {
            for (int y = 0; y < GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight(); y++) {
                if (edge.equals(jFenrir.getPixelColor(x, y))) {
                    current++;
                    System.out.println(current + " numbers of color");
                    if (current == total) {
                        return new Point(x, y);
                    }
                } else {
                    if (current > 0) System.out.println(new Point(x, y));
                    current = 0;
                }
            }
            System.out.println("Calculating " + x + "th row");
        }
        System.out.println("Please open the 2048 window");
        return getCorner();
    }

    /**
     * Gets the color of the pixel
     * @param x variable
     * @param y variable
     * @return the color
     */
    public Color getColor(int x, int y) {
        return jFenrir.getPixelColor(x, y);
    }

    /**
     * Presses and releases a key
     * @param key the key code to be pressed
     */
    public void pressKey(int key) {
        jFenrir.keyPress(key);
        jFenrir.keyRelease(key);
    }

    /**
     * Restarts the game by pressing `R`
     */
    public void restart() {
        pressKey(82);
        System.err.println("Restarting");
    }
}