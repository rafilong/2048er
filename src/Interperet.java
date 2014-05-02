import java.awt.*;

/**
 * Calculates the grid
 */
public class Interperet {
    /**
     * Holds the corner of the board
     */
    public static Point corner;

    /**
     * The point where color is drawn from the first sqaure
     */
    private static Point firstPick = new Point(0, 0);

    /**
     * The width of the cells
     */
    private static int cellWidth = 107;

    /**
     * The padding of the cells
     */
    private static int cellPadding = 15;

    /**
     * Holds colors for pieces
     * In order of [0, 2, 4, 8, 16, 32, 64...]
     */
    private static Color[] colors = {new Color(0xccc0b4), new Color(0xeee4da), new Color(0xede0c8), new Color(0xf2b179), new Color(0xf59563), new Color(0xf67c5f), new Color(0xf65e3b), new Color(0xedcf72), new Color(0xedcc61), new Color(0xedc53f), new Color(0xeec400), new Color(0x60d992), new Color(0xedcf72)};
    //                                             0                    2                   4                     8                   16                    32                  64                   128                  256                  512                  1024                2048                4096

    /**
     * Resets all variables
     */
    public static void reset() {
        Calculate.scenarios[0].clearGrid();
        setGrid();
        Calculate.scenarios[0].setLastLargest();
    }

    /**
     * Sets the firstPick to be dependent on corner
     */
    public static void setFirstPick() {
        firstPick.setLocation(corner.getX() + 20, corner.getY() - 50);
    }

    /**
     * Sets the grid according to the screen
     */
    public static void setGrid() {
        Color color;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                color = getColor(x, y);
                for (int c = 0; c < 12; c++) {
                    if (colors[c].equals(color)) {
                        Calculate.scenarios[0].grid[x][y] = (int) Math.pow(2, c);
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
        int y = (int) (firstPick.getY() - r * (cellWidth + cellPadding));
        return Main.fenrir.getColor(x, y);
    }
}
