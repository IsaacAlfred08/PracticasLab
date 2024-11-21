import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InventarioGUI extends JFrame {
    private InventarioVehiculos inventario = new InventarioVehiculos();

    // Componentes de la GUI
    private JTextField txtMarca, txtModelo, txtAnio, txtNumeroIdentificacion, txtNumeroPuertas, txtTipoMotor, txtCapacidadCarga;
    private JTextArea txtAreaInventario;

    public InventarioGUI() {
        setTitle("Gestión de Inventario de Vehículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para agregar vehículos
        JPanel panelAgregar = new JPanel(new GridLayout(11, 2));
        panelAgregar.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panelAgregar.add(txtMarca);

        panelAgregar.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelAgregar.add(txtModelo);

        panelAgregar.add(new JLabel("Año de Fabricación:"));
        txtAnio = new JTextField();
        panelAgregar.add(txtAnio);

        panelAgregar.add(new JLabel("Número de Identificación:"));
        txtNumeroIdentificacion = new JTextField();
        panelAgregar.add(txtNumeroIdentificacion);

        panelAgregar.add(new JLabel("Número de Puertas (Auto):"));
        txtNumeroPuertas = new JTextField();
        panelAgregar.add(txtNumeroPuertas);

        panelAgregar.add(new JLabel("Tipo de Motor (Motocicleta):"));
        txtTipoMotor = new JTextField();
        panelAgregar.add(txtTipoMotor);

        panelAgregar.add(new JLabel("Capacidad de Carga (Camión):"));
        txtCapacidadCarga = new JTextField();
        panelAgregar.add(txtCapacidadCarga);

        JButton btnAgregar = new JButton("Agregar Vehículo");
        panelAgregar.add(btnAgregar);

        JButton btnMostrarInventario = new JButton("Mostrar Inventario");
        panelAgregar.add(btnMostrarInventario);

        JButton btnBorrar = new JButton("Borrar Vehículo");
        panelAgregar.add(btnBorrar);

        JButton btnModificar = new JButton("Modificar Vehículo");
        panelAgregar.add(btnModificar);

        // Botones para guardar y cargar inventario
        JButton btnGuardar = new JButton("Guardar Inventario");
        panelAgregar.add(btnGuardar);

        JButton btnCargar = new JButton("Cargar Inventario");
        panelAgregar.add(btnCargar);

        // Área de texto para mostrar el inventario
        txtAreaInventario = new JTextArea();
        txtAreaInventario.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaInventario);

        add(panelAgregar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listeners para botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo();
            }
        });

        btnMostrarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarVehiculo();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarVehiculo();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarInventario();
            }
        });

        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarInventario();
            }
        });
    }

    // Método para agregar un vehículo al inventario
    private void agregarVehiculo() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int anio = Integer.parseInt(txtAnio.getText());
        String numeroIdentificacion = txtNumeroIdentificacion.getText();

        if (!txtNumeroPuertas.getText().isEmpty()) {
            int numeroPuertas = Integer.parseInt(txtNumeroPuertas.getText());
            Auto auto = new Auto(marca, modelo, anio, numeroIdentificacion, numeroPuertas);
            inventario.agregarVehiculo(auto);
        } else if (!txtTipoMotor.getText().isEmpty()) {
            String tipoMotor = txtTipoMotor.getText();
            Motocicleta moto = new Motocicleta(marca, modelo, anio, numeroIdentificacion, tipoMotor);
            inventario.agregarVehiculo(moto);
        } else if (!txtCapacidadCarga.getText().isEmpty()) {
            double capacidadCarga = Double.parseDouble(txtCapacidadCarga.getText());
            Camion camion = new Camion(marca, modelo, anio, numeroIdentificacion, capacidadCarga);
            inventario.agregarVehiculo(camion);
        }

        JOptionPane.showMessageDialog(this, "Vehículo agregado al inventario.");
        limpiarCampos();
    }

    // Método para mostrar el inventario en el área de texto
    private void mostrarInventario() {
        txtAreaInventario.setText(""); // Limpia el área de texto
        for (Vehiculo vehiculo : inventario.getInventario()) {
            txtAreaInventario.append(vehiculo.toString() + "\n");
            txtAreaInventario.append("-----\n");
        }
    }

    // Método para borrar un vehículo
    private void borrarVehiculo() {
        String numeroIdentificacion = JOptionPane.showInputDialog(this, "Ingrese el Número de Identificación del vehículo a borrar:");
        if (numeroIdentificacion != null) {
            if (inventario.eliminarVehiculo(numeroIdentificacion)) {
                JOptionPane.showMessageDialog(this, "Vehículo borrado exitosamente.");
                mostrarInventario();
            } else {
                JOptionPane.showMessageDialog(this, "Vehículo no encontrado.");
            }
        }
    }

    // Método para modificar un vehículo
    private void modificarVehiculo() {
        String numeroIdentificacion = JOptionPane.showInputDialog(this, "Ingrese el Número de Identificación del vehículo a modificar:");
        Vehiculo vehiculoExistente = inventario.buscarVehiculo(numeroIdentificacion);

        if (vehiculoExistente != null) {
            // Opciones de modificación
            String[] opciones = {"Marca", "Modelo", "Año de Fabricación"};
            int eleccion = JOptionPane.showOptionDialog(
                    this,
                    "¿Qué atributo desea modificar?",
                    "Modificar Vehículo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (eleccion == 0) { // Marca
                String nuevaMarca = JOptionPane.showInputDialog(this, "Nueva Marca:", vehiculoExistente.getMarca());
                vehiculoExistente.setMarca(nuevaMarca);
            } else if (eleccion == 1) { // Modelo
                String nuevoModelo = JOptionPane.showInputDialog(this, "Nuevo Modelo:", vehiculoExistente.getModelo());
                vehiculoExistente.setModelo(nuevoModelo);
            } else if (eleccion == 2) { // Año de Fabricación
                int nuevoAnio = Integer.parseInt(JOptionPane.showInputDialog(this, "Nuevo Año de Fabricación:", vehiculoExistente.getAnioFabricacion()));
                vehiculoExistente.setAnioFabricacion(nuevoAnio);
            }

            JOptionPane.showMessageDialog(this, "Vehículo modificado exitosamente.");
            mostrarInventario(); // Muestra la lista actualizada
        } else {
            JOptionPane.showMessageDialog(this, "Vehículo no encontrado.");
        }
    }

    // Método para guardar el inventario
    private void guardarInventario() {
        try {
            inventario.guardarInventario("inventario.dat");
            JOptionPane.showMessageDialog(this, "Inventario guardado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el inventario: " + e.getMessage());
        }
    }

    // Método para cargar el inventario
    private void cargarInventario() {
        try {
            inventario.cargarInventario("inventario.dat");
            mostrarInventario();
            JOptionPane.showMessageDialog(this, "Inventario cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el inventario: " + e.getMessage());
        }
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnio.setText("");
        txtNumeroIdentificacion.setText("");
        txtNumeroPuertas.setText("");
        txtTipoMotor.setText("");
        txtCapacidadCarga.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventarioGUI().setVisible(true));
    }
}
