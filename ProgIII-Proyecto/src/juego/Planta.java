package juego;

import ventanas.VentanaJuegoTablero;

public abstract class Planta extends Bicho {
	private int funcion;//1 ataque(disparador)	,2 crear soles(sol)
	private int recarga;//tiempo de recarga ms
	private int costo;//costo creacion
	public Planta() {
		// TODO Auto-generated constructor stub
	}
	
	public Planta(int funcion, int recarga, int costo) {
		super();
		this.funcion = funcion;
		this.recarga = recarga;
		this.costo = costo;
	}

	public Planta(CoordTablero ct, String nomFicGrafico, int ancho, int alto, TableroBichos tc) {
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

	public int getFuncion() {
		return funcion;
	}
	public void setFuncion(int funcion) {
		this.funcion = funcion;
	}
	public int getRecarga() {
		return recarga;
	}
	public void setRecarga(int recarga) {
		this.recarga = recarga;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
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
