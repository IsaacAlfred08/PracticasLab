public class Motocicleta extends Vehiculo {
    private static final long serialVersionUID = 1L;

    private String tipoMotor;

    public Motocicleta(String marca, String modelo, int anioFabricacion, String numeroIdentificacion, String tipoMotor) {
        super(marca, modelo, anioFabricacion, numeroIdentificacion);
        this.tipoMotor = tipoMotor;
    }


    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Motocicleta" +
                "\nTipo de Motor: " + tipoMotor;
    }
}




