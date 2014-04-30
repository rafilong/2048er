import java.awt.*;

/**
 * Calculates the grid
 */
public class Grid {
    /**
     * Holds the corner of the board
     */
    public static Point corner;

    /**
     * The point where color is drawn from the first sqaure
     */
    private static Point firstPick = new Point(0, 0);

    /**
     * The current highest block
     */
    public static int largest;

    /**
     * The previous highest block
     */
    public static int lastLargest;

    /**
     * The width of the cells
     */
    private static int cellWidth = 106;

    /**
     * The padding of the cells
     */
    private static int cellPadding = 15;

    /**
     * Holds colors for pieces
     * In order of [0, 2, 4, 8, 16, 32, 64...]
     */
    private static Color[] colors = {new Color(0xccc0b4), new Color(0xeee4da), new Color(0xede0c8), new Color(0xf2b179), new Color(0xf59563), new Color(0xdb8a6f), new Color(0xe95937), new Color(0xf2d86a), new Color(0xe8be2e), new Color(0xe2b913), new Color(0xeec400), new Color (0x60d992)};

    /**
     * Resets all variables
     */
    public static void reset() {
        setFirstPick();
        setGrid();
        lastLargest = largest;
    }

    /**
     * Sets the firstPick to be dependent on corner
     */
    private static void setFirstPick() {
        firstPick.setLocation(corner.getX() + 30, corner.getY() + 30);
    }

    /**
     * Sets the grid according to the screen
     */
    public static void setGrid() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int c = 0; c < 12; c++) {
                    if (colors[c].equals(getColor(x, y))) {
                        Calculate.grid[x][y] = (int) Math.pow(2, c);
                    }
                }
            }
        }
    }

    /**
     * Finds the color of the given row and column
     * @param c the x position
     * @param r the y position
     * @return the color of the position
     */
    public static Color getColor(int c, int r) {
        int x = (int) (firstPick.getX() + c * (cellWidth + cellPadding));
        int y = (int) (firstPick.getY() + r * (cellWidth + cellPadding));
        return Main.fenrir.getColor(x, y);
    }

    /**
     * Gets the biggest value
     * @return Returns the biggest number
     */
    public static int getBiggest() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (Calculate.grid[x][y] > largest) {
                    largest = Calculate.grid[x][y];
                }
            }
        }
        return largest;
    }
}
