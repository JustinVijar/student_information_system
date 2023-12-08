import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class Utilities {

    // Put the databaseUrl, user and pass in here
    public static final String databaseUrl = "jdbc:mysql://localhost:3306/student_info_sys",
                                user = "root",
                                pass = "secret";

    abstract void performActionEnter();
    void actionEnter(JPanel mainPanel) {
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");

        ActionMap actionMap = mainPanel.getActionMap();
        actionMap.put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performActionEnter();
            }
        });
    }

    static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2 - frame.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2 - frame.getHeight() / 2;
        frame.setLocation(centerX, centerY);
    }

}