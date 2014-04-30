/**
 * Calculates the best course of action
 */
public class Calculate {
    /**
     * Is the grid of the gameboard
     */
    public int[][] grid = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    /**
     * The object current
     */
    public static Calculate current = new Calculate();

    /**
     * The object after the move
     */
    public static Calculate after = new Calculate();

    /**
     * Constructor
     */
    public Calculate() {

    }

    /**
     * Calculates the best move
     */
    public static void calculateMove() {
        int[] horiz = current.checkHorizontal();
        int afterScore = 0;
        copyGrid();
        int right = after.moveRight();
        copyGrid();
        int left = after.moveLeft();
        afterScore = Math.max(right, left);
        if (right == left) {
            if (Math.random() > .5) {
                horiz[0] = 1;
            } else {
                horiz[0] = 0;
            }
        } else if (afterScore == left) {
            horiz[0] = 0;
        } else {
            horiz[0] = 1;
        }
        horiz[1] += afterScore;

        int[] verti = current.checkVertical();
        afterScore = 0;
        copyGrid();
        afterScore = after.moveDown();
        verti[1] += afterScore;

        System.out.println("Horizon score is " + horiz[1]);
        System.out.println("Verticl score is " + verti[1]);

        double largest = Math.max(horiz[1], verti[1]);

        if (horiz[1] == largest) {
            if (horiz[0] == 0) {
                move(1);
            } else {
                move(3);
            }
        } else {
            move(0);
        }
    }

    /**
     * Moves the grid
     * @param d direction to move
     */
    public static void move(int d) {
        if (d == 0) {
            Main.fenrir.pressKey(40);
        } else if (d == 1) {
            Main.fenrir.pressKey(37);
        } else if (d == 3) {
            Main.fenrir.pressKey(39);
        } else {
            System.err.println("Not a valid direction");
        }
    }

    /**
     * Copies the grid to gridAfter
     */
    public static void copyGrid() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                after.grid[x][y] = current.grid[x][y];
            }
        }
    }


    /**
     * Checks to see how many combines to right
     * @return the direction and score
     */
    public int[] checkHorizontal() {
        int[] score = {0, 0};
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[x][y] == grid[x+1][y] && grid[x][y] != 0) {
                    score[1]++;
                    if (grid[x][y] > 16) score[1] += 2;
                    if (grid[x][y] > 128) score[1] += 2;
                }
            }
        }
        return score;
    }

    /**
     * Checks to see how many combines down
     */
    public int[] checkVertical() {
        int[] score = {0, 0};
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] == grid[x][y+1] && grid[x][y] != 0) {
                    score[1]++;
                    if (grid[x][y] > 16) score[1] += 2;
                    if (grid[x][y] > 128) score[1] += 2;
                }
            }
        }
        return score;
    }

    /**
     * Moves the grid right
     * @return returns the highest value possible gotten from moving right
     */
    public int moveRight() {
        for (int x = 1; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (grid[4-x][y] > 0) {
                    if (grid[4-x][y] == grid[3-x][y]) {
                        grid[4-x][y] = 0;
                        grid[3-x][y] *= 2;
                    } else if (grid[3-x][y] == 0) {
                        grid[3-x][y] = grid[4-x][y];
                        grid[4-x][y] = 0;
                    }
                }
            }
        }
        return Math.max(checkHorizontal()[1], checkVertical()[1]);
    }

    /**
     * Moves the grid left
     * @return returns the highest value possible gotten from moving left
     */
    public int moveLeft() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 4; y++) {
                if (grid[x][y] > 0) {
                    if (grid[x][y] == grid[x+1][y]) {
                        grid[x+1][y] = 0;
                        grid[x][y] *= 2;
                    } else if (x > 0 && grid[x-1][y] == 0) {
                        grid[x-1][y] = grid[x][y];
                        grid[x][y] = 0;
                    }
                }
            }
        }
        return Math.max(checkHorizontal()[1], checkVertical()[1]);
    }

    /**
     * Moves the grid down
     * @return returns the highest value possible gotten from moving down
     */
    public int moveDown() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] > 0) {
                    if (grid[x][y] == grid[x][y+1]) {
                        grid[x][y+1] = 0;
                        grid[x][y] *= 2;
                    } else if (y > 0 && grid[x][y-1] == 0) {
                        grid[x][y-1] = grid[x][y];
                        grid[x][y] = 0;
                    }
                }
            }
        }
        return Math.max(checkHorizontal()[1], checkVertical()[1]);
    }

    /**
     * Empties the grid
     */
    public void clearGrid() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                grid[x][y] = 0;
            }
        }
    }

}
