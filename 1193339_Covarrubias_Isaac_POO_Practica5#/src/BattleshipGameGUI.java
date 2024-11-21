import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleshipGameGUI extends JFrame {
    private Player humanPlayer;
    private Player aiPlayer;
    private JButton[][] humanBoardButtons;
    private JButton[][] aiBoardButtons;
    private JTextArea infoTextArea;

    public BattleshipGameGUI() {
        humanPlayer = new Player("Humano");
        aiPlayer = new Player("Máquina");

        // Configurar la ventana principal
        setTitle("Battleship Game");
        setSize(600, 600);
        setLayout(new BorderLayout());

        // Configurar tableros
        humanBoardButtons = new JButton[10][10];
        aiBoardButtons = new JButton[10][10];

        // Crear panel para los tableros
        JPanel boardsPanel = new JPanel();
        boardsPanel.setLayout(new GridLayout(1, 2));

        // Panel para el tablero del jugador
        JPanel humanBoardPanel = new JPanel();
        humanBoardPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.addActionListener(new HumanShotListener(i, j));
                // Borde más grueso
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                humanBoardButtons[i][j] = button;
                humanBoardPanel.add(button);
            }
        }

        // Panel para el tablero de la máquina
        JPanel aiBoardPanel = new JPanel();
        aiBoardPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setEnabled(false); // Los botones de la máquina están deshabilitados
                // Borde más grueso
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                aiBoardButtons[i][j] = button;
                aiBoardPanel.add(button);
            }
        }

        boardsPanel.add(humanBoardPanel);
        boardsPanel.add(aiBoardPanel);

        // Configurar el área de texto para mostrar información
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        // Añadir componentes a la ventana
        add(boardsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Configurar el cierre de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Método setup para colocar los barcos en los tableros
    public void setup() {
        // Colocar barcos en el tablero humano
        humanPlayer.placeShip(new Ship("Portaaviones", 4), 1, 1, true);
        humanPlayer.placeShip(new Ship("Crucero 1", 3), 3, 3, false);
        humanPlayer.placeShip(new Ship("Crucero 2", 3), 5, 5, true);
        humanPlayer.placeShip(new Ship("Destructor 1", 2), 7, 0, false);
        humanPlayer.placeShip(new Ship("Destructor 2", 2), 8, 4, true);

        // Colocar barcos en el tablero de la máquina
        aiPlayer.placeShip(new Ship("Portaaviones", 4), 0, 0, true);
        aiPlayer.placeShip(new Ship("Crucero A", 3), 2, 2, false);
        aiPlayer.placeShip(new Ship("Crucero B", 3), 4, 6, true);
        aiPlayer.placeShip(new Ship("Destructor A", 2), 6, 1, false);
        aiPlayer.placeShip(new Ship("Destructor B", 2), 9, 7, true);

        updateBoards();
    }

    // Método para actualizar los tableros
    public void updateBoards() {
        // Actualiza el tablero humano
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char cell = humanPlayer.getBoard().getCell(i, j);
                JButton button = humanBoardButtons[i][j];
                if (cell == 'S') {
                    button.setBackground(Color.LIGHT_GRAY); // Color gris para barcos
                } else if (cell == 'H') {
                    button.setBackground(Color.RED); // Rojo para golpes
                } else if (cell == 'M') {
                    button.setBackground(Color.CYAN); // Azul para fallos
                } else {
                    button.setBackground(Color.WHITE); // Blanco para celdas vacías
                }
            }
        }

        // Actualiza el tablero de la máquina
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char cell = aiPlayer.getBoard().getCell(i, j);
                JButton button = aiBoardButtons[i][j];
                if (cell == 'H') {
                    button.setBackground(Color.RED); // Rojo para golpes
                } else if (cell == 'M') {
                    button.setBackground(Color.CYAN); // Azul para fallos
                } else {
                    button.setBackground(Color.WHITE); // Blanco para celdas vacías
                }
            }
        }
    }

    // Método para mostrar mensaje de fin de juego y deshabilitar los botones
    public void showGameOver(String message) {
        JOptionPane.showMessageDialog(this, message, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        // Deshabilitar todos los botones de los tableros
        disableAllButtons();
    }

    private void disableAllButtons() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                humanBoardButtons[i][j].setEnabled(false);
                aiBoardButtons[i][j].setEnabled(false);
            }
        }
    }

    // Listener para los disparos del jugador humano
    private class HumanShotListener implements ActionListener {
        private int x, y;

        public HumanShotListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Comprobar si ya se ha disparado en esta celda
            char currentCell = aiPlayer.getBoard().getCell(x, y);
            if (currentCell == 'H' || currentCell == 'M') {
                infoTextArea.append("Ya has disparado en (" + x + ", " + y + "). Elige otra celda.\n");
                return; // No avanzamos al turno de la máquina
            }

            // Disparo del jugador humano
            boolean hit = humanPlayer.shoot(x, y, aiPlayer);
            infoTextArea.append("Tu disparo en (" + x + ", " + y + ") " + (hit ? "golpeó." : "falló.") + "\n");
            updateBoards(); // Actualizar los tableros después del disparo

            // Verificar si la máquina ha perdido todos sus barcos
            if (aiPlayer.allShipsSunk()) {
                infoTextArea.append("¡Has ganado!\n");
                showGameOver("¡Has ganado!");
            } else {
                // Si la máquina no ha perdido, es su turno
                aiTurn();
            }
        }
    }


        // Método para el turno de la máquina
    public void aiTurn() {
        Random rand = new Random();
        int aiX, aiY;
        char targetCell;

        // Elegir una celda no marcada para disparar
        do {
            aiX = rand.nextInt(10);
            aiY = rand.nextInt(10);
            targetCell = humanPlayer.getBoard().getCell(aiX, aiY);
        } while (targetCell == 'H' || targetCell == 'M');

        // Realizar el disparo en la celda seleccionada
        boolean aiHit = aiPlayer.shoot(aiX, aiY, humanPlayer);
        infoTextArea.append("La máquina disparó en (" + aiX + ", " + aiY + ") y " + (aiHit ? "golpeó." : "falló.") + "\n");
        updateBoards();

        if (humanPlayer.allShipsSunk()) {
            infoTextArea.append("La máquina ha ganado.\n");
            showGameOver("La máquina ha ganado.");
        }
    }


    // Método principal para ejecutar el juego
    public static void main(String[] args) {
        BattleshipGameGUI game = new BattleshipGameGUI();
        game.setup();
    }
}
