public class FichaDomino {
    private int lado1;
    private int lado2;

    public FichaDomino(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    // Método para voltear la ficha
    public void voltear() {
        int temp = lado1;
        lado1 = lado2;
        lado2 = temp;
    }

    @Override
    public String toString() {
        return "[" + lado1 + "|" + lado2 + "]";
    }
}
