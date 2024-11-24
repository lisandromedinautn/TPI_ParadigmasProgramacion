import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Forms.Login.LoginWithFile;
import Forms.MainForm.MainForm;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWithFile(() -> {
            SwingUtilities.invokeLater(MainForm::new);
        }));
    }
}