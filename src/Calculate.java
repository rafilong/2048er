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


}
