package Forms.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import Clases.Cliente;
import Clases.Vehiculo;

public class VehiculoForm {

    // Ruta del archivo que contiene los vehículos
    private static final String ARCHIVO_VEHICULOS = "Forms/Vehiculo/vehiculos.txt";

    public static void main(String[] args) {
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
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length == 7 && datos[2].trim().equalsIgnoreCase(patente)) {
                // Crear el cliente utilizando el número de documento
                int numeroDocumento = Integer.parseInt(datos[6].trim());
                Cliente cliente = buscarClientePorDocumento(numeroDocumento);

                if (cliente != null) {
                    // Retornar el objeto Vehiculo con el cliente correspondiente
                    return new Vehiculo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]),
                            Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), cliente);
                } else {
                    System.out.println("Cliente no encontrado para el documento: " + numeroDocumento);
                    return null;
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
    return null;
}
private static Cliente buscarClientePorDocumento(int documento) {
    String archivoClientes = "Forms/Cliente/Clientes.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length == 4) { // Asegurarse de que el archivo tenga el formato correcto
                int documentoArchivo = Integer.parseInt(datos[2].trim());
                if (documentoArchivo == documento) {
                    // Crear el objeto Cliente con los datos del archivo
                    //return new Cliente(datos[0], datos[1], documentoArchivo, datos[3].trim());
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
    }

    return null; // Cliente no encontrado
}
    // Método para registrar un nuevo vehículo
    private static void registrarNuevoVehiculo(String patenteInicial) {
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
    
        // Campo de patente
        campos[2].setText(patenteInicial);
        campos[2].setEditable(false);
    
        // ComboBox para tipo de documento
        JComboBox<String> tipoDocumentoComboBox = new JComboBox<>();
        etiquetas[7].setBounds(50, 310, 150, 30);
        tipoDocumentoComboBox.setBounds(220, 310, 200, 30);
        registroFrame.add(etiquetas[7]);
        registroFrame.add(tipoDocumentoComboBox);
    
        // Cargar tipos de documento desde el archivo
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
                    String marca = campos[0].getText().trim();
                    String modelo = campos[1].getText().trim();
                    String patente = campos[2].getText().trim();
                    int numeroChasis = Integer.parseInt(campos[3].getText().trim());
                    int añoFabricacion = Integer.parseInt(campos[4].getText().trim());
                    int peso = Integer.parseInt(campos[5].getText().trim());
                    int documento = Integer.parseInt(campos[6].getText().trim());
                    String tipoDocumento = (String) tipoDocumentoComboBox.getSelectedItem();
    
                    // Verificar cliente
                    if (!verificarCliente(documento, tipoDocumento)) {
                        JOptionPane.showMessageDialog(registroFrame, "Cliente no encontrado en el registro.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    // Si el cliente existe, registrar el vehículo
                    Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, patente, numeroChasis, añoFabricacion, peso, null);
                    guardarVehiculoEnArchivo(nuevoVehiculo);
    
                    JOptionPane.showMessageDialog(registroFrame, "Vehículo registrado exitosamente.");
                    registroFrame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registroFrame, "Error al guardar: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        registroFrame.setVisible(true);
    }
    
    private static boolean verificarCliente(int documento, String tipoDocumento) {
        String archivoClientes = "Forms/Cliente/Clientes.txt";
    
        try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    int documentoArchivo = Integer.parseInt(datos[2].trim());
                    String tipoDocumentoArchivo = datos[3].trim();
    
                    if (documentoArchivo == documento && tipoDocumentoArchivo.equalsIgnoreCase(tipoDocumento)) {
                        return true; // Cliente encontrado
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        }
    
        return false; // Cliente no encontrado
    }
    
    // Método para guardar un vehículo en el archivo
    private static void guardarVehiculoEnArchivo(Vehiculo vehiculo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_VEHICULOS, true))) {
            bw.write(vehiculo.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el vehículo: " + e.getMessage());
        }
    }
}

