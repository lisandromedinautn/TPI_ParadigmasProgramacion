import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo Swing");
        JButton button = new JButton("Haz clic aquí");
        button.setBounds(50, 50, 200, 50);
        frame.add(button);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}