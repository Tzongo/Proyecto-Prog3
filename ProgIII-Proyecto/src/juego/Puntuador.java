package juego;

public class Puntuador
{
    int puntosAcum;
    
    public Puntuador() {
        this.puntosAcum = 0;
    }
    
    public void addPuntos(final int puntos) {
        this.puntosAcum += puntos;
    }
    
    public int getPuntos() {
        return this.puntosAcum;
    }
    
    public static void main(final String[] args) {
        final Puntuador p = new Puntuador();
        p.addPuntos(5);
        p.addPuntos(3);
        System.out.println(p.getPuntos());
    }
}
