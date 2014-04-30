/**
 * Calculates the best course of action
 */
public class Calculate {
    /**
     * Is the grid of the gameboard
     */
    public static int[][] grid = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    /**
     * The grid after a move
     */
    public static int[][] gridAfter = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    /**
     * Calculates the best move
     */
    public static void calculateMove() {
        int[] horiz = checkHorizontal();
        int[] verti = checkVertical();
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
                gridAfter[x][y] = grid[x][y];
            }
        }
    }


    /**
     * Checks to see how many combines to right
     * @return the direction and score
     */
    public static int[] checkHorizontal() {
        int[] score = {0, 0};
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[x][y] == grid[x+1][y] && grid[x][y] != 0) {
                    score[1]++;
                    if (grid[x][y] > 16) score[1] += 2;
                    if (grid[x][y] > 128) score[1] += 2;
                    int afterScore = 0;
                    copyGrid();
                    int right = moveRight();
                    int left = moveLeft();
                    afterScore = Math.max(right, left);
                    if (afterScore == left) {
                        score[0] = 0;
                    } else {
                        score[0] = 1;
                    }
                    score[1] += afterScore;
                }
            }
        }
        return score;
    }

    /**
     * Checks to see how many combines down
     */
    public static int[] checkVertical() {
        int[] score = {0, 0};
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] == grid[x][y+1] && grid[x][y] != 0) {
                    score[1]++;
                    if (grid[x][y] > 16) score[1] += 2;
                    if (grid[x][y] > 128) score[1] += 2;
                    int afterScore = 0;
                    copyGrid();
                    score[1] += moveDown();
                }
            }
        }
        return score;
    }

    /**
     * Moves the grid right
     * @return returns the highest value possible gotten from moving right
     */
    public static int moveRight() {
        for (int x = 0; x < 3; x++) {
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
    public static int moveLeft() {
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
    public static int moveDown() {
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

}
