package juego;

import accion.Movible;
import accion.Quitable;

public class Minizombie extends Zombie implements Quitable, Movible{
	public Minizombie() {
		// TODO Auto-generated constructor stub
	}

	public Minizombie(int ataque, int velocidad) {
		super(ataque, velocidad);
		// TODO Auto-generated constructor stub
	}
	public Minizombie(final CoordTablero ct, final int ancho, final int alto, final TableroBichos tc) {
        super(ct, "MiniZombie", ancho, alto, tc);
    }

	@Override
	public int getVida() {
		// TODO Auto-generated method stub
		return super.getVida();
	}

	@Override
	public void setVida(int vida) {
		// TODO Auto-generated method stub
		super.setVida(vida);
	}

	@Override
	public int getAtaque() {
		// TODO Auto-generated method stub
		return super.getAtaque();
	}

	@Override
	public void setAtaque(int ataque) {
		// TODO Auto-generated method stub
		super.setAtaque(ataque);
	}

	@Override
	public int getVelocidad() {
		// TODO Auto-generated method stub
		return super.getVelocidad();
	}

	@Override
	public void setVelocidad(int velocidad) {
		// TODO Auto-generated method stub
		super.setVelocidad(velocidad);
	}

	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		final int fila = this.posicion.getFila();
        final int col = this.posicion.getColumna();
        final CoordTablero caida = new CoordTablero(fila , col+1);
        if (this.tablero.getObjetoDC(caida) != null) {
            return false;
        }
        this.tablero.mueveCaramelo(this.posicion, caida);
        if (this.tablero.getVentana() != null) {
            this.tablero.getVentana().movePosTablero(this.getObjeto(), caida);
        }
        return true;
	}

	@Override
	public void setPuntuador(Puntuador p0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitar() {
		// TODO Auto-generated method stub
		
	}
	

}
