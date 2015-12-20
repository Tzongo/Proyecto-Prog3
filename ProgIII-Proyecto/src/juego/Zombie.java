package juego;

import accion.Movible;

public abstract class Zombie extends Bicho implements Runnable,Movible{
	private int ataque;//ataque
	private int velocidad;//velocidad movimiento cuadrados/s
	protected Thread hilo;
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

	public Zombie(CoordTablero ct, String nomFicGrafico, int ancho, int alto, TableroBichos tc,Thread hilo) {
		super(ct, nomFicGrafico, ancho, alto, tc);
		// TODO Auto-generated constructor stub
		this.hilo=new Thread(this);
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
	public void run() {
		// TODO Auto-generated method stub
		while (this.hilo.isAlive()) {
			this.posicion=new CoordTablero(posicion.getFila()+1, posicion.getColumna());
			try {
				hilo.sleep(2500);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	public void startThread() {
		this.hilo.start();
	}
	public void killThread() {
		this.hilo.interrupt();;
	}
	public Thread getThread() {
		return hilo;
	}
	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		final int fila = this.posicion.getFila();
        final int col = this.posicion.getColumna();
        final CoordTablero caida = new CoordTablero(fila , col-2);
        if (this.tablero.getObjetoDC(caida) != null) {
            return false;
        }
        this.tablero.mueveZombie(this.posicion, caida);
        if (this.tablero.getVentana() != null) {
            this.tablero.getVentana().movePosTablero(this.getObjeto(), caida);
        }
        return true;
	}
}
