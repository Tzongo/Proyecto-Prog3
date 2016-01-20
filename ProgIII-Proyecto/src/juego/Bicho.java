package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import accion.Movible;
import accion.Puntuable;
import accion.Quitable;

public abstract class Bicho implements Quitable, Puntuable, Movible {

	protected int vida = 50;
	protected ObjetoDeJuego objeto;
	protected CoordTablero posicion;
	protected TableroBichos tablero;

	public Bicho() {
		// TODO Auto-generated constructor stub
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public Bicho(final CoordTablero ct, final String nomFicGrafico, final int ancho, final int alto,
			final TableroBichos tc) {
		this.posicion = ct;
		this.tablero = tc;
		this.objeto = new ObjetoDeJuego(String.valueOf(nomFicGrafico) + ".png", true, ancho, alto);
	}

	public ObjetoDeJuego getObjeto() {
		return this.objeto;
	}

	public CoordTablero getPosicionTablero() {
		return this.posicion;
	}

	public void setPosicionTablero(final CoordTablero ct) {
		this.posicion = ct;
	}

	@Override
	public String toString() {
		return "[objetoDeustoCrash (" + this.posicion.getFila() + "," + this.posicion.getColumna() + ")]";
	}

	
	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		final int fila = this.posicion.getFila();
		final int col = this.posicion.getColumna();
		final CoordTablero caida = new CoordTablero(fila, col - 1);
		System.out.println(this.tablero.getObjetoDC(caida));
		if (this.tablero.getObjetoDC(caida) != null && !(this.tablero.getObjetoDC(caida) instanceof Transparencia)) {
			return false;
		}
		this.tablero.mueveZombie(this.posicion, caida);
		/*
		 * if (this.tablero.getVentana() != null) {
		 * this.tablero.getVentana().movePosTablero2(this.getObjeto(), caida); }
		 */
		this.setPosicionTablero(
				new CoordTablero(this.getPosicionTablero().getFila(), this.getPosicionTablero().getColumna() - 1));
		return true;
	}
	public void quitar() {
        /*if (this.miPuntuador != null) {
            this.miPuntuador.addPuntos(1);
        }*/
        if (this.tablero.getVentana() != null) {
            this.tablero.getVentana().removeObjeto(this.getObjeto());
        }
        this.tablero.quitaObjetoDC(this.posicion);
        Transparencia caram = new Transparencia(posicion,"Transparencia", 60, 60, this.tablero);
        this.tablero.setBicho(caram, posicion);
        this.tablero.getVentana().addObjeto(this.tablero.getObjetoDC(posicion).getObjeto(), posicion);
    }
}