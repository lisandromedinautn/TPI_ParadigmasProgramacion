package Forms.Revision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

import Clases.Cliente;
import Clases.TipoDocumento;
import Clases.Vehiculo;
import Forms.Cliente.ClienteForm;
import Forms.Vehiculo.VehiculoForm;

public class RevisionForm {

    String patente;

    // Ruta del archivo que contiene los vehículos
    private static final String ARCHIVO_VEHICULOS = "Forms/Vehiculo/vehiculos.txt";

    public RevisionForm() {

        // Crear el JFrame para RevisionForm
        JFrame frame = new JFrame("Formulario de Revisión");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Etiqueta y campo para "Número de Revisión"
        JLabel numeroRevisionLabel = new JLabel("Número de Revisión:");
        numeroRevisionLabel.setBounds(30, 30, 150, 30);
        frame.add(numeroRevisionLabel);

        JTextField numeroRevisionField = new JTextField();
        numeroRevisionField.setBounds(180, 30, 180, 30);
        frame.add(numeroRevisionField);

        // Botón para seleccionar vehículo
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(30, 180, 120, 30);
        frame.add(buscarButton);

        //Campo patente vehiculo
        JTextField patenteCampo = new JTextField("Patente");
        patenteCampo.setBounds(180, 180, 180, 30);
        frame.add(patenteCampo);

        // Etiqueta y campo para "Fecha de Realización"
        JLabel fechaRealizacionLabel = new JLabel("Fecha de Realización:");
        fechaRealizacionLabel.setBounds(30, 80, 150, 30);
        frame.add(fechaRealizacionLabel);

        JTextField fechaRealizacionField = new JTextField();
        fechaRealizacionField.setBounds(180, 80, 180, 30);
        frame.add(fechaRealizacionField);

        // Casilla para "Pago"
        JLabel pagoLabel = new JLabel("¿Pago realizado?");
        pagoLabel.setBounds(30, 130, 150, 30);
        frame.add(pagoLabel);
        JCheckBox pagoCheckBox = new JCheckBox();
        pagoCheckBox.setBounds(180, 130, 30, 30);
        frame.add(pagoCheckBox);

        //Boton de registrar revision
        JButton registrarButton = new JButton("Registrar Revision");
        registrarButton.setBounds(180, 280, 180, 40);
        frame.add(registrarButton);

        // Hacer visible el formulario de Revisión
        frame.setVisible(true);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patente = patenteCampo.getText().trim().toUpperCase();

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
                    //VehiculoForm(patente);
                }
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
        //new ClienteForm();
        return null;
    }

    // Método main para iniciar la aplicación
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RevisionForm(); // Crear e iniciar RevisionForm
            }
        });
    }
}