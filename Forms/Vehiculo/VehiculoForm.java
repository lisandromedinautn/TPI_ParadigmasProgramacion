package Forms.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Clases.Cliente;
import Clases.TipoDocumento;
import Clases.Vehiculo;
import Forms.Cliente.ClienteForm;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class VehiculoForm extends JFrame {

    private JTextField marcaField, modeloField, patenteField, nroChasisField, añoFabricacionField, pesoField, documentoField;
    private JComboBox<String> tipoDocumentoComboBox;

    public VehiculoForm() {
        setTitle("Registro de Vehiculo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Labels and Fields
        contentPanel.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        contentPanel.add(marcaField);

        contentPanel.add(new JLabel("Modelo:"));
        modeloField = new JTextField();
        contentPanel.add(modeloField);

        contentPanel.add(new JLabel("Patente:"));
        patenteField = new JTextField();
        contentPanel.add(patenteField);

        contentPanel.add(new JLabel("nroChasis:"));
        nroChasisField = new JTextField();
        contentPanel.add(nroChasisField);

        contentPanel.add(new JLabel("Año de fabricacion:"));
        añoFabricacionField = new JTextField();
        contentPanel.add(añoFabricacionField);

        contentPanel.add(new JLabel("Peso:"));
        pesoField = new JTextField();
        contentPanel.add(pesoField);

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

        // Action Listeners
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVehiculo();
            }
        });

        cancelarButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    public Cliente buscarClientePorDocumento(int documento) {
        String archivoClientes = "Forms/Cliente/Clientes.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
            String linea;
            boolean isFirstLine = true; // Salta la cabecera si existe
            while ((linea = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Salta la cabecera
                }

                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length < 9) {
                    System.out.println("Línea malformada: " + linea);
                    continue;
                }

                try {
                    int documentoArchivo = Integer.parseInt(datos[7].trim());
                    if (documentoArchivo == documento) {
                        System.out.println(documentoArchivo + documento);
                        TipoDocumento tipoDocumento = new TipoDocumento(datos[8].trim());
                        return new Cliente(
                                Integer.parseInt(datos[0].trim()), // Número Cliente
                                Long.parseLong(datos[1].trim()), // CUIL
                                datos[2].trim(),                  // Nombre
                                datos[3].trim(),                  // Apellido
                                datos[4].trim(),                  // Email
                                datos[5].trim(),                  // Domicilio
                                datos[6].trim(),                  // Teléfono
                                documentoArchivo,                 // Documento
                                tipoDocumento                     // TipoDocumento
                        );
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Documento inválido en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        }

        // Si no se encuentra el cliente, abrir ClienteForm
        JOptionPane.showMessageDialog(null, "Cliente no encontrado. Procediendo a registrarlo.");
        ClienteForm clienteForm = new ClienteForm(this);
        clienteForm.setVisible(true);
        return buscarClientePorDocumento(documento);
    }

    private void toTXT(Vehiculo vehiculo){
        String filepath = "Forms/Vehiculo/Vehiculos.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            sb.append(vehiculo.getMarca()).append(",");
            sb.append(vehiculo.getModelo()).append(",");
            sb.append(vehiculo.getPatente()).append(",");
            sb.append(vehiculo.getNumeroChasis()).append(",");
            sb.append(vehiculo.getAñoFabricacion()).append(",");
            sb.append(vehiculo.getPeso()).append(",");
            sb.append(vehiculo.getCliente().getDocumento());
            writer.write(sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo TXT: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }


    }

    private void registrarVehiculo() {
        try {
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            String patente = patenteField.getText();
            String nroChasis = nroChasisField.getText();
            int añoFabricacion = Integer.parseInt(añoFabricacionField.getText());
            int peso = Integer.parseInt(pesoField.getText());
            int documento = Integer.parseInt(documentoField.getText());

            Cliente cliente = buscarClientePorDocumento(documento);

            Vehiculo vehiculo = new Vehiculo(marca, modelo, patente, nroChasis, añoFabricacion, peso, cliente);
            toTXT(vehiculo);

            JOptionPane.showMessageDialog(this, "Vehiculo registrado exitosamente:\n");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar vehiculo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new VehiculoForm();
    }
}