import java.util.ArrayList;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        int numJugadores = 0;
        Baraja b1 = new Baraja();
        Scanner teclado = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

        // Preguntar el número de jugadores
        while (numJugadores < 2 || numJugadores > 4) {
            System.out.println("¿Cuántos jugadores serán?");
            numJugadores = teclado.nextInt();
            if (numJugadores < 2) {
                System.out.println("No puedes jugar solo");
            } else if (numJugadores > 4) {
                System.out.println("Solo pueden jugar 4");
            }
        }

        // Crear jugadores
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador(b1, String.valueOf(i)));
        }

        // Inicializar el tablero con una carta válida que no sea especial
        int k = 0;
        while (b1.cartas.get(k).getValor() > 9) {
            k++;
        }
        Tablero tab = new Tablero(b1.cartas.get(k));
        b1.cartas.remove(k);

        int index = 0;
        int aux;
        boolean bandera = true;
        int sentido = 1; // 1 para sentido normal, -1 para sentido inverso

        // Juego principal
        while (bandera) {
            aux = jugadores.get(index).tirarCarta(tab, b1);
            if (aux == 0) {
                if (jugadores.get(index).getMano().size() < 1) {
                    bandera = false;
                } else {
                    index = (index + sentido + numJugadores) % numJugadores;
                }
            } else if (aux == 1) { // Saltar Turno
                if (jugadores.get(index).getMano().size() < 1) {
                    bandera = false;
                } else {
                    index = (index + 2 * sentido + numJugadores) % numJugadores;
                }
            } else if (aux == 2) { // Reversa
                if (jugadores.get(index).getMano().size() < 1) {
                    bandera = false;
                } else {
                    sentido *= -1; // Cambiar el sentido del juego
                    index = (index + sentido + numJugadores) % numJugadores;
                }
            } else if (aux == 3) { // Roba 2
                int siguiente = (index + sentido + numJugadores) % numJugadores;
                jugadores.get(siguiente).getMano().add(b1.cartas.get(0));
                b1.cartas.remove(0);
                jugadores.get(siguiente).getMano().add(b1.cartas.get(0));
                b1.cartas.remove(0);

                if (jugadores.get(index).getMano().size() < 1) {
                    bandera = false;
                } else {
                    index = (index + 2 * sentido + numJugadores) % numJugadores;
                }
            } else if (aux == 4 || aux == 5) { // Comodín o Comodín roba 4
                if (jugadores.get(index).getMano().size() < 1) {
                    bandera = false;
                } else {
                    int cartasAComer = aux == 5 ? 4 : 0;
                    int siguiente = (index + sentido + numJugadores) % numJugadores;
                    for (int i = 0; i < cartasAComer; i++) {
                        jugadores.get(siguiente).getMano().add(b1.cartas.get(0));
                        b1.cartas.remove(0);
                    }
                    index = (index + sentido + numJugadores) % numJugadores;
                }
            }
        }
        System.out.println("------------ " + jugadores.get(index).getNombre() + " gana!!!!");
    }
}

