import Forms.MainForm.MainForm;
import Forms.Login.LoginWithFile;

import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWithFile(() -> {
            SwingUtilities.invokeLater(MainForm::new);
        }));
    }
}
