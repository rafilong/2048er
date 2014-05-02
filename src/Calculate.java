/**
 * Calculates the best course of action
 */
public class Calculate {
    /**
     * The number of steps to look ahead
     */
    public static int lookAhead = 3;

    /**
     * Holds the best move for the current scenario
     */
    public static int[][] moves = new int[lookAhead][2];

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
        int down = scenarios[0].move(0);
        scenarios[0].setNext();
        int left = scenarios[0].move(1);
        scenarios[0].setNext();
        int up = scenarios[0].move(2);
        scenarios[0].setNext();
        int right = scenarios[0].move(3);

        System.out.println(down + " " + left + " " + up + " " + right);

        int largest = Math.max(Math.max(down, left), Math.max(up, right));
        if (largest == down) return 0;
        if (largest == left) return 1;
        if (largest == up) return 2;
        if (largest == right) return 3;
        System.err.println("wut");
        return 0;
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
