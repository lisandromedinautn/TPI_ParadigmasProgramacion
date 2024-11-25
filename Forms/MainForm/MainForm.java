package Forms.MainForm;

import Forms.Login.LoginWithFile;
import Forms.Revision.RevisionForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    public MainForm() {
        setTitle("Sistema VTV");
        setSize(300, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre botones

        // Botón para registrar revisión
        JButton registrarRevisionButton = new JButton("Registrar Revisión");
        registrarRevisionButton.setPreferredSize(new Dimension(200, 50));
        add(registrarRevisionButton, gbc);

        // Incrementar posición en el diseño
        gbc.gridy++;

        // Botón para cerrar sesión
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize(new Dimension(200, 50));
        add(logOutButton, gbc);

        setVisible(true);

        // Acción para abrir RevisionForm
        registrarRevisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisionForm(); // Abrir el formulario de revisión
                dispose(); // Cerrar la ventana actual (MainForm)
            }
        });

        // Acción para cerrar sesión y volver al LoginWithFile
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWithFile(() -> new MainForm()); // Regresar al login
                dispose(); // Cerrar la ventana actual (MainForm)
            }
        });
    }

    public static void main(String[] args) {
        // Abrir el LoginWithFile, que abrirá MainForm si el login es exitoso
        new LoginWithFile(() -> new MainForm());
    }
}
