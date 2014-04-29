import java.awt.*;

public class JFenrir {
    public Robot fenrir;

    public JFenrir() {
        try {
            fenrir = new Robot();
        } catch (AWTException e) {
            System.err.println("Failed to create robot");
            e.printStackTrace();
        }
    }
}
