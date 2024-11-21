import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Baraja {
    static ArrayList<Carta> cartas;
    int numCartas;
    private static final String[] COLORES = {"Rojo", "Azul", "Verde", "Amarillo"};
    private Random random = new Random();

    public Baraja() {
        cartas = new ArrayList<Carta>();
        int j = 0;

        // Crear cartas numéricas
        while (j < 2) {
            for (int i = 1; i <= 9; i++) {
                cartas.add(new Carta("Rojo", i));
                cartas.add(new Carta("Azul", i));
                cartas.add(new Carta("Verde", i));
                cartas.add(new Carta("Amarillo", i));
            }
            j++;
        }

        // Crear cartas especiales con colores aleatorios
        for (int i = 0; i < 8; i++) {
            cartas.add(new Carta(COLORES[random.nextInt(COLORES.length)], 10)); // Saltar Turno
            cartas.add(new Carta(COLORES[random.nextInt(COLORES.length)], 11)); // Reversa
            cartas.add(new Carta(COLORES[random.nextInt(COLORES.length)], 12)); // Roba 2
        }

        // Crear cartas comodín
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta("Ninguno", 13)); // Comodín
            cartas.add(new Carta("Ninguno", 14)); // Comodín Roba 4
        }

        cartas.add(new Carta("Rojo", 0));
        cartas.add(new Carta("Azul", 0));
        cartas.add(new Carta("Verde", 0));
        cartas.add(new Carta("Amarillo", 0));

        Collections.shuffle(cartas);
        numCartas = cartas.size();
    }

    public void imprimeBaraja() {
        int contador = 0;
        System.out.println(cartas.size());
        for (Carta c : cartas) {
            c.getCara();
            contador++;
        }
        System.out.println(contador);
    }

    public ArrayList<Carta> getBaraja() {
        return cartas;
    }
}
