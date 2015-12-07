package juego;

import accion.Puntuable;
import accion.Quitable;
import juego.Puntuador;

public class Sol extends Planta implements Quitable, Puntuable{
	private Puntuador miPuntuador;
	public Sol() {
		// TODO Auto-generated constructor stub
	}
	public Sol(final CoordTablero ct, final int ancho, final int alto, final TableroBichos tc) {
        super(ct, "Sol", ancho, alto, tc);
        this.miPuntuador =  null;
    }
	
	public Sol( int recarga, int costo) {
		super(2, recarga, costo);
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

	@Override
	public int getFuncion() {
		// TODO Auto-generated method stub
		return super.getFuncion();
	}

	@Override
	public void setFuncion(int funcion) {
		// TODO Auto-generated method stub
		super.setFuncion(funcion);
	}

	@Override
	public int getRecarga() {
		// TODO Auto-generated method stub
		return super.getRecarga();
	}

	@Override
	public void setRecarga(int recarga) {
		// TODO Auto-generated method stub
		super.setRecarga(recarga);
	}

	@Override
	public int getCosto() {
		// TODO Auto-generated method stub
		return super.getCosto();
	}

	@Override
	public void setCosto(int costo) {
		// TODO Auto-generated method stub
		super.setCosto(costo);
	}

	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPuntuador(final Puntuador p) {
        this.miPuntuador = p;
    }

	@Override
	public void quitar() {
		// TODO Auto-generated method stub
		
	}
	
}
