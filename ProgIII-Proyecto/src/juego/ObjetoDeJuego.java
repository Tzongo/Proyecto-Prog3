package juego;

import javax.swing.*;

import utilidades.*;

import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;

public class ObjetoDeJuego extends JLabel
{
    protected String nombreImagenObjeto;
    protected JPanel panelJuego;
    protected boolean esVisible;
    protected int anchuraObjeto;
    protected int alturaObjeto;
    protected ImageIcon icono;
    protected boolean escalado;
    protected BufferedImage imagenObjeto;
    private static final long serialVersionUID = 1L;
    
    public ObjetoDeJuego(final String nombreImagenObjeto, final boolean visible, final int anchura, final int altura) {
        this.panelJuego = null;
        this.anchuraObjeto = anchura;
        this.alturaObjeto = altura;
        this.nombreImagenObjeto = nombreImagenObjeto;
        final URL imgURL = Img.getURLRecurso(nombreImagenObjeto);
        if (imgURL == null) {
            this.icono = null;
            this.setOpaque(true);
            this.setBackground(Color.red);
            this.setForeground(Color.blue);
            this.setBorder(BorderFactory.createLineBorder(Color.blue));
            this.setText(nombreImagenObjeto);
        }
        else {
            this.setIcon(this.icono = new ImageIcon(imgURL));
            if (anchura == this.icono.getIconWidth() && altura == this.icono.getIconHeight()) {
                this.escalado = false;
            }
            else {
                this.escalado = true;
                try {
                    this.imagenObjeto = ImageIO.read(imgURL);
                }
                catch (IOException e) {
                    this.escalado = false;
                }
            }
        }
        this.setSize(anchura, altura);
        this.setVisible(this.esVisible = visible);
    }
    
    public ObjetoDeJuego(final String nombreImagenObjeto, final boolean visible) {
        this(nombreImagenObjeto, visible, 10, 10);
        if (this.icono != null) {
            this.anchuraObjeto = this.icono.getIconWidth();
            this.alturaObjeto = this.icono.getIconHeight();
            this.setSize(this.anchuraObjeto, this.alturaObjeto);
        }
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.esVisible = visible;
    }
    
    public int getAnchuraObjeto() {
        return this.anchuraObjeto;
    }
    
    public int getAlturaObjeto() {
        return this.alturaObjeto;
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        if (this.escalado) {
            final Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(this.imagenObjeto, 0, 0, this.anchuraObjeto, this.alturaObjeto, null);
        }
        else {
            super.paintComponent(g);
        }
    }
}
