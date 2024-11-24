package Forms.Revision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Forms.Vehiculo.VehiculoForm;

public class RevisionForm {
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
        JButton seleccionarVehiculoButton = new JButton("Seleccionar Vehículo");
        seleccionarVehiculoButton.setBounds(30, 180, 330, 40);
        frame.add(seleccionarVehiculoButton);

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

        // Acción para abrir el VehiculoForm cuando el botón es presionado
        seleccionarVehiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear e instanciar VehiculoForm sin cerrar RevisionForm
                VehiculoForm vehiculoForm = new VehiculoForm();
            }
        });

        // Hacer visible el formulario de Revisión
        frame.setVisible(true);
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