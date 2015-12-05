package juego;

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

}
