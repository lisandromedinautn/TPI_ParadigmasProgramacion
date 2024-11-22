package Forms.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class VehiculoApp {

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
                    return new Vehiculo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]),
                            Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), datos[6]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    // Método para registrar un nuevo vehículo
    private static void registrarNuevoVehiculo(String patenteInicial) {
        JFrame registroFrame = new JFrame("Registrar Vehículo");
        registroFrame.setSize(500, 400);
        registroFrame.setLayout(null);

        // Etiquetas y campos de texto
        JLabel[] etiquetas = {
                new JLabel("Marca:"), new JLabel("Modelo:"), new JLabel("Patente:"),
                new JLabel("Número de Chasis:"), new JLabel("Año de Fabricación:"),
                new JLabel("Peso:"), new JLabel("Nombre del Cliente:")
        };

        JTextField[] campos = new JTextField[7];
        for (int i = 0; i < campos.length; i++) {
            campos[i] = new JTextField();
            etiquetas[i].setBounds(50, 30 + (i * 40), 150, 30);
            campos[i].setBounds(220, 30 + (i * 40), 200, 30);
            registroFrame.add(etiquetas[i]);
            registroFrame.add(campos[i]);
        }

        // Rellenar el campo de patente con la inicial
        campos[2].setText(patenteInicial);
        campos[2].setEditable(false);

        // Botón para guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.setBounds(180, 330, 120, 30);
        registroFrame.add(guardarButton);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar y guardar los datos
                    String marca = campos[0].getText().trim();
                    String modelo = campos[1].getText().trim();
                    String patente = campos[2].getText().trim();
                    int numeroChasis = Integer.parseInt(campos[3].getText().trim());
                    int añoFabricacion = Integer.parseInt(campos[4].getText().trim());
                    int peso = Integer.parseInt(campos[5].getText().trim());
                    String cliente = campos[6].getText().trim();

                    Vehiculo nuevoVehiculo = new Vehiculo(marca, modelo, patente, numeroChasis, añoFabricacion, peso,
                            cliente);
                    guardarVehiculoEnArchivo(nuevoVehiculo);

                    JOptionPane.showMessageDialog(registroFrame, "Vehículo registrado exitosamente.");
                    registroFrame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registroFrame, "Error al guardar: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mostrar la pantalla de registro
        registroFrame.setVisible(true);
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

// Clase Vehiculo
class Vehiculo {
    private String marca;
    private String modelo;
    private String patente;
    private int numeroChasis;
    private int añoFabricacion;
    private int peso;
    private String cliente;

    public Vehiculo(String marca, String modelo, String patente, int numeroChasis, int añoFabricacion, int peso, String cliente) {
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.numeroChasis = numeroChasis;
        this.añoFabricacion = añoFabricacion;
        this.peso = peso;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nPatente: " + patente +
                "\nNúmero de Chasis: " + numeroChasis + "\nAño de Fabricación: " + añoFabricacion +
                "\nPeso: " + peso + " kg\nCliente: " + cliente;
    }

    public String toCSV() {
        return marca + "," + modelo + "," + patente + "," + numeroChasis + "," + añoFabricacion + "," + peso + "," + cliente;
    }
}
