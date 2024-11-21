import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<FichaDomino> mano;
    private ArrayList<FichaDomino> tren;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.tren = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarFicha(FichaDomino ficha) {
        mano.add(ficha);
    }
    

    public boolean tieneFicha(int lado1, int lado2) {
        for (FichaDomino ficha : mano) {
            if ((ficha.getLado1() == lado1 && ficha.getLado2() == lado2) ||
                (ficha.getLado1() == lado2 && ficha.getLado2() == lado1)) {
                return true;
            }
        }
        return false;
    }

    public void jugarFicha(int lado1, int lado2) {
    for (int i = 0; i < mano.size(); i++) {
        FichaDomino ficha = mano.get(i);
        if ((ficha.getLado1() == lado1 && ficha.getLado2() == lado2) ||
            (ficha.getLado1() == lado2 && ficha.getLado2() == lado1)) {
            mano.remove(i);  // Elimina la ficha de la mano del jugador
            break;
        }
    }
}

    
       public ArrayList<FichaDomino> getMano() {
        return mano;
    }

    public ArrayList<FichaDomino> getTren() {
        return tren;
    }

    public void mostrarMano() {
        System.out.println(nombre + " tiene las siguientes fichas:");
        for (int i = 0; i < mano.size(); i++) {
            FichaDomino ficha = mano.get(i);
            System.out.println((i + 1) + "- [" + ficha.getLado1() + "|" + ficha.getLado2() + "]");
        }
    }

    public boolean puedeJugar(int ultimoLadoMexicano) {
        for (FichaDomino ficha : mano) {
            if (ficha.getLado1() == ultimoLadoMexicano || ficha.getLado2() == ultimoLadoMexicano) {
                return true;
            }
        }
        return false;
    }

    public boolean manoVacia() {
        return mano.isEmpty();
    }

    public int contarPuntos() {
        int puntos = 0;
        for (FichaDomino ficha : mano) {
            puntos += ficha.getLado1() + ficha.getLado2();
        }
        return puntos;
    }

}


