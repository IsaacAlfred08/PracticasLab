public class Auto extends Vehiculo {
    private static final long serialVersionUID = 1L;

    private int numeroPuertas;

    public Auto(String marca, String modelo, int anioFabricacion, String numeroIdentificacion, int numeroPuertas) {
        super(marca, modelo, anioFabricacion, numeroIdentificacion);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Auto" +
                "\nNúmero de Puertas: " + numeroPuertas;
    }
}


