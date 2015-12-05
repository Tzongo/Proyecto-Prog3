package juego;

public abstract class Zombie extends Bicho {
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

}
