
public class Grid {
    /**
     * The grid that computations are run on
     */
    public int[][] grid = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    /**
     * The current highest block
     */
    public int largest;

    /**
     * The previous highest block
     */
    public int lastLargest;

    /**
     * A class for the instance variables
     */
    public Grid() {
        largest = 0;
        setLastLargest();
    }

    /**
     * Sets and gets the biggest value in the grid
     * @return Returns the biggest number
     */
    public int getLargest() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] > largest) {
                    largest = grid[x][y];
                }
            }
        }
        return largest;
    }

    /**
     * Sets lastLargest to largest
     */
    public void setLastLargest() {
        lastLargest = largest;
    }
}