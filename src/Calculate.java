/**
 * Calculates the best course of action
 */
public class Calculate {
    /**
     * Is the grid of the gameboard
     */
    public static int[][] grid = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    /**
     * Calculates the best move
     */
    public static void calculateMove() {
        int horizontalScore = checkHorizontal();
        System.out.println("Horizon score is " + horizontalScore);
        int verticalScore = checkVertical();
        System.out.println("Verticl score is " + verticalScore);

        double smallest = Math.min(horizontalScore, verticalScore);

        if (horizontalScore != smallest) {
            if (Math.random() < .5) {
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
     * Checks to see how many combines to right
     */
    public static int checkHorizontal() {
        int score = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[x][y] == grid[x+1][y] && grid[x][y] != 0) {
                    score++;
                }
            }
        }
        return score;
    }

    /**
     * Checks to see how many combines up
     */
    public static int checkVertical() {
        int score = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[x][y] == grid[x][y+1] && grid[x][y] != 0) {
                    score++;
                }
            }
        }
        return score;
    }
}
