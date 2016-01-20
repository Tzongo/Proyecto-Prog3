package juego;

import java.util.ArrayList;

import accion.Puntuable;
import accion.Quitable;
import juego.Puntuador;

public class Disparador extends Planta implements Quitable, Puntuable{
    private Puntuador miPuntuador;
    private ArrayList<Bala> balas;
	
	public Disparador() {
		// TODO Auto-generated constructor stub
		balas=new ArrayList<>();
	}

	public Disparador( int recarga, int costo) {
		super(1, recarga, costo);
		balas=new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	public Disparador(final CoordTablero ct, final int ancho, final int alto, final TableroBichos tc) {
        super(ct, "Disparador", ancho, alto, tc);
        this.miPuntuador = null;
        balas=new ArrayList<>();
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
	public void disparar() {
		
		Bala b = new Bala(new CoordTablero(posicion.getFila(), posicion.getColumna()+1), "Bala", 60, 60, this.tablero);
		
		balas.add(b);
        this.tablero.setBicho(b, new CoordTablero(posicion.getFila(), posicion.getColumna()+1));
        this.tablero.getVentana().addObjeto(this.tablero.getObjetoDC(new CoordTablero(posicion.getFila(), posicion.getColumna()+1)).getObjeto(), new CoordTablero(posicion.getFila(), posicion.getColumna()+1));
        for (int i = 0; i < balas.size(); i++) {
            Boolean s=balas.get(i).mover();
		}
        this.tablero.setBicho(this, this.posicion);
        this.tablero.getVentana().addObjeto(this.tablero.getObjetoDC(this.posicion).getObjeto(), this.posicion);
     
	}
	public ArrayList<Bala> getBalas() {
		return balas;
	}

	public void setBalas(ArrayList<Bala> balas) {
		this.balas = balas;
	}
	public Bala getBala(int n) {
		return balas.get(n);
	}
}