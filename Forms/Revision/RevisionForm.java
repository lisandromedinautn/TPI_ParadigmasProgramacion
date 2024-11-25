package Forms.Revision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

import Clases.Vehiculo;
import Forms.Vehiculo.VehiculoForm;

public class RevisionForm {

    // Ruta del archivo que contiene los vehículos
    private static final String ARCHIVO_VEHICULOS = "Forms/Vehiculo/Vehiculos.txt";

    public RevisionForm() {
        // Crear el JFrame para RevisionForm
        JFrame frame = new JFrame("Formulario de Revisión");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Etiqueta y campo para buscar patente
        JLabel patenteLabel = new JLabel("Buscar Patente:");
        patenteLabel.setBounds(30, 30, 150, 30);
        frame.add(patenteLabel);

        JTextField patenteField = new JTextField();
        patenteField.setBounds(180, 30, 180, 30);
        frame.add(patenteField);

        // Botón para buscar vehículo
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(30, 80, 120, 30);
        frame.add(buscarButton);

        // Campos para datos adicionales
        JLabel numeroRevisionLabel = new JLabel("Número de Revisión:");
        numeroRevisionLabel.setBounds(30, 130, 150, 30);
        numeroRevisionLabel.setVisible(false);
        frame.add(numeroRevisionLabel);

        JTextField numeroRevisionField = new JTextField();
        numeroRevisionField.setBounds(180, 130, 180, 30);
        numeroRevisionField.setVisible(false);
        frame.add(numeroRevisionField);

        JLabel fechaRealizacionLabel = new JLabel("Fecha de Realización:");
        fechaRealizacionLabel.setBounds(30, 180, 150, 30);
        fechaRealizacionLabel.setVisible(false);
        frame.add(fechaRealizacionLabel);

        JTextField fechaRealizacionField = new JTextField();
        fechaRealizacionField.setBounds(180, 180, 180, 30);
        fechaRealizacionField.setVisible(false);
        frame.add(fechaRealizacionField);

        JLabel legajoEmpleadoLabel = new JLabel("Legajo del Empleado:");
        legajoEmpleadoLabel.setBounds(30, 230, 150, 30);
        legajoEmpleadoLabel.setVisible(false);
        frame.add(legajoEmpleadoLabel);

        JTextField legajoEmpleadoField = new JTextField();
        legajoEmpleadoField.setBounds(180, 230, 180, 30);
        legajoEmpleadoField.setVisible(false);
        frame.add(legajoEmpleadoField);

        // Botón para registrar revisión
        JButton registrarButton = new JButton("Registrar Revisión");
        registrarButton.setBounds(180, 300, 180, 40);
        registrarButton.setVisible(false);
        frame.add(registrarButton);

        // Mostrar el formulario de revisión
        frame.setVisible(true);

        // Acción del botón de buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patente = patenteField.getText().trim().toUpperCase();
                if (patente.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese una patente válida.");
                    return;
                }

                // Verificar si el vehículo existe
                Vehiculo vehiculo = buscarVehiculoPorPatente(patente);
                if (vehiculo != null) {
                    // Mostrar los campos adicionales
                    numeroRevisionLabel.setVisible(true);
                    numeroRevisionField.setVisible(true);
                    fechaRealizacionLabel.setVisible(true);
                    fechaRealizacionField.setVisible(true);
                    legajoEmpleadoLabel.setVisible(true);
                    legajoEmpleadoField.setVisible(true);
                    registrarButton.setVisible(true);
                } else {
                    // Si no existe, abrir formulario de Vehículo
                    JOptionPane.showMessageDialog(frame, "Vehículo no encontrado. Procediendo a registrar.");
                    new VehiculoForm();
                }
            }
        });

        // Acción del botón de registrar revisión
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroRevision = numeroRevisionField.getText().trim();
                String fechaRealizacion = fechaRealizacionField.getText().trim();
                String legajoEmpleado = legajoEmpleadoField.getText().trim();

                if (numeroRevision.isEmpty() || fechaRealizacion.isEmpty() || legajoEmpleado.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                    return;
                }

                try {
                    // Guardar la revisión en un archivo
                    String archivoRevisiones = "Forms/Revision/Revision.txt";
                    try (FileWriter writer = new FileWriter(archivoRevisiones, true)) {
                        writer.write(String.format("Número de Revisión: %s, Fecha: %s, Legajo: %s, Patente: %s%n",
                                numeroRevision, fechaRealizacion, legajoEmpleado, patenteField.getText()));
                    }

                    JOptionPane.showMessageDialog(frame, "Revisión registrada con éxito.", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Cerrar la ventana actual
                    frame.dispose();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al guardar la revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para buscar un vehículo por su patente
    private static Vehiculo buscarVehiculoPorPatente(String patente) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VEHICULOS))) {
            String linea = br.readLine(); // Leer la cabecera
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 2 && datos[2].trim().equalsIgnoreCase(patente)) {
                    return new Vehiculo(datos[0], datos[1], datos[2], datos[3],
                            Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), null);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de vehículos: " + e.getMessage());
        }
        return null;
    }

    // Método main para probar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RevisionForm());
    }
}
