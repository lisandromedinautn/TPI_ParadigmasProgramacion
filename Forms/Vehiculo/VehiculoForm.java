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

public class VehiculoForm {

    // Ruta del archivo que contiene los vehículos
    private static final String ARCHIVO_VEHICULOS = "Forms/Vehiculo/vehiculos.txt";

    public VehiculoForm() {
        // Crear la pantalla principal
        JFrame frame = new JFrame("Buscar Vehículo");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Etiqueta y campo de búsqueda
        JLabel patenteLabel = new JLabel("Ingrese la patente:");
        patenteLabel.setBounds(50, 30, 150, 30);
        frame.add(patenteLabel);

        JTextField patenteField = new JTextField();
        patenteField.setBounds(200, 30, 150, 30);
        frame.add(patenteField);

        // Botón de buscar
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(150, 80, 100, 30);
        frame.add(buscarButton);

        // Acción al buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patente = patenteField.getText().trim().toUpperCase();
                if (patente.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese una patente válida.");
                    return;
                }

                // Buscar vehículo
                Vehiculo vehiculo = buscarVehiculoPorPatente(patente);
                if (vehiculo != null) {
                    // Mostrar información del vehículo
                    JOptionPane.showMessageDialog(frame, vehiculo.toString(), "Información del Vehículo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Abrir pantalla para registrar un nuevo vehículo
                    registrarNuevoVehiculo(patente);
                }
            }
        });

        // Mostrar la pantalla principal
        frame.setVisible(true);
    }

    // Método para buscar un vehículo por su patente
    private static Vehiculo buscarVehiculoPorPatente(String patente) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VEHICULOS))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 7 && datos[2].trim().equalsIgnoreCase(patente)) {
                    int numeroDocumento = Integer.parseInt(datos[6].trim());
                    Cliente cliente = buscarClientePorDocumento(numeroDocumento);

                    if (cliente != null) {
                        return new Vehiculo(datos[0], datos[1], datos[2], datos[3],
                                Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), cliente);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    // Método para buscar cliente por documento
    private static Cliente buscarClientePorDocumento(int documento) {
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
        new ClienteForm();
        return null;
    }

    // Método para registrar un nuevo vehículo
    private void registrarNuevoVehiculo(String patenteInicial) {
        JFrame registroFrame = new JFrame("Registrar Vehículo");
        registroFrame.setSize(600, 500);
        registroFrame.setLayout(null);

        JLabel[] etiquetas = {
                new JLabel("Marca:"), new JLabel("Modelo:"), new JLabel("Patente:"),
                new JLabel("Número de Chasis:"), new JLabel("Año de Fabricación:"),
                new JLabel("Peso:"), new JLabel("Número de Documento:"), new JLabel("Tipo de Documento:")
        };

        JTextField[] campos = new JTextField[7];
        for (int i = 0; i < 7; i++) {
            campos[i] = new JTextField();
            etiquetas[i].setBounds(50, 30 + (i * 40), 150, 30);
            campos[i].setBounds(220, 30 + (i * 40), 200, 30);
            registroFrame.add(etiquetas[i]);
            registroFrame.add(campos[i]);
        }

        campos[2].setText(patenteInicial);
        campos[2].setEditable(false);

        JComboBox<String> tipoDocumentoComboBox = new JComboBox<>();
        etiquetas[7].setBounds(50, 310, 150, 30);
        tipoDocumentoComboBox.setBounds(220, 310, 200, 30);
        registroFrame.add(etiquetas[7]);
        registroFrame.add(tipoDocumentoComboBox);

        try (BufferedReader br = new BufferedReader(new FileReader("Forms/Cliente/tipoDocumento.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                tipoDocumentoComboBox.addItem(linea.trim());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(registroFrame, "Error al cargar tipos de documento: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton guardarButton = new JButton("Guardar");
        guardarButton.setBounds(180, 360, 120, 30);
        registroFrame.add(guardarButton);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tipoDocumento = (String) tipoDocumentoComboBox.getSelectedItem();
                    int documento = Integer.parseInt(campos[6].getText().trim());

                    if (!verificarCliente(documento, tipoDocumento)) {
                        JOptionPane.showMessageDialog(registroFrame, "Cliente no encontrado. Registrando cliente...");
                        new ClienteForm();
                        registroFrame.dispose();
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(registroFrame, "Vehiculo Registrado con exito");
                        Cliente cliente;
                        cliente = buscarClientePorDocumento(documento);
                        Vehiculo vehiculo = new Vehiculo(campos[0].getText(), campos[1].getText(), campos[2].getText(), campos[3].getText(), Integer.parseInt(campos[4].getText()), Integer.parseInt(campos[5].getText()), cliente);
                        toTXT(vehiculo);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registroFrame, "Error al registrar vehículo: " + ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        registroFrame.setVisible(true);
    }

    private void toTXT(Vehiculo vehiculo){
        String filepath = "Forms/Vehiculo/Vehiculos.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(vehiculo.toCSV());
            sb.append("\n");
            writer.write(sb.toString());
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(registroFrame, "Error al escribir en el archivo TXT: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private static boolean verificarCliente(int documento, String tipoDocumento) {
        String archivoClientes = "Forms/Cliente/Clientes.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 9) {
                    int documentoArchivo = Integer.parseInt(datos[7].trim());
                    String tipoDocumentoArchivo = datos[8].trim();

                    if (documentoArchivo == documento && tipoDocumentoArchivo.equalsIgnoreCase(tipoDocumento)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        }

        return false;
    }
    public static void main(String[] args) {
        new VehiculoForm();
    }

}
