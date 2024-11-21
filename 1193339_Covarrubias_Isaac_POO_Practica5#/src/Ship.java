class Ship {
    private String name;
    private int size;
    private boolean[] hits; // Array de hits para cada parte del barco

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hits = new boolean[size];
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    // Verifica si el barco está hundido
    public boolean isSunk() {
        for (boolean hit : hits) {
            if (!hit) {
                return false; // Si hay alguna parte que no ha sido golpeada, no está hundido
            }
        }
        return true; // Si todas las partes están golpeadas, está hundido
    }

    // Marcar una parte del barco como golpeada
    public void hit(int index) {
        if (index >= 0 && index < size) {
            hits[index] = true;
        }
    }
}
