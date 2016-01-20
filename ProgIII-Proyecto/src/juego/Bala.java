package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import game.model.CommandCenter;

public class Bala extends Bicho {
	private int vida;

	public Bala() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bala(CoordTablero ct, String nomFicGrafico, int ancho, int alto, TableroBichos tc) {
		super(ct, nomFicGrafico, ancho, alto, tc);
		// TODO Auto-generated constructor stub
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	@Override
	public void setPuntuador(Puntuador p0) {
		// TODO Auto-generated method stub

	}

	public void quitar() {
		/*
		 * if (this.miPuntuador != null) { this.miPuntuador.addPuntos(1); }
		 */
		if (this.tablero.getVentana() != null) {
			this.tablero.getVentana().removeObjeto(this.getObjeto());
		}
		this.tablero.quitaObjetoDC(this.posicion);
		Transparencia caram = new Transparencia(posicion, "Transparencia", 60, 60, this.tablero);
		this.tablero.setBicho(caram, posicion);
		this.tablero.getVentana().addObjeto(this.tablero.getObjetoDC(posicion).getObjeto(), posicion);
	}

	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		final int fila = this.posicion.getFila();
		final int col = this.posicion.getColumna();
		if (col + 1 < 10) {

			final CoordTablero caida = new CoordTablero(fila, col + 1);

			if (this.tablero.getObjetoDC(caida) != null) {
				if (this.tablero.getObjetoDC(caida) instanceof Zombie) {
					if (this.tablero.getObjetoDC(caida).getVida()>0) {
						this.tablero.getObjetoDC(caida)
							.setVida(this.tablero.getObjetoDC(caida).getVida() - 50);
					}
					if (this.tablero.getObjetoDC(caida).getVida() <= 0) {
						this.quitar();
						this.tablero.getObjetoDC(caida).quitar();
						
					} else {
						this.quitar();
						return false;
					}
				} else if (this.tablero.getObjetoDC(
						new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())) instanceof Zombie) {
					if (this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())).getVida()>0) {
						this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
							.setVida(this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())).getVida() - 50);
					}
					

					if (this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
							.getVida() <= 0) {
						this.quitar();
						this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
								.quitar();
						
					} else {
						this.quitar();
						return false;
					}
				} else if ((this.tablero.getObjetoDC(caida) instanceof Transparencia)) {
					if (this.tablero.getObjetoDC(
							new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())) instanceof Zombie) {
						if (this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())).getVida()>0) {
							this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
								.setVida(this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())).getVida() - 50);
						}
						

						if (this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
								.getVida() <= 0) {
							this.quitar();
							this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
									.quitar();
							
						} else {
							this.quitar();
							return false;
						}
					}
					else
					this.tablero.mueveBala(this.posicion, caida);
				}
				this.setPosicionTablero(new CoordTablero(this.getPosicionTablero().getFila(),
						this.getPosicionTablero().getColumna() + 1));
				return true;
			} else {
				if (this.tablero.getObjetoDC(
						new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())) instanceof Zombie) {
					if (this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())).getVida()>0) {
						this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
							.setVida(this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna())).getVida() - 50);
					}
					

					if (this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
							.getVida() <= 0) {
						this.quitar();
						this.tablero.getObjetoDC(new CoordTablero(this.posicion.getFila(), this.posicion.getColumna()))
								.quitar();
						
					} else {
						this.quitar();
						return false;
					}
				}else {
					this.tablero.mueveBala(this.posicion, caida);
					this.setPosicionTablero(new CoordTablero(this.getPosicionTablero().getFila(),
							this.getPosicionTablero().getColumna() + 1));
					return true;
				}
			}
		}
		this.quitar();
		return false;
	}
}
