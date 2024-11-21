public class House
{
    private Square body;
    private RectangleShape door;
    private Triangle roof;
    private Square window1;
    private Square window2;

    /**
     * Create a new house.
     */
    public House()
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

        // Puerta de la casa
        door = new RectangleShape();
        door.changeSize(20, 40); // Tamaño del rectángulo de la puerta
        door.moveHorizontal(90);
        door.moveVertical(210);
        door.changeColor("yellow");

        // Ventanas de la casa
        window1 = new Square();
        window1.changeSize(20); // Cambiar tamaño de la ventana
        window1.moveHorizontal(60);
        window1.moveVertical(170);
        window1.changeColor("white");

        window2 = new Square();
        window2.changeSize(20);
        window2.moveHorizontal(120);
        window2.moveVertical(170);
        window2.changeColor("white");
    }

    /**
     * Draw the house.
     */
    public void draw()
    {
        body.makeVisible();
        roof.makeVisible();
        door.makeVisible();
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
        door.makeInvisible();
        window1.makeInvisible();
        window2.makeInvisible();
    }
    
       /**
     * Change the color of the entire house (body, roof, door, and windows).
     * 
     * @param bodyColor Color for the body of the house.
     * @param roofColor Color for the roof of the house.
     * @param doorColor Color for the door of the house.
     * @param windowColor Color for the windows of the house.
     */
    public void changeColor(String bodyColor, String roofColor, String doorColor, String windowColor)
    {
        body.changeColor(bodyColor);
        roof.changeColor(roofColor);
        door.changeColor(doorColor);
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
        door.moveHorizontal(x);
        door.moveVertical(y);
        window1.moveHorizontal(x);
        window1.moveVertical(y);
        window2.moveHorizontal(x);
        window2.moveVertical(y);
    }
}
