package juego;

import accion.Puntuable;
import accion.Quitable;
import juego.Puntuador;

public class Disparador extends Planta implements Quitable, Puntuable{
    private Puntuador miPuntuador;
	public Disparador() {
		// TODO Auto-generated constructor stub
	}

	public Disparador( int recarga, int costo) {
		super(1, recarga, costo);
		// TODO Auto-generated constructor stub
	}
	public Disparador(final CoordTablero ct, final int ancho, final int alto, final TableroBichos tc) {
        super(ct, "Disparador", ancho, alto, tc);
        this.miPuntuador = null;
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
	public void setPuntuador(final Puntuador p) {
        this.miPuntuador = p;
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
        this.tablero.getVentana().addObjeto(caram.getObjeto()/*this.tablero.getObjetoDC(posicion).getObjeto()*/, posicion);
    }

	@Override
	public boolean mover() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
