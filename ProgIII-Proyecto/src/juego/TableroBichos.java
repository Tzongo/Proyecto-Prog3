package juego;

import ventanas.*;
import java.util.*;

public class TableroBichos
{
    private Bicho[][] tablero;
    private int filas;
    private int columnas;
    private int anchoBicho;
    private int altoBicho;
    private VentanaJuegoTablero miVentana;
   
    public int length() {
		return tablero.length;
	}
    
    public TableroBichos(final int f, final int c, final int anchoBicho, final int altoBicho, final VentanaJuegoTablero v) {
        this.filas = f;
        this.columnas = c;
        this.tablero = new Bicho[f][c];
        this.miVentana = v;
        this.distribuyeBichosAlAzar(this.anchoBicho = anchoBicho, this.altoBicho = altoBicho);
        if (this.miVentana != null) {
            this.addTableroAVentana(this.miVentana);
        }
    }
    
    public TableroBichos(final int fc, final VentanaJuegoTablero v) {
        this(fc, fc, v.getAnchoCasilla(), v.getAltoCasilla(), v);
    }
    
    public TableroBichos(final int anchoBicho, final int altoBicho, final VentanaJuegoTablero v) {
        this(4,4, anchoBicho, altoBicho, v);
    }
    
    private void addTableroAVentana(final VentanaJuegoTablero v) {
        for (int f = 0; f < this.getFilas() ; ++f) {
            for (int c = 0; c < this.getColumnas(); ++c) {
                final CoordTablero ct = new CoordTablero(f , c);
                v.addObjeto(this.getObjetoDC(ct).getObjeto(), ct);
            }
        }
    }
    
    public VentanaJuegoTablero getVentana() {
        return this.miVentana;
    }
    
    private void distribuyeBichosAlAzar(final int anchoBicho, final int altoBicho) {
    	
        for (int f = 0; f < this.filas; ++f) {
            for (int c = 0; c < this.columnas; ++c) {
                if ((f == 0 || f==1 || f == this.getFilas() - 1) && (c == 9)) {
                    final Minizombie zombie = new Minizombie(new CoordTablero(f, c), anchoBicho , altoBicho , this);
                    this.tablero[f ][c] = zombie;

                	System.out.println("distri z");
                }
                else if (c == 0 && (f == 0 || f == 1 ||f == 2)) {
                    final Sol sol = new Sol(new CoordTablero(f, c), anchoBicho, altoBicho, this);
                    this.tablero[f ][c] = sol;

                	System.out.println("distri s");
                }
                else if (c == 1 && (f == 0 || f == 1 ||f == 2 )) {
                    final Disparador disp = new Disparador(new CoordTablero(f, c), anchoBicho, altoBicho, this);
                    this.tablero[f][c] = disp;

                	System.out.println("distri d");
                }
                else {
                    final Transparencia caram = new Transparencia(new CoordTablero(f, c),"Transparencia", anchoBicho, altoBicho, this);
                    this.tablero[f][c] = caram;
                    System.out.println("distri t");
                }
            }
        }
    }
    
    public void reponerFilaOculta() {
        final int filaOculta = 0;
        for (int c = 0; c < this.columnas; ++c) {
            if (this.tablero[filaOculta][c] == null) {
                
                final Sol caram = new Sol(new CoordTablero(filaOculta - 1, c), this.anchoBicho, this.altoBicho, this);
                this.tablero[filaOculta][c] = caram;
                if (this.miVentana != null) {
                    final CoordTablero ct = new CoordTablero(filaOculta - 1, c);
                    this.miVentana.addObjeto(this.getObjetoDC(ct).getObjeto(), ct);
                }
            }
        }
    }
    
    public void setBicho(final Bicho c, final CoordTablero ct) {
        this.tablero[ct.getFila() ][ct.getColumna()] = c;
    }
    
    public Bicho getBicho(final CoordTablero ct) {
    	final Bicho odc = this.tablero[ct.getFila() ][ct.getColumna()];
        if (odc instanceof Bicho) {
            return (Bicho)odc;
        }
        return null;
    }
    
    
    public Bicho getObjetoDC(final CoordTablero ct) {
    	//System.out.println(tablero[ct.getFila()+1][ct.getColumna()]);
        if (ct.getFila() < -1 || ct.getColumna() < 0 || ct.getFila() >= this.getFilas() || ct.getColumna() >= this.getColumnas()) {
            return null;
        }
        else if (tablero[ct.getFila()][ct.getColumna()] == null) {
			return null;
		}
        return this.tablero[ct.getFila() ][ct.getColumna()];
    }
    
    public void quitaObjetoDC(final CoordTablero ct) {
        this.tablero[ct.getFila() ][ct.getColumna()] = null;
    }
    
    public void mueveZombie(final CoordTablero origen, final CoordTablero destino) {
        this.tablero[destino.getFila()][destino.getColumna()] = this.tablero[origen.getFila()][origen.getColumna()];
        this.tablero[origen.getFila()][origen.getColumna() ] = null;
        if (this.tablero[destino.getFila()][destino.getColumna()] != null && (this.tablero[destino.getFila()][destino.getColumna()] instanceof Transparencia)) {
            this.tablero[destino.getFila()][destino.getColumna() ].setPosicionTablero(destino);
        }
    }
    
    public void intercambiaCaramelos(final CoordTablero origen, final CoordTablero destino) {
        final Bicho temp = this.tablero[destino.getFila() ][destino.getColumna()];
        final CoordTablero tempCT = this.tablero[destino.getFila() ][destino.getColumna()].getPosicionTablero();
        this.tablero[destino.getFila() ][destino.getColumna()].setPosicionTablero(this.tablero[origen.getFila() ][origen.getColumna()].getPosicionTablero());
        this.tablero[origen.getFila()][origen.getColumna()].setPosicionTablero(tempCT);
        this.tablero[destino.getFila() ][destino.getColumna()] = this.tablero[origen.getFila() ][origen.getColumna()];
        this.tablero[origen.getFila() ][origen.getColumna()] = temp;
    }
    
    @Override
    public String toString() {
        String ret = "";
        for (int f = 1; f < this.filas; ++f) {
            for (int c = 0; c < this.columnas; ++c) {
                if (this.tablero[f][c] == null) {
                    ret = String.valueOf(ret) + "- ";
                }
                else if (this.tablero[f][c] instanceof Zombie) {
                    ret = String.valueOf(ret) + "- ";
                }
                else if (this.tablero[f][c] instanceof Planta) {
                    final Planta cud = (Planta)this.tablero[f][c];
                    ret = String.valueOf(ret) + cud.getFuncion() + ": ";
                }
                else {
                    ret = String.valueOf(ret) + "* ";
                }
            }
            if (f < this.filas - 1) {
                ret = String.valueOf(ret) + "\n";
            }
        }
        return ret;
    }
    
    public int getFilas() {
        return this.filas;
    }
    
    public int getColumnas() {
        return this.columnas;
    }
    
    public static void main(final String[] args) {
        final TableroBichos tc = new TableroBichos(15, 15, null);
        System.out.println("Tablero inicial:");
        System.out.println(tc.toString());
        System.out.println("Quita el caramelo de (0,0):");
        tc.quitaObjetoDC(new CoordTablero(0, 0));
        System.out.println("Mueve un caramelo de (0,1) a (0,2):");
        tc.mueveZombie(new CoordTablero(0, 1), new CoordTablero(0, 2));
        System.out.println("Intercambia los caramelos de (1,0) y (2,0)");
        tc.intercambiaCaramelos(new CoordTablero(1, 0), new CoordTablero(2, 0));
        System.out.println("Tablero resultado:");
        System.out.println(tc.toString());
    }
}
