package Forms.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoginWithFile {

    private static HashMap<String, String> usuariosPermitidos = new HashMap<>();

    public static void main(String[] args) {
        // Cargar usuarios desde el archivo
        cargarUsuarios("Forms/Login/usuarios.txt");

        // Crear el marco principal
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Etiqueta y campo de texto para el nombre de usuario
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(50, 50, 100, 30);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 200, 30);
        frame.add(userField);

        // Etiqueta y campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 100, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        frame.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(150, 150, 120, 30);
        frame.add(loginButton);

        // Acción al presionar el botón
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Validar usuario y contraseña
                if (usuariosPermitidos.containsKey(username) && usuariosPermitidos.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "¡Inicio de sesión exitoso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mostrar el marco
        frame.setVisible(true);
    }

    // Método para cargar usuarios desde un archivo
    private static void cargarUsuarios(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim(); // Eliminar espacios en blanco
                if (!linea.isEmpty() && linea.contains(":")) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String usuario = partes[0].trim();
                        String contraseña = partes[1].trim();
                        usuariosPermitidos.put(usuario, contraseña);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de usuarios: " + e.getMessage());
        }
    }
}
