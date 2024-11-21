import java.util.ArrayList;
import java.util.Scanner;

public class Jugador{
    private ArrayList<Carta> mano;
    private String nombre="Jugador ";
// 	private int numJugadores;


    public Jugador(Baraja mazo,String nombre){
        this.nombre=this.nombre+nombre;
        mano= new ArrayList<Carta>();
        this.repartir(mazo);
    }


    public void repartir(Baraja mazo){
        for(int i=0;i<7;i++){
            mano.add(mazo.cartas.get(0));
            mazo.cartas.remove(0);
        }

    }
    /*recibe una carta, la ultima del monton
    retornará un cero, si tiró una carta normal y no necesito modificar el estado del juego o
    arruinar al jugador siguiente :v
    retornará:
    1: Saltar1
    2: Reversa
    3: Roba 2
    4:COmodin
    5:COmodin roba 4
    */
    public int tirarCarta(Tablero monton, Baraja b1){
        System.out.flush();
        System.out.print("Carta en mesa: ");
        monton.carta.getCara();
        int auxiliar=-1;
        Scanner teclado=new Scanner(System.in);
        while(auxiliar==-1){
            System.out.println("\nTurno: "+nombre);
            this.imprimeBaraja();
            if(verificarSiNoCome(monton,b1)){
                System.out.println("¿Que carta desea Tirar?");
                int cartaAtirar=teclado.nextInt();
                auxiliar=comprobarCarta(monton,cartaAtirar);
            }else{
                while(verificarSiNoCome(monton,b1)==false){
                    System.out.println(nombre+"debe comer de la baraja");
                    mano.add(b1.cartas.get(0));
                    b1.cartas.remove(0);
                }
            }

        }
        return auxiliar;
    }

    public int comprobarCarta(Tablero monton, int cartaAtirar) {
        int aRetornar = 0;
        Carta cartaJugada = mano.get(cartaAtirar - 1);

        // Verifica si la carta jugada es válida según el color o valor
        if (cartaJugada.getValor() <= 9) {
            if (monton.carta.getValor() == cartaJugada.getValor() || monton.carta.getColor().equals(cartaJugada.getColor())) {
                monton.carta = cartaJugada;  // Actualiza el tablero
                mano.remove(cartaAtirar - 1);  // Elimina la carta de la mano
            } else {
                System.out.println("NO puedes tirar esa carta");
                aRetornar = -1;
            }
        } else {
            // Lógica para cartas especiales que coincide solo en valor
            if (monton.carta.getValor() == cartaJugada.getValor() || monton.carta.getColor().equals(cartaJugada.getColor())) {
                monton.carta = cartaJugada;
                mano.remove(cartaAtirar - 1);
                aRetornar = cartaJugada.getValor() - 9;  // Retorna 1 para Saltar, 2 para Reversa, 3 para Roba 2
            } else if (cartaJugada.getValor() == 13 || cartaJugada.getValor() == 14) {
                mano.remove(cartaAtirar - 1);
                Scanner teclado = new Scanner(System.in);
                System.out.println("Elige un color: 1. Rojo, 2. Azul, 3. Verde, 4. Amarillo");
                int colorElegido = teclado.nextInt();
                String nuevoColor = asignarColor(colorElegido);
                monton.carta = new Carta(nuevoColor, cartaJugada.getValor());
                aRetornar = cartaJugada.getValor() == 13 ? 4 : 5;
            } else {
                System.out.println("NO puedes tirar esa carta");
                aRetornar = -1;
            }
        }
        return aRetornar;
    }



    // Método auxiliar para asignar color a la carta comodín
    public String asignarColor(int colorElegido) {
        switch (colorElegido) {
            case 1: return "Rojo";
            case 2: return "Azul";
            case 3: return "Verde";
            case 4: return "Amarillo";
            default: return "Rojo";
        }
    }


    public boolean verificarSiNoCome(Tablero monton, Baraja b1){
        boolean bandera=false;
        for (Carta c :  mano ) {
            if(c.getValor()>12 || c.getValor()==monton.carta.getValor() || c.getColor()==monton.carta.getColor())
                bandera = true;
        }
        return bandera;

    }

    public void imprimeBaraja(){
        for (Carta c :  mano ) {
            c.getCara();
        }
        System.out.println(" ");



    }

    public ArrayList<Carta> getMano(){
        return mano;
    }
    public String getNombre(){
        return nombre;
    }

}
