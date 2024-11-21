public class LetraCEnCirculo {
    private Circle circulo;
    private LetraC letraC;
    private int tamanoCirculo;  // Tamaño del círculo, necesario para ajustar las posiciones

    public LetraCEnCirculo() {
        // Crear el círculo
        circulo = new Circle();
        tamanoCirculo = 6; // Tamaño inicial del círculo
        circulo.changeSize(tamanoCirculo);
        circulo.moveHorizontal(180);
        circulo.moveVertical(90);
        circulo.changeColor("blue");

        // Crear la letra "C"
        letraC = new LetraC(tamanoCirculo / 5); // Usar una proporción del tamaño del círculo
        letraC.move(100, 60); // Posicionar la letra "C" dentro del círculo
    }

    public void moveFigure(int x, int y) {
        circulo.moveHorizontal(x);
        circulo.moveVertical(y);
        letraC.move(x, y);
    }

    public void draw() {
        circulo.makeVisible();
        letraC.draw();
    }

    public void changeColor(String circuloColor, String letraCColor) {
        circulo.changeColor(circuloColor);
        letraC.changeColor(letraCColor);
    }

    public void erase() {
        circulo.makeInvisible();
        letraC.erase();
    }

    // Método para cambiar el tamaño del círculo y la letra "C"
    public void changeSize(int nuevoTamano) {
        this.tamanoCirculo = nuevoTamano;
        circulo.changeSize(tamanoCirculo);
        
        // Cambiar el tamaño de la letra "C" proporcionalmente
        letraC.changeSize(nuevoTamano / 5); // Ajustar según proporción deseada
        letraC.move(0,0); // Reubicar la letra "C" si es necesario
    }

    public static void main(String[] args) {
        LetraCEnCirculo dibujo = new LetraCEnCirculo();
        dibujo.draw();

        // Ejemplo de cambiar el tamaño
        dibujo.changeSize(100); // Cambiar el tamaño del círculo y la letra "C"
        dibujo.draw(); // Volver a dibujar después de cambiar el tamaño
    }
}







