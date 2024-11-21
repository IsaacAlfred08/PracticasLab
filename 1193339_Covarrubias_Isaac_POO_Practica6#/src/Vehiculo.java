import java.io.Serializable;

public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String marca;
    protected String modelo;
    protected int anioFabricacion;
    protected String numeroIdentificacion;

    public Vehiculo(String marca, String modelo, int anioFabricacion, String numeroIdentificacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    // Getters y Setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }


    @Override
    public String toString() {
        return "Vehículo:" +
                "\nMarca: " + marca +
                "\nModelo: " + modelo +
                "\nAño de Fabricación: " + anioFabricacion +
                "\nNúmero de Identificación: " + numeroIdentificacion;
    }
}




