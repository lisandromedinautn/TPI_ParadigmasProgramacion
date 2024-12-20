package Forms.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import Clases.Cliente;
import Clases.TipoDocumento;

public class ClienteForm extends JDialog {

    private JTextField numeroClienteField, cuilField, nombreField, apellidoField, emailField, domicilioField, telefonoField, documentoField;
    private JComboBox<String> tipoDocumentoComboBox;

    public ClienteForm(JFrame parent) {
        super(parent, "Registro de Cliente", true); // Set modal
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Labels and Fields
        contentPanel.add(new JLabel("Número Cliente:"));
        numeroClienteField = new JTextField();
        contentPanel.add(numeroClienteField);

        contentPanel.add(new JLabel("CUIL:"));
        cuilField = new JTextField();
        contentPanel.add(cuilField);

        contentPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        contentPanel.add(nombreField);

        contentPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        contentPanel.add(apellidoField);

        contentPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        contentPanel.add(emailField);

        contentPanel.add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        contentPanel.add(domicilioField);

        contentPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        contentPanel.add(telefonoField);

        contentPanel.add(new JLabel("Documento:"));
        documentoField = new JTextField();
        contentPanel.add(documentoField);

        contentPanel.add(new JLabel("Tipo Documento:"));
        tipoDocumentoComboBox = new JComboBox<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Forms/Cliente/tipoDocumento.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                tipoDocumentoComboBox.addItem(linea.trim());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tipos de documento: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        contentPanel.add(tipoDocumentoComboBox);

        JButton registrarButton = new JButton("Registrar");
        contentPanel.add(registrarButton);

        JButton cancelarButton = new JButton("Cancelar");
        contentPanel.add(cancelarButton);

        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        paddedPanel.add(contentPanel, BorderLayout.CENTER);

        add(paddedPanel, BorderLayout.CENTER);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
                cancelarButton.doClick();
            }
        });

        cancelarButton.addActionListener(e -> dispose());
    }

    private void toTXT(Cliente cliente){
        String filepath = "Forms/Cliente/Clientes.txt";
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
            Long cuil = Long.parseLong(cuilField.getText());
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
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}