
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

    /**
     * Clears the grid
     */
    public void clearGrid() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                grid[x][y] = 0;
            }
        }
    }

    /**
     * Calls the correct move directions
     * @param d direction of move
     * @return returns the number of combines
     */
    public int move(int d) {
        if (d == 0) return moveDown();
        if (d == 1) return moveLeft();
        if (d == 2) return moveUp();
        if (d == 3) return moveRight();
        if (d > 3 || d < 0) System.err.println("Invalid move");
        return -100;
    }

    /**
     * Tries to move the board down
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveDown() {
        int total = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] != 0 && grid[x][y] == grid[x][y+1]) {
                    grid[x][y] *= 2;
                    grid[x][y+1] = 0;
                    total++;
                } else if (grid[x][y] == 0 && grid[x][y+1] > 0) {
                    grid[x][y] = grid[x][y+1];
                    grid[x][y+1] = 0;
                }
            }
        }
        return total;
    }

    /**
     * Tries to move the board left
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveLeft() {
        int total = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x > 0; x--) {
                if (grid[x][y] != 0 && grid[x][y] == grid[x-1][y]) {
                    grid[x][y] *= 2;
                    grid[x-1][y] = 0;
                    total++;
                } else if (grid[x][y] == 0 && grid[x-1][y] > 0) {
                    grid[x-1][y] = grid[x][y];
                    grid[x-1][y] = 0;
                }
            }
        }
        return total;
    }

    /**
     * Tries to move the board up
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveUp() {
        int total = 0;
        for (int y = 3; y > 0; y--) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] != 0 && grid[x][y] == grid[x][y-1]) {
                    grid[x][y] *= 2;
                    grid[x][y-1] = 0;
                    total++;
                } else if (grid[x][y] == 0 && grid[x][y-1] > 0) {
                    grid[x][y-1] = grid[x][y];
                    grid[x][y-1] = 0;
                }
            }
        }
        return total;
    }

    /**
     * Tries to move the board right
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveRight() {
        int total = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[x][y] != 0 && grid[x][y] == grid[x+1][y]) {
                    grid[x][y] *= 2;
                    grid[x+1][y] = 0;
                    total++;
                } else if (grid[x][y] == 0 && grid[x+1][y] > 0) {
                    grid[x][y] = grid[x+1][y];
                    grid[x+1][y] = 0;
                }

            }
        }
        return total;
    }
}