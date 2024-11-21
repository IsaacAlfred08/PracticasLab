import java.util.ArrayList;
import java.util.Collections;  // Importa Collections para usar shuffle

public class Tablero {
    private ArrayList<FichaDomino> trenMexicano;
    private ArrayList<FichaDomino> cementerio;

    public Tablero() {
        trenMexicano = new ArrayList<>();
        cementerio = generarCementerio();
    }

    private ArrayList<FichaDomino> generarCementerio() {
        ArrayList<FichaDomino> nuevoCementerio = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                nuevoCementerio.add(new FichaDomino(i, j));
            }
        }
        Collections.shuffle(nuevoCementerio);
        return nuevoCementerio;
    }

    public FichaDomino robarFichaCementerio() {
        if (!cementerio.isEmpty()) {
            return cementerio.remove(0);
        }
        return null;
    }

    public boolean cementerioVacio() {
        return cementerio.isEmpty();
    }
    
        public int obtenerUltimoLadoIzquierdo() {
        // Verifica si hay fichas en el tren
        if (!trenMexicano.isEmpty()) {
            return trenMexicano.get(0).getLado1(); // Devuelve el lado 1 de la primera ficha (lado izquierdo)
        }
        return -1; // o algún valor que indique que no hay fichas
    }

    public int obtenerUltimoLadoDerecho() {
        // Verifica si hay fichas en el tren
        if (!trenMexicano.isEmpty()) {
            return trenMexicano.get(trenMexicano.size() - 1).getLado2(); // Devuelve el lado 2 de la última ficha (lado derecho)
        }
        return -1; // o algún valor que indique que no hay fichas
    }

    public int obtenerPrimerLadoMexicano() {
    if (!trenMexicano.isEmpty()) {
        FichaDomino primeraFicha = trenMexicano.get(0); // Obtener la primera ficha
        return primeraFicha.getLado1();  // Retorna el primer lado de la primera ficha en el tren
    }
    return -1;  // Si no hay fichas en el tren Mexicano
}


    public void agregarFichaTrenMexicano(FichaDomino ficha, boolean esJugador1) {
    if (esJugador1) {
        // Agregar la ficha del jugador 1 al inicio
        trenMexicano.add(0, ficha);  // Agregar al inicio
    } else {
        // Agregar la ficha del jugador 2 al final
        trenMexicano.add(ficha);  // Agregar al final
    }
}

    public int obtenerUltimoLadoMexicano() {
        if (!trenMexicano.isEmpty()) {
            FichaDomino ultimaFicha = trenMexicano.get(trenMexicano.size() - 1); // Obtener la última ficha
            return ultimaFicha.getLado2();  // Retorna el segundo lado de la última ficha en el tren
        }
        return -1;  // Si no hay fichas en el tren Mexicano
    }

    // Mostrar trenes de ambos jugadores y el tren Mexicano
    public void mostrarTrenes(Jugador jugador1, Jugador jugador2) {
        System.out.println("Tren Mexicano:");
        mostrarTren(trenMexicano);

        System.out.println(jugador1.getNombre() + "'s tren (izquierda):");
        mostrarTren(jugador1.getTren());

        System.out.println(jugador2.getNombre() + "'s tren (derecha):");
        mostrarTren(jugador2.getTren());
    }

    private void mostrarTren(ArrayList<FichaDomino> tren) {
        for (FichaDomino ficha : tren) {
            System.out.print("[" + ficha.getLado1() + "|" + ficha.getLado2() + "] ");
        }
        System.out.println();  // Para salto de línea después de mostrar todas las fichas
    }

    public boolean hayMovimientosPosibles(Jugador jugador1, Jugador jugador2) {
        int ultimoLadoMexicano = obtenerUltimoLadoMexicano();

        for (FichaDomino ficha : jugador1.getMano()) {
            if (ficha.getLado1() == ultimoLadoMexicano || ficha.getLado2() == ultimoLadoMexicano) {
                return true;
            }
        }
        for (FichaDomino ficha : jugador2.getMano()) {
            if (ficha.getLado1() == ultimoLadoMexicano || ficha.getLado2() == ultimoLadoMexicano) {
                return true;
            }
        }
        return false;
    }
}




