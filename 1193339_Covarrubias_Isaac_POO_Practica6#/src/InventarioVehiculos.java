import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioVehiculos {
    private List<Vehiculo> inventario;

    public InventarioVehiculos() {
        inventario = new ArrayList<>();
    }

    // Método para agregar un vehículo al inventario
    public void agregarVehiculo(Vehiculo vehiculo) {
        inventario.add(vehiculo);
        System.out.println("Vehículo agregado al inventario.");
    }

    // Método para obtener la lista de vehículos
    public List<Vehiculo> getInventario() {
        return inventario;
    }

    // Método para buscar un vehículo por Número de Identificación
    public Vehiculo buscarVehiculo(String numeroIdentificacion) {
        for (Vehiculo vehiculo : inventario) {
            if (vehiculo.getNumeroIdentificacion().equals(numeroIdentificacion)) {
                return vehiculo;
            }
        }
        return null;
    }

    // Método para eliminar un vehículo por Número de Identificación
    public boolean eliminarVehiculo(String numeroIdentificacion) {
        Vehiculo vehiculo = buscarVehiculo(numeroIdentificacion);
        if (vehiculo != null) {
            inventario.remove(vehiculo);
            System.out.println("Vehículo eliminado del inventario.");
            return true;
        }
        return false;
    }

    // Método para guardar el inventario en un archivo
    public void guardarInventario(String archivo) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(inventario);
            System.out.println("Inventario guardado en el archivo: " + archivo);
        }
    }

    // Método para cargar el inventario desde un archivo
    public void cargarInventario(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            inventario = (List<Vehiculo>) entrada.readObject();
            System.out.println("Inventario cargado desde el archivo: " + archivo);
        }
    }
}


