public class Camion extends Vehiculo {
    private static final long serialVersionUID = 1L;

    private double capacidadCarga;

    public Camion(String marca, String modelo, int anioFabricacion, String numeroIdentificacion, double capacidadCarga) {
        super(marca, modelo, anioFabricacion, numeroIdentificacion);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Camion" +
                "\nCapacidad de Carga: " + capacidadCarga + " toneladas";
    }
}




