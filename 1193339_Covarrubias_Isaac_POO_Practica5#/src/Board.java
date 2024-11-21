public class Board {
    private char[][] grid;
    private Ship[] ships;

    public Board() {
        grid = new char[10][10];
        ships = new Ship[5]; // Ajusta el tamaño según la cantidad de barcos
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = ' '; // Inicializamos el tablero con espacios vacíos
            }
        }
    }

    // Coloca un barco en el tablero
    public void placeShip(Ship ship, int x, int y, boolean horizontal) {
        for (int i = 0; i < ship.getSize(); i++) {
            if (horizontal) {
                grid[x][y + i] = 'S'; // Marca la posición del barco en horizontal
            } else {
                grid[x + i][y] = 'S'; // Marca la posición del barco en vertical
            }
        }
    }

    // Verifica si un disparo ha golpeado a un barco
    public boolean shootAt(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X'; // Marca como golpeado (S -> X)
            return true;
        }
        return false;
    }

    // Verifica si todos los barcos han sido hundidos
    public boolean allShipsSunk() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 'S') { // Si hay una celda con un barco no hundido
                    return false;
                }
            }
        }
        return true;
    }

    // Muestra el tablero en la consola
    public void display() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j] + " "); // Muestra el contenido de la celda
            }
            System.out.println(); // Salto de línea después de cada fila
        }
    }

    // Establece el valor en una celda (para los disparos)
    public void setCell(int x, int y, char value) {
        grid[x][y] = value; // Asigna un valor (como 'H' o 'M')
    }

    // Obtiene el valor de una celda en el tablero
    public char getCell(int x, int y) {
        return grid[x][y]; // Retorna el valor en la celda especificada
    }
}
