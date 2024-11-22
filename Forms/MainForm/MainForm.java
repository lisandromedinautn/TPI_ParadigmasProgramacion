package Forms.MainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    public MainForm(){
        setTitle("Sistema VTV");
        setSize(300, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton registrarRevisionButton = new JButton("Registrar Revision");
        registrarRevisionButton.setPreferredSize(new Dimension(200, 50));
        add(registrarRevisionButton, gbc);

        setVisible(true);

        registrarRevisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new MainForm();
    }
}