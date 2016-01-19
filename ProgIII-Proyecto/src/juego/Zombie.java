package juego;

import accion.Movible;

public abstract class Zombie extends Bicho implements Movible{
	private int ataque;//ataque
	private int velocidad;//velocidad movimiento cuadrados/s
	public Zombie() {
		// TODO Auto-generated constructor stub
	}
	
	public Zombie(int ataque, int velocidad) {
		super();
		this.ataque = ataque;
		this.velocidad = velocidad;
	}
	
	public Zombie(CoordTablero ct, String nomFicGrafico, int ancho, int alto, TableroBichos tc) {
		super(ct, nomFicGrafico, ancho, alto, tc);
		// TODO Auto-generated constructor stub
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

	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		final int fila = this.posicion.getFila();
        final int col = this.posicion.getColumna();
        final CoordTablero caida = new CoordTablero(fila , col-1);
        System.out.println(this.tablero.getObjetoDC(caida));
        if (this.tablero.getObjetoDC(caida) != null && !(this.tablero.getObjetoDC(caida) instanceof Transparencia)) {
            return false;
        }
        this.tablero.mueveZombie(this.posicion, caida);
        /*if (this.tablero.getVentana() != null) {
            this.tablero.getVentana().movePosTablero2(this.getObjeto(), caida);
        }*/
        this.setPosicionTablero(new CoordTablero(this.getPosicionTablero().getFila(), this.getPosicionTablero().getColumna()-1));
        return true;
	}
}
