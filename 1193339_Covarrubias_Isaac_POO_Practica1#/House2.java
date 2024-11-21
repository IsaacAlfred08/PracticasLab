public class House2
{
    private Square body;
    private RectangleShape door1;
    private RectangleShape door2;
    private Triangle roof;
    private Square window1;
    private Square window2;
    private Person person; // Persona asociada a la casa

    /**
     * Create a new house with two doors.
     */
    public House2()
    {
        // Cuerpo de la casa
        body = new Square();
        body.changeSize(100); // Cambiar tamaño del cuerpo
        body.moveVertical(150);
        body.moveHorizontal(50);
        body.changeColor("blue");

        // Techo de la casa
        roof = new Triangle();
        roof.changeSize(60, 140); // Cambiar tamaño del techo
        roof.moveHorizontal(196);
        roof.moveVertical(70);
        roof.changeColor("red");

        // Primera puerta (izquierda)
        door1 = new RectangleShape();
        door1.changeSize(20, 40); // Tamaño de la puerta izquierda
        door1.moveHorizontal(60); // Ajustar posición en la izquierda
        door1.moveVertical(210);
        door1.changeColor("yellow");

        // Segunda puerta (derecha)
        door2 = new RectangleShape();
        door2.changeSize(20, 40); // Tamaño de la puerta derecha
        door2.moveHorizontal(130); // Ajustar posición en la derecha
        door2.moveVertical(210);
        door2.changeColor("yellow");

        // Ventanas de la casa
        window1 = new Square();
        window1.changeSize(20); // Cambiar tamaño de la ventana
        window1.moveHorizontal(70);
        window1.moveVertical(170);
        window1.changeColor("white");

        window2 = new Square();
        window2.changeSize(20);
        window2.moveHorizontal(110);
        window2.moveVertical(170);
        window2.changeColor("white");

        // Inicializar la persona
        person = new Person();
    }

    /**
     * Draw the house with two doors.
     */
    public void draw()
    {
        body.makeVisible();
        roof.makeVisible();
        door1.makeVisible();
        door2.makeVisible();
        window1.makeVisible();
        window2.makeVisible();
    }

    /**
     * Erase the house.
     */
    public void erase()
    {
        body.makeInvisible();
        roof.makeInvisible();
        door1.makeInvisible();
        door2.makeInvisible();
        window1.makeInvisible();
        window2.makeInvisible();
    }

    /**
     * Change the color of the entire house (body, roof, doors, and windows).
     * 
     * @param bodyColor Color for the body of the house.
     * @param roofColor Color for the roof of the house.
     * @param doorColor Color for the doors of the house.
     * @param windowColor Color for the windows of the house.
     */
    public void changeColor(String bodyColor, String roofColor, String doorColor, String windowColor)
    {
        body.changeColor(bodyColor);
        roof.changeColor(roofColor);
        door1.changeColor(doorColor);
        door2.changeColor(doorColor);
        window1.changeColor(windowColor);
        window2.changeColor(windowColor);
    }

    /**
     * Move the house to a new position (x, y) on the canvas.
     * 
     * @param x Horizontal movement (relative to the current position).
     * @param y Vertical movement (relative to the current position).
     */
    public void moveHouse(int x, int y)
    {
        body.moveHorizontal(x);
        body.moveVertical(y);
        roof.moveHorizontal(x);
        roof.moveVertical(y);
        door1.moveHorizontal(x);
        door1.moveVertical(y);
        door2.moveHorizontal(x);
        door2.moveVertical(y);
        window1.moveHorizontal(x);
        window1.moveVertical(y);
        window2.moveHorizontal(x);
        window2.moveVertical(y);
    }

    /**
     * Animate the person entering through one door and exiting through the other.
     */
    public void animatePerson()
    {
        // Posicionar la persona frente a la primera puerta (izquierda)
        person.moveHorizontal(60); // Posición inicial frente a la puerta izquierda
        person.moveVertical(180);  // Ajustar altura
        person.makeVisible();

        // Simular que la persona entra a la casa (desaparece temporalmente)
        person.slowMoveHorizontal(30);
        person.makeInvisible();

        // Simular que la persona aparece frente a la segunda puerta (derecha)
        person.moveHorizontal(80); // Mover a la posición de la segunda puerta
        person.makeVisible();

        // La persona se mueve fuera de la casa desde la segunda puerta
        person.slowMoveHorizontal(40);
    }
}

