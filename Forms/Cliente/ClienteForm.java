package Forms.Cliente;

import Clases.Cliente;
import Clases.TipoDocumento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClienteForm extends JFrame {

    private JTextField numeroClienteField, cuilField, nombreField, apellidoField, emailField, domicilioField, telefonoField, documentoField;
    private JComboBox<String> tipoDocumentoComboBox;

    public ClienteForm() {
        setTitle("Registro de Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        // Labels and Fields
        add(new JLabel("Número Cliente:"));
        numeroClienteField = new JTextField();
        add(numeroClienteField);

        add(new JLabel("CUIL:"));
        cuilField = new JTextField();
        add(cuilField);

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        add(apellidoField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        add(domicilioField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("Documento:"));
        documentoField = new JTextField();
        add(documentoField);

        add(new JLabel("Tipo Documento:"));
        tipoDocumentoComboBox = new JComboBox<>(new String[]{"DNI", "PASAPORTE", "LNC", "LIBRETA CIVICA", "LIBRETA DE ENROLAMIENTO"}); // Replace with actual TipoDocumento values
        add(tipoDocumentoComboBox);

        // Buttons
        JButton registrarButton = new JButton("Registrar");
        add(registrarButton);

        JButton cancelarButton = new JButton("Cancelar");
        add(cancelarButton);

        // Action Listeners
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        cancelarButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void toTXT(Cliente cliente){
        String filepath = "Clientes.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(cliente.getNumeroCliente()).append(",");
            sb.append(cliente.getCuil()).append(",");
            sb.append(cliente.getNombre()).append(",");
            sb.append(cliente.getApellido()).append(",");
            sb.append(cliente.geteMail()).append(",");
            sb.append(cliente.getDomicilio()).append(",");
            sb.append(cliente.getTelefono()).append(",");
            sb.append(cliente.getDocumento()).append(",");
            sb.append(cliente.getTipodocumento().toString());
            sb.append("\n");
            writer.write(sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo TXT: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

    }


    }

    private void registrarCliente() {
        try {
            int numeroCliente = Integer.parseInt(numeroClienteField.getText());
            int cuil = Integer.parseInt(cuilField.getText());
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String email = emailField.getText();
            String domicilio = domicilioField.getText();
            String telefono = telefonoField.getText();
            int documento = Integer.parseInt(documentoField.getText());
            String tipoDocumentoText = (String) tipoDocumentoComboBox.getSelectedItem();
            TipoDocumento tipoDocumento = new TipoDocumento(tipoDocumentoText);

            Cliente cliente = new Cliente(numeroCliente, cuil, nombre, apellido, email, domicilio, telefono, documento, tipoDocumento);
            toTXT(cliente);

            JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente:\n" + cliente.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ClienteForm();
    }
}