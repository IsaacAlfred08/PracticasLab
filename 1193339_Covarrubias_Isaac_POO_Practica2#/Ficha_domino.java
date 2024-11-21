public class Ficha_domino {
    // instance variables
    private Square square1 = new Square();
    private Square square2 = new Square();
    private Square square3 = new Square();
    private Square square4 = new Square();
    private Circle[][] circle = new Circle[2][10]; // Cambiado a 10 para el doble 9
    private int valor_sup;
    private int valor_inf;
    private int xPosition;
    private int yPosition;
    private int vuelta;
    private int contador;

    /**
     * Constructor for objects of class Ficha_domino
     */
    public Ficha_domino(int valor_sup, int valor_inf, int xPosition, int yPosition) {
        this.valor_sup = valor_sup;
        this.valor_inf = valor_inf;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        vuelta = 1;
        contador = 0;
        dibujarFicha();
    }

    public void invertirValores() {
        int temp = valor_sup;
        valor_sup = valor_inf;
        valor_inf = temp;
        borrarFicha();
        dibujarFicha();
    }

    public void voltearFicha() {
        borrarFicha(); // Borra la ficha actual

        // Dibuja los cuadrados en la posición correcta
        square1.makeVisible();
        square1.changeColor("black");
        square1.changeSize(40);
        square1.moveFigure(xPosition, yPosition);

        square2.changeColor("black");
        square2.makeVisible();
        square2.changeSize(40);
        square2.moveVertical(40);
        square2.moveFigure(xPosition, yPosition + 35);

        square3.makeVisible();
        square3.changeSize(30);
        square3.changeColor("white");
        square3.moveVertical(5);
        square3.moveHorizontal(5);
        square3.moveFigure(xPosition + 5, yPosition + 5);

        square4.makeVisible();
        square4.changeSize(30);
        square4.changeColor("white");
        square4.moveVertical(45);
        square4.moveHorizontal(5);
        square4.moveFigure(xPosition + 5, yPosition + 40);
    }

    private void borrarFicha() {
        square1.makeInvisible();
        square2.makeInvisible();
        square3.makeInvisible();
        square4.makeInvisible();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) { // Cambiado a 10 para el doble 9
                if (circle[i][j] != null) {
                    circle[i][j].makeInvisible();
                }
            }
        }
    }

    void valorFicha(int nuevoValor_sup, int nuevoValor_inf, int xPosition, int yPosition) {
        borrarFicha();
        valor_sup = nuevoValor_sup; 
        valor_inf = nuevoValor_inf;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        dibujarFicha();
    }
    
    public void ponerFichaHorizontal() {
    borrarFicha(); // Borra la ficha actual antes de cambiar la orientación

    // Dibuja los cuadrados en horizontal
    square1.makeVisible();
    square1.changeColor("black");
    square1.changeSize(40);
    square1.moveFigure(xPosition, yPosition);

    square2.changeColor("black");
    square2.makeVisible();
    square2.changeSize(40);
    square2.moveHorizontal(40);
    square2.moveFigure(xPosition + 35, yPosition);

    square3.makeVisible();
    square3.changeSize(30);
    square3.changeColor("white");
    square3.moveHorizontal(5);
    square3.moveVertical(5);
    square3.moveFigure(xPosition + 5, yPosition + 5);

    square4.makeVisible();
    square4.changeSize(30);
    square4.changeColor("white");
    square4.moveHorizontal(45);
    square4.moveVertical(5);
    square4.moveFigure(xPosition + 40, yPosition + 5);

    // Reubicar los círculos
    for (int i = 0; i < 2; i++) {
        int valor = (i == 0) ? valor_sup : valor_inf;
        for (int j = 0; j < valor; j++) {
            circle[i][j] = new Circle();
            circle[i][j].makeVisible();
            circle[i][j].changeSize(6);
            switch (valor) {
                case 1:
                    circle[i][j].changeColor("teal");
                    break;
                case 2:
                    circle[i][j].changeColor("brown");
                    break;
                case 3:
                    circle[i][j].changeColor("red");
                    break;
                case 4:
                    circle[i][j].changeColor("purple");
                    break;
                case 5:
                    circle[i][j].changeColor("cyan");
                    break;
                case 6:
                    circle[i][j].changeColor("pink");
                    break;
                case 7:
                    circle[i][j].changeColor("orange");
                    break;
                case 8:
                    circle[i][j].changeColor("black");
                    break;
                case 9:
                    circle[i][j].changeColor("gray");
                    break;
            }
            // Posicionamiento de círculos en horizontal
            if (j < 4) {
                circle[i][j].moveFigure(xPosition + (i * 40) + 5, yPosition + 10 + (j * 6));
            } else if (j < 8) {
                circle[i][j].moveFigure(xPosition + (i * 40) + 15, yPosition + 10 + ((j - 4) * 6));
            } else if (j < 10) { // Cambiado a 10 para el doble 9
                circle[i][j].moveFigure(xPosition + (i * 40) + 25, yPosition + 10 + ((j - 8) * 6));
            }
        }
    }
}


    public void dibujarFicha() {
        //cuadrado 1
        square1.makeVisible();
        square1.changeColor("black");
        square1.changeSize(40);
        //cuadrado 2
        square2.changeColor("black");
        square2.makeVisible();
        square2.changeSize(40);
        square2.moveVertical(40);
        //cuadrado 3
        square3.makeVisible();
        square3.changeSize(30);
        square3.changeColor("white");
        square3.moveVertical(5);
        square3.moveHorizontal(5);
        //cuadrado 4
        square4.makeVisible();
        square4.changeSize(30);
        square4.changeColor("white");
        square4.moveVertical(45);
        square4.moveHorizontal(5);

        // mover ficha libremente
        square1.moveFigure(xPosition, yPosition);
        square2.moveFigure(xPosition, yPosition + 35);
        square3.moveFigure(xPosition + 5, yPosition + 5);
        square4.moveFigure(xPosition + 5, yPosition + 40);
        
        for (int i = 0; i < 2; i++) {
            int valor = (i == 0) ? valor_sup : valor_inf; // Usar valor_sup para i=0 y valor_inf para i=1
            for (int j = 0; j < valor; j++) {
                circle[i][j] = new Circle();
                circle[i][j].makeVisible();
                circle[i][j].changeSize(6);
                switch (valor) {
                    case 1:
                        circle[i][j].changeColor("teal");
                        break;
                    case 2:
                        circle[i][j].changeColor("brown");
                        break;
                    case 3:
                        circle[i][j].changeColor("red");
                        break;
                    case 4:
                        circle[i][j].changeColor("purple");
                        break;
                    case 5:
                        circle[i][j].changeColor("cyan");
                        break;
                    case 6:
                        circle[i][j].changeColor("pink");
                        break;
                    case 7:
                        circle[i][j].changeColor("orange");
                        break;
                    case 8:
                        circle[i][j].changeColor("black");
                        break;
                    case 9:   
                        circle[i][j].changeColor("gray");
                        break;
                }
                // Posicionamiento de círculos
                if (j < 4) {
                    circle[i][j].moveFigure(xPosition + 10 + (j * 6), yPosition + (i * 40) + 5);
                } else if (j < 8) {
                    circle[i][j].moveFigure(xPosition + 10 + ((j - 4) * 6), yPosition + (i * 40) + 15);
                } else if (j < 10) { // Cambiado a 10 para el doble 9
                    circle[i][j].moveFigure(xPosition + 10 + ((j - 8) * 6), yPosition + (i * 40) + 25);
                }
            }
        }
    }
}
