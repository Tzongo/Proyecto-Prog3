package juego;

public class CoordTablero
{
    private int fila;
    private int columna;
    
    public CoordTablero(final int fila, final int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public int getFila() {
        return this.fila;
    }
    
    public void setFila(final int fila) {
        this.fila = fila;
    }
    
    public int getColumna() {
        return this.columna;
    }
    
    public void setColumna(final int columna) {
        this.columna = columna;
    }
    
    public void setCoord(final int fila, final int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public double distanciaCon(final CoordTablero c2) {
        return Math.sqrt(Math.pow(Math.abs(c2.fila - this.fila), 2.0) + Math.pow(Math.abs(c2.columna - this.columna), 2.0));
    }
    
    @Override
    protected Object clone() {
        return new CoordTablero(this.fila, this.columna);
    }
    
    @Override
    public boolean equals(final Object coor2) {
        if (coor2 instanceof CoordTablero) {
            final CoordTablero ct = (CoordTablero)coor2;
            return this.fila == ct.fila && this.columna == ct.columna;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "(" + this.fila + "," + this.columna + ")";
    }
    
    public static void main(final String[] args) {
        final CoordTablero c1 = new CoordTablero(0, 7);
        final CoordTablero c2 = new CoordTablero(-2, -1);
        System.out.println("Una coordenada: " + c1);
        System.out.println("Otra coordenada: " + c2);
        final CoordTablero c3 = (CoordTablero)c2.clone();
        System.out.println("Tercera -copia de la segunda-: " + c3);
        System.out.println("Segunda y tercera " + (c2.equals(c3) ? "" : "no ") + "son iguales.");
        c3.setCoord(1, 5);
        System.out.println("Cambiando la coordenada de la tercera a (1,5)...");
        System.out.println("Segunda coordenada: " + c2);
        System.out.println("Segunda y tercera " + (c2.equals(c3) ? "" : "no ") + "son iguales.");
    }
}
