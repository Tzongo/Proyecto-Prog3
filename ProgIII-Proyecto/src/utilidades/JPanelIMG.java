package utilidades;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * 
 * Clase que extiende de JPanel y permite poner una imagen como fondo.
 * 
 * @author Guille Rodriguez Gonzalez ( http://www.driverlandia.com )
 * @version 1.0 | 05-2014
 * 
 */

public class JPanelIMG extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributo que guardara la imagen de Background que le pasemos.
	private Image background;

	// Metodo que es llamado automaticamente por la maquina virtual Java cada
	// vez que repinta
	public void paintComponent(Graphics g) {

		/*
		 * Obtenemos el tama�o del panel para hacer que se ajuste a este cada
		 * vez que redimensionemos la ventana y se lo pasamos al drawImage
		 */
		int width = this.getSize().width;
		int height = this.getSize().height;

		// Mandamos que pinte la imagen en el panel
		if (this.background != null) {
			g.drawImage(this.background, 0, 0, width, height, null);
		}

		super.paintComponent(g);
	}

	// Metodo donde le pasaremos la direcci�n de la imagen a cargar.
	public void setBackground(URL url) {

		// Construimos la imagen y se la asignamos al atributo background.
		this.setOpaque(false);
		this.background = new ImageIcon(url).getImage();
		repaint();
	}

	public void setBackground(String url) {

		// Construimos la imagen y se la asignamos al atributo background.
		this.setOpaque(false);
		this.background = new ImageIcon(url).getImage();
		repaint();
	}
}