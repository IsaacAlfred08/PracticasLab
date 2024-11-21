public class PiramideInversa {
    public static void main(String[] args) {
        int xPosition = 20; // Posición inicial en X
        int yPosition = 20; // Posición inicial en Y
        int row = 10; // Número de filas en la pirámide
        int deltaY = 70; // Ajuste en Y entre cada fila
        int deltaX = 40; // Ajuste en X entre cada ficha en una fila

        // Bucle para dibujar la pirámide inversa de fichas de dominó
        for (int i = 0; i < row; i++) {
            int x = xPosition; // Reiniciamos la posición en X para cada fila
            int y = yPosition + i * deltaY; // Ajuste de posición en Y para cada fila

            // Bucle para dibujar las fichas en la fila actual
            for (int j = 0; j < row - i; j++) {
                Ficha_domino ficha = new Ficha_domino(row - i - 1, j, x, y);
                x += deltaX; // Ajuste de posición en X para la siguiente ficha
            }
            
            // Ajuste de posición en Y para la siguiente fila
            y += deltaY;
        }
    }
}
