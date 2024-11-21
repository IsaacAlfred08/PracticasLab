public class Tablero {
    static Carta carta;

    public Tablero(Carta cartaInicial) {
        this.carta = cartaInicial;
    }

    public void actualizarCarta(Carta nuevaCarta) {
        this.carta = nuevaCarta;
        System.out.print("Carta en mesa: ");
        carta.getCara();  // Imprime la nueva carta en el tablero
    }
}
