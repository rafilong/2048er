/**
 * Calculates the best course of action
 */
public class Calculate {
    /**
     * The number of steps to look ahead
     */
    public static int lookAhead = 4;

    /**
     * Holds the best move for the current scenario
     */
    public static int[][] moves = new int[lookAhead + 2][2];

    /**
     * Possible scenarios for the future
     */
    public static Grid[] scenarios = new Grid[lookAhead];

    /**
     * Calculates the best direction to move
     * @return the best direction
     */
    public static int bestMove() {
        scenarios[0].setNext();
        double down = scenarios[0].move(0, true);
        down += getTotalFuture();
        scenarios[0].setNext();
        double left = scenarios[0].move(1, true);
        left += getTotalFuture();
        scenarios[0].setNext();
        double up = scenarios[0].move(2, true);
        up += getTotalFuture();
        scenarios[0].setNext();
        double right = scenarios[0].move(3, true);
        right += getTotalFuture();

        System.out.println(down + " " + left + " " + up + " " + right);

        double largest = Math.max(Math.max(down, left), Math.max(up, right));

        if (largest == -100) Main.fenrir.restart();

        if (largest == down) return 0;
        if (largest == left) return 1;
        if (largest == up) return 2;
        if (largest == right) return 3;
        System.err.println("wut");
        return 0;
    }

    /**
     * Gets the best scenario for the future
     * @return the highest possible score for the future
     */
    public static double getTotalFuture() {
        double total = 0;
        for (double i = 1; i < lookAhead - 1; i++) {
            total += futureDirection((int)i) / 2 * i;
        }
        return total;
    }

    /**
     * Finds which direction is the best to move in for the next turn and sets scenario to that move
     * @param move what move it is
     * @return the largest score
     */
    public static double futureDirection(int move) {
        scenarios[move].setNext();
        double down = scenarios[move].move(0, false);
        scenarios[move].setNext();
        double left = scenarios[move].move(1, false);
        scenarios[move].setNext();
        double up = scenarios[move].move(2, false);
        scenarios[move].setNext();
        double right = scenarios[move].move(3, false);

        double largeScore = 0;

        double largest = Math.max(Math.max(down, left), Math.max(up, right));

        if (largest == down) largeScore = scenarios[move].move(0, false);
        if (largest == left) largeScore = scenarios[move].move(1, false);
        if (largest == up) largeScore = scenarios[move].move(2, false);
        if (largest == right) largeScore = scenarios[move].move(3, false);

        return largeScore;
    }

    /**
     * Moves the grid
     * @param d direction to move
     */
    public static void move(int d) {
        if (d == 0) {
            System.out.println("down");
            Main.fenrir.pressKey(40);
        } else if (d == 1) {
            System.out.println("left");
            Main.fenrir.pressKey(37);
        } else if (d == 2) {
            System.out.println("up");
            Main.fenrir.pressKey(38);
        } else if (d == 3) {
            System.out.println("right");
            Main.fenrir.pressKey(39);
        } else {
            System.err.println("Not a valid direction");
        }
    }
}
