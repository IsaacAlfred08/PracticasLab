public class LetraC {
    private Square[] letraC;
    
    public LetraC(int tamanoCuadrado) {
        // Crear los cuadrados para la letra "C"
        letraC = new Square[6];
        for (int i = 0; i < letraC.length; i++) {
            letraC[i] = new Square();
            letraC[i].changeSize(tamanoCuadrado); // Tamaño inicial de cada cuadrado
            letraC[i].changeColor("black");
        }

        // Posicionar los cuadrados en forma de "C"
        posicionarCuadrados();
    }

    private void posicionarCuadrados() {
        // Ajustamos las posiciones en función del tamaño del cuadrado
        int ajuste = 10; // Factor de ajuste basado en el tamaño de los cuadrados

        letraC[0].moveHorizontal(20);  // Parte superior de la C
        letraC[0].moveVertical(10);

        letraC[1].moveHorizontal(25);  // Parte superior de la C
        letraC[1].moveVertical(10);

        letraC[2].moveHorizontal(10);  // Parte izquierda superior de la C
        letraC[2].moveVertical(18);

        letraC[3].moveHorizontal(10);  // Parte izquierda inferior de la C
        letraC[3].moveVertical(20);

        letraC[4].moveHorizontal(20);  // Parte inferior de la C
        letraC[4].moveVertical(30);

        letraC[5].moveHorizontal(25);  // Parte derecha inferior de la C
        letraC[5].moveVertical(30);
    }
    
    public void changeSize(int nuevoTamano) {
        for (Square cuadrado : letraC) {
            cuadrado.changeSize(nuevoTamano); // Cambia el tamaño de cada cuadrado
        }
        posicionarCuadrados(); // Reajusta las posiciones después de cambiar el tamaño
    }

    public void move(int x, int y) {
        for (Square cuadrado : letraC) {
            cuadrado.moveHorizontal(x);
            cuadrado.moveVertical(y);
        }
    }

    public void draw() {
        for (Square cuadrado : letraC) {
            cuadrado.makeVisible();
        }
    }

    public void changeColor(String color) {
        for (Square cuadrado : letraC) {
            cuadrado.changeColor(color);
        }
    }

    public void erase() {
        for (Square cuadrado : letraC) {
            cuadrado.makeInvisible();
        }
    }
}

