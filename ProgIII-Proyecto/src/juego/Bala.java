package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import game.model.CommandCenter;

public class Bala extends Bicho {
	
	    private Image bala;
	    //Coordenadas de la pelota
	    private int X;
	    private int Y;

	    private int velocidad_X;
	    private int velocidad_Y;

	    private int limite_izquierda=0;
	    private int limite_derecha;
	    private int limite_superior=0;
	    private int limite_inferior;

	     public Bala(int x, int y) {
	        //coordenadas iniciales
	        this.X = x; this.Y = y;
	        //imagen de la pelota
	        //bala = new ImageIcon(getClass().getResource("pelota.png")).getImage();
	    }

	    //dado las dimensiones del contendor JPanel
	   public void LimitesXY(int width, int height) {
	        limite_derecha  = width  - bala.getWidth(null);
	        limite_inferior = height - bala.getHeight(null);
	    }

	   public boolean mover() {
	        X += velocidad_X;
	        Y += velocidad_Y;
	        if (X < this.limite_izquierda) {
	            X = 0;
	            velocidad_X = -velocidad_X;
	        } else if (X > limite_derecha) {
	            X = limite_derecha;
	            velocidad_X = -velocidad_X;
	        }
	        if (Y < this.limite_superior) {
	            Y = 0;
	            velocidad_Y = -velocidad_Y;

	        } else if (Y > limite_inferior) {
	            Y =  limite_inferior;
	            velocidad_Y = -velocidad_Y;
	        }
			return false;
	    }

	    public void setVelocidadXY(){
	        velocidad_X = getNumberRandom(4);
	        velocidad_Y = getNumberRandom(8);
	    }

	    public void dibujar(Graphics g) {
	        Graphics2D g2 = (Graphics2D)g;
	        g2.fillOval (350, 270, 50, 70);
	        //g2.drawImage(bala, X, Y, null);
	    }

	    //devuelve un número aleatorio entre 1 y MAX
	    private int getNumberRandom(int Max){
	        return (int) (Math.random()*Max+1);
	    }
	
	public Bala() {
		// TODO Auto-generated constructor stub
	}

	public Bala(CoordTablero ct, String nomFicGrafico, int ancho, int alto, TableroBichos tc) {
		super(ct, nomFicGrafico, ancho, alto, tc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void quitar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPuntuador(Puntuador p0) {
		// TODO Auto-generated method stub

	}
	 private final static int REGULAR_BULLET_RADIUS = 40;
	    private final double FIRE_POWER = 15.0;

	    private Color bulletColor;
	    public int bulletType;
}
