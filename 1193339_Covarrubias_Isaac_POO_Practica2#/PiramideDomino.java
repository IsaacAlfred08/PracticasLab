public class PiramideDomino {
    private Ficha_domino[][] fichas;
    
    public PiramideDomino() {
        fichas = new Ficha_domino[10][];  // La pirámide tiene 10 filas, ya que es doble 9
    }

    public void dibujarPiramide() {
        int xInicial = 50;  // Posición inicial en X
        int yInicial = 50;  // Posición inicial en Y
        int offsetX = 90;   // Espacio entre las fichas horizontalmente (ajustado para fichas horizontales)
        int offsetY = 50;   // Espacio entre las filas

        // Comenzamos desde la fila más grande (fila 9) hacia la fila 0
        for (int fila = 9; fila >= 0; fila--) {
            // Inicializa la fila con el número adecuado de fichas
            fichas[fila] = new Ficha_domino[10 - fila];
            
            for (int columna = 0; columna < 10 - fila; columna++) {
                // Calcula las posiciones X e Y para cada ficha
                int xPos = xInicial + columna * offsetX;
                int yPos = yInicial + (9 - fila) * offsetY;  // La fila superior está más abajo
                
                // El valor superior y el valor inferior de la ficha
                int valorSup = 9 - fila;
                int valorInf = columna;

                // Crea la ficha con los valores calculados y la posición
                fichas[fila][columna] = new Ficha_domino(valorSup, valorInf, xPos, yPos);
                
                // Coloca la ficha en horizontal
                fichas[fila][columna].ponerFichaHorizontal();
            }
        }
    }
}



