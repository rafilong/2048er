/**
 * Runs commands on grid
 */
public class Grid {
    /**
     * The grid that computations are run on
     */
    public int[][] grid = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    /**
     * The next grid to be calculated on
     */
    public Grid next;

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
    public Grid(Grid n) {
        this.next = n;
        this.largest = 0;
        setLastLargest();
    }

    /**
     * Sets the largest to be the current largest in the grid
     */
    public void setLargest() {
        largest = getCurrentLargest();
    }

    /**
     * Gets the biggest value currently in the grid
     * @return Returns the biggest number
     */
    public int getCurrentLargest() {
        int currentLargest = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] > currentLargest) {
                    currentLargest = grid[x][y];
                }
            }
        }
        return currentLargest;
    }

    /**
     * Sets lastLargest to largest
     */
    public void setLastLargest() {
        lastLargest = largest;
    }

    /**
     * Sets the next grid to equal the current grid
     */
    public void setNext() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                next.grid[x][y] = grid[x][y];
            }
        }
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
    public int move(int d, boolean findChange) {
        if (d == 0) return moveDown(findChange);
        if (d == 1) return moveLeft(findChange);
        if (d == 2) return moveUp(findChange);
        if (d == 3) return moveRight(findChange);
        if (d > 3 || d < 0) System.err.println("Invalid move");
        return -100;
    }

    /**
     * Finds whether there is a change in the grid
     * @return -100 when the boards are the same to prevent getting stuck
     */
    public int findChange() {
        int totalSame = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (grid[x][y] == next.grid[x][y]) {
                    totalSame++;
                }
            }
        }
        if (totalSame == 16) return -100;
        return 0;
    }

    /**
     * Tries to move the board down
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveDown(boolean findChange) {
        int total = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                if (next.grid[x][y] != 0 && next.grid[x][y] == next.grid[x][y+1]) {
                    next.grid[x][y] *= 2;
                    next.grid[x][y+1] = 0;
                    total += weightingMerge(next.grid[x][y]);
                } else if (next.grid[x][y] == 0 && next.grid[x][y+1] > 0) {
                    next.grid[x][y] = next.grid[x][y+1];
                    next.grid[x][y+1] = 0;
                }
            }
        }
        if (findChange) return total + findChange();
        return total;
    }

    /**
     * Tries to move the board left
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveLeft(boolean findChange) {
        int total = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x > 0; x--) {
                if (next.grid[x][y] != 0 && next.grid[x][y] == next.grid[x-1][y]) {
                    next.grid[x-1][y] *= 2;
                    next.grid[x][y] = 0;
                    total += weightingMerge(next.grid[x][y]);
                } else if (next.grid[x-1][y] == 0 && next.grid[x][y] > 0) {
                    next.grid[x-1][y] = next.grid[x][y];
                    next.grid[x][y] = 0;
                }
            }
        }
        if (findChange) return total + findChange();
        return total;
    }

    /**
     * Tries to move the board up
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveUp(boolean findChange) {
        int total = 0;
        for (int y = 3; y > 0; y--) {
            for (int x = 0; x < 4; x++) {
                if (next.grid[x][y] != 0 && next.grid[x][y] == grid[x][y-1]) {
                    next.grid[x][y] *= 2;
                    next.grid[x][y-1] = 0;
                    total += weightingMerge(next.grid[x][y]);
                } else if (next.grid[x][y] == 0 && next.grid[x][y-1] > 0) {
                    next.grid[x][y-1] = next.grid[x][y];
                    next.grid[x][y-1] = 0;
                }
            }
        }
        if (findChange) return total + findChange();
        return total;
    }

    /**
     * Tries to move the board right
     * @return returns the number of combines, -1 if there are no changes
     */
    public int moveRight(boolean findChange) {
        int total = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (next.grid[x][y] != 0 && next.grid[x][y] == next.grid[x+1][y]) {
                    next.grid[x+1][y] *= 2;
                    next.grid[x][y] = 0;
                    total += weightingMerge(next.grid[x][y]);
                } else if (next.grid[x+1][y] == 0 && next.grid[x][y] > 0) {
                    next.grid[x+1][y] = next.grid[x][y];
                    next.grid[x][y] = 0;
                }

            }
        }
        if (findChange) return total + findChange();
        return total;
    }

    /**
     * Prints the grid
     */
    public void printGrid() {
        for (int y = 3; y >= 0; y--) {
            for (int x = 0; x < 4; x++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static int weightingMerge(int value) {
        if (value >= 2048) return value * 20;
        if (value >= 512) return value * 12;
        if (value >= 128) return value * 8;
        if (value >= 32) return value * 4;
        return value;
    }
}