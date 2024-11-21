public class Player {
    private String name;
    private Board board;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    // Método para colocar un barco
    public void placeShip(Ship ship, int x, int y, boolean horizontal) {
        board.placeShip(ship, x, y, horizontal);
    }

    // Método para disparar a una posición en el tablero enemigo
    public boolean shoot(int x, int y, Player opponent) {
        char targetCell = opponent.getBoard().getCell(x, y);
        boolean hit = false;

        if (targetCell == 'S') {  // Golpe a un barco
            opponent.getBoard().setCell(x, y, 'H');  // Marcamos la celda como golpeada con 'H'
            hit = true;
        } else if (targetCell == ' ') {  // Agua (fallo)
            opponent.getBoard().setCell(x, y, 'M');  // Marcamos la celda como fallo con 'M'
        }

        return hit;  // Retornamos verdadero si fue un golpe, falso si falló
    }

    // Método para verificar si todos los barcos están hundidos
    public boolean allShipsSunk() {
        return board.allShipsSunk();  // Retorna verdadero si no hay barcos restantes
    }
}

