import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class JuegoDomino {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private boolean juegoTerminado;
    private Scanner scanner;

    public JuegoDomino() {
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
        tablero = new Tablero();
        juegoTerminado = false;
        scanner = new Scanner(System.in);
    }

    public void repartirFichas() {
        for (int i = 0; i < 12; i++) {
            jugador1.agregarFicha(tablero.robarFichaCementerio());
            jugador2.agregarFicha(tablero.robarFichaCementerio());
        }
    }

    private Jugador encontrarDobleNueve() {
        Jugador jugadorActual = jugador1;
        boolean dobleNueveEncontrado = false;

        while (!dobleNueveEncontrado && !tablero.cementerioVacio()) {
            if (jugadorActual.tieneFicha(9, 9)) {
                dobleNueveEncontrado = true;
                return jugadorActual;
            } else {
                FichaDomino fichaRobada = tablero.robarFichaCementerio();
                if (fichaRobada != null) {
                    System.out.println(jugadorActual.getNombre() + " roba una ficha del cementerio.");
                    jugadorActual.agregarFicha(fichaRobada);
                    if (fichaRobada.getLado1() == 9 && fichaRobada.getLado2() == 9) {
                        dobleNueveEncontrado = true;
                        return jugadorActual;
                    }
                }
            }
            jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        }

        return null;
    }

public void jugar() {
    repartirFichas();
    jugador1.mostrarMano();
    jugador2.mostrarMano();

    Jugador jugadorConDobleNueve = encontrarDobleNueve();
    if (jugadorConDobleNueve != null) {
        // Agregar el doble nueve al tren Mexicano
        FichaDomino dobleNueve = new FichaDomino(9, 9);
        
        // Determinar si el jugador que tiene el doble nueve es el Jugador 1
        boolean esJugador1 = jugadorConDobleNueve == jugador1;
        
        // Agregar el doble nueve al tren Mexicano
        tablero.agregarFichaTrenMexicano(dobleNueve, esJugador1);
        
        // Eliminar el doble nueve de la mano del jugador que lo jugó
        jugadorConDobleNueve.jugarFicha(9, 9);

        System.out.println(jugadorConDobleNueve.getNombre() + " comienza con el doble nueve.");
    } else {
        System.out.println("No se encontró el doble nueve en las manos ni en el cementerio.");
        return;
    }

    boolean turnoJugador1 = jugadorConDobleNueve == jugador1;

    while (!juegoTerminado) {
        tablero.mostrarTrenes(jugador1, jugador2);

        if (turnoJugador1) {
            System.out.println("Turno de " + jugador1.getNombre());
            if (!turnoJugador(jugador1)) {
                System.out.println(jugador1.getNombre() + " no puede jugar y pasa turno.");
            }
        } else {
            System.out.println("Turno de " + jugador2.getNombre());
            if (!turnoJugador(jugador2)) {
                System.out.println(jugador2.getNombre() + " no puede jugar y pasa turno.");
            }
        }

        if (jugador1.manoVacia() || jugador2.manoVacia() || !tablero.hayMovimientosPosibles(jugador1, jugador2)) {
            juegoTerminado = true;
            determinarGanador();
        }

        turnoJugador1 = !turnoJugador1;
    }
}

private boolean turnoJugador(Jugador jugador) {
    int ultimoLadoIzquierdo = tablero.obtenerUltimoLadoIzquierdo(); // Obtén el lado izquierdo
    int ultimoLadoDerecho = tablero.obtenerUltimoLadoDerecho(); // Obtén el lado derecho
    jugador.mostrarMano();
    System.out.println("Último lado izquierdo del tren mexicano: " + ultimoLadoIzquierdo);
    System.out.println("Último lado derecho del tren mexicano: " + ultimoLadoDerecho);

    System.out.println("Seleccione el índice de la ficha que desea jugar (o -1 para robar): ");
    int indice = scanner.nextInt();

    if (indice == -1) {
        FichaDomino fichaRobada = tablero.robarFichaCementerio();
        if (fichaRobada != null) {
            jugador.agregarFicha(fichaRobada);
            System.out.println(jugador.getNombre() + " robó una ficha: " + fichaRobada);
            return true;
        } else {
            System.out.println("El cementerio está vacío.");
        }
        return false;
    }

    if (indice >= 0 && indice < jugador.getMano().size()) {
        FichaDomino fichaSeleccionada = jugador.getMano().get(indice);
        System.out.println("Ficha seleccionada: " + fichaSeleccionada);

        // Comprobar si la ficha es jugable en ambos lados del tren mexicano
        boolean sePuedeJugar = false;

        if (fichaSeleccionada.getLado1() == ultimoLadoIzquierdo || fichaSeleccionada.getLado1() == ultimoLadoDerecho ||
            fichaSeleccionada.getLado2() == ultimoLadoIzquierdo || fichaSeleccionada.getLado2() == ultimoLadoDerecho) {
            sePuedeJugar = true;
        }

        if (sePuedeJugar) {
            // Lógica de voltear la ficha para Jugador 1
            if (jugador == jugador1) {
                System.out.println("Jugador 1 está jugando.");
                if (fichaSeleccionada.getLado1() == ultimoLadoIzquierdo || fichaSeleccionada.getLado1() == ultimoLadoDerecho) {
                    fichaSeleccionada.voltear(); // Voltear si el lado 1 coincide
                    System.out.println("Ficha volteada: " + fichaSeleccionada);
                }
            } else { // Lógica de voltear la ficha para Jugador 2
                System.out.println("Jugador 2 está jugando.");
                if (fichaSeleccionada.getLado2() == ultimoLadoIzquierdo || fichaSeleccionada.getLado2() == ultimoLadoDerecho) {
                    fichaSeleccionada.voltear(); // Voltear si el lado 2 coincide
                    System.out.println("Ficha volteada: " + fichaSeleccionada);
                }
            }

            // Agregar la ficha al tren Mexicano
            tablero.agregarFichaTrenMexicano(fichaSeleccionada, jugador == jugador1);
            jugador.jugarFicha(fichaSeleccionada.getLado1(), fichaSeleccionada.getLado2());
            System.out.println(jugador.getNombre() + " jugó " + fichaSeleccionada);
            return true;
        } else {
            System.out.println("La ficha seleccionada no es válida para jugar en el tren Mexicano.");
        }
    } else {
        System.out.println("Índice inválido.");
    }

    return false;
}


    private void determinarGanador() {
        int puntosJugador1 = jugador1.contarPuntos();
        int puntosJugador2 = jugador2.contarPuntos();

        System.out.println("Puntos de " + jugador1.getNombre() + ": " + puntosJugador1);
        System.out.println("Puntos de " + jugador2.getNombre() + ": " + puntosJugador2);

        if (puntosJugador1 < puntosJugador2) {
            System.out.println(jugador1.getNombre() + " gana!");
        } else if (puntosJugador2 < puntosJugador1) {
            System.out.println(jugador2.getNombre() + " gana!");
        } else {
            System.out.println("Es un empate!");
        }
    }
}
