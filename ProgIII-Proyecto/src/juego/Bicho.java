package juego;

import juego.*;

public abstract class Bicho {
	// prueba

	protected int vida;
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
		this.objeto = new ObjetoDeJuego(String.valueOf(nomFicGrafico) + ".jpg", true, ancho, alto);
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
}
