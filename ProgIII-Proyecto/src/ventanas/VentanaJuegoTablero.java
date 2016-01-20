package ventanas;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import juego.*;
import utilidades.JPanelIMG;
import utilidades.JPanelRelleno;

public class VentanaJuegoTablero extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lMensaje;
	public JPanelIMG pAreaJuego;
	private JPanelIMG pFondo;
	private int anchVentana;
	private int altVentana;
	private int filasTablero;
	private int colsTablero;
	private boolean casillasCuadradas;
	private float pixelsPorFila;
	private float pixelsPorColumna;
	private int origenX;
	private int origenY;
	private int finX;
	private int finY;
	private ArrayList<CoordTablero> arrastresRaton;
	private CoordTablero pulsacionRaton;
	private long tiempoAnimMsg;
	private long tiempoFrameAnimMsg;
	private HiloAnimacion hilo;
	private HiloAnimacion2 hilo2;
	private HiloAnimacion3 hilo3;
	private ArrayList<Animacion> animacionesPendientes;
	private ArrayList<Animacion> animacionesPendientes2;

	private ArrayList<Animacion> animacionesPendientes3;
	private JPanelRelleno pRelleno1;
	private TableroBichos tablero;
	private ArrayList<Bicho> zList;
	    private Timer timer;

	public VentanaJuegoTablero(final int anchuraVent, final int alturaVent, final int filas, final int columnas,
			final boolean casCuadradas) {
		this.lMensaje = new JLabel(" ");
		this.pAreaJuego = new JPanelIMG();
		this.pFondo = new JPanelIMG();
		this.pulsacionRaton = null;
		this.tiempoAnimMsg = 2500L;
		this.tiempoFrameAnimMsg = this.tiempoAnimMsg / 40L;
		this.hilo = null;
		this.hilo3 = null;
		this.animacionesPendientes = new ArrayList<Animacion>();
		this.animacionesPendientes2 = new ArrayList<Animacion>();

		this.animacionesPendientes3 = new ArrayList<Animacion>();
		this.anchVentana = anchuraVent;// 562
		this.altVentana = alturaVent;// 317
		this.filasTablero = filas;// 3
		this.colsTablero = columnas;// 10
		this.casillasCuadradas = casCuadradas;
		this.pRelleno1 = new JPanelRelleno();

		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					VentanaJuegoTablero.access$2(VentanaJuegoTablero.this, new ArrayList());
					VentanaJuegoTablero.this.setDefaultCloseOperation(1);
					VentanaJuegoTablero.this.setSize(VentanaJuegoTablero.this.anchVentana,
							VentanaJuegoTablero.this.altVentana);
					VentanaJuegoTablero.this.setLocationRelativeTo(null);
					VentanaJuegoTablero.this.setResizable(false);
					VentanaJuegoTablero.this.setTitle("Ventana de juego de tablero");

					VentanaJuegoTablero.this.getContentPane().add(VentanaJuegoTablero.this.pAreaJuego, "Center");
					VentanaJuegoTablero.this.pAreaJuego.setBackground("img/tablero-american-suburbs.png");
					// VentanaJuegoTablero.this.pAreaJuego.setBackground("img/tablero-american-suburbs.png");
					// VentanaJuegoTablero.this.pFondo.add(VentanaJuegoTablero.this.pRelleno1,
					// "North");
					// VentanaJuegoTablero.this.getContentPane().add(VentanaJuegoTablero.this.pFondo,
					// "Center");
					VentanaJuegoTablero.this.getContentPane().add(VentanaJuegoTablero.this.lMensaje, "South");
					VentanaJuegoTablero.this.pAreaJuego.setLayout(null);
					VentanaJuegoTablero.this.lMensaje.setHorizontalAlignment(0);
					VentanaJuegoTablero.this.setVisible(true);
					VentanaJuegoTablero.this.calcTamanyo();
					VentanaJuegoTablero.this.pAreaJuego.addFocusListener(new FocusAdapter() {
						@Override
						public void focusLost(final FocusEvent arg0) {
							VentanaJuegoTablero.this.pAreaJuego.requestFocus();
						}
					});
					VentanaJuegoTablero.this.pAreaJuego.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(final MouseEvent arg0) {
							final CoordTablero sueltaRaton = VentanaJuegoTablero.this.pixsToCoord(arg0.getX(),
									arg0.getY());
							if (VentanaJuegoTablero.this.pulsacionRaton != null
									&& !VentanaJuegoTablero.this.pulsacionRaton.equals(sueltaRaton)
									&& VentanaJuegoTablero.this.estaEnTablero(VentanaJuegoTablero.this.pulsacionRaton)
									&& VentanaJuegoTablero.this.estaEnTablero(sueltaRaton)) {
								VentanaJuegoTablero.this.arrastresRaton.add(VentanaJuegoTablero.this.pulsacionRaton);
								VentanaJuegoTablero.this.arrastresRaton.add(sueltaRaton);
							}
							VentanaJuegoTablero.access$11(VentanaJuegoTablero.this, null);
						}

						@Override
						public void mousePressed(final MouseEvent arg0) {
							VentanaJuegoTablero.access$11(VentanaJuegoTablero.this,
									VentanaJuegoTablero.this.pixsToCoord(arg0.getX(), arg0.getY()));
						}

						@Override
						public void mouseClicked(final MouseEvent arg0) {
						}
					});
					
				}
			});
		} catch (Exception ex) {
		}
	}

	private CoordTablero pixsToCoord(int pixX, int pixY) {
		pixX -= this.origenX;
		pixY -= this.origenY;
		return new CoordTablero(Math.round(pixY / this.pixelsPorFila - 0.5f),
				Math.round(pixX / this.pixelsPorColumna - 0.5f));
	}

	private Point coordToPixs(final CoordTablero ct) {
		return new Point(Math.round(this.origenX + ct.getColumna() * this.pixelsPorColumna),
				Math.round(this.origenY + ct.getFila() * this.pixelsPorFila));
	}
	
	private void calcTamanyo() {
		this.pixelsPorFila = (this.pAreaJuego.getHeight()-60) * 1.0f / this.filasTablero;
		this.pixelsPorColumna = this.pAreaJuego.getWidth() * 1.0f / this.colsTablero;
		this.origenX = 0;
		this.origenY = 0;
		this.finX = this.pAreaJuego.getWidth() - 1;
		this.finY = this.pAreaJuego.getHeight() - 1;
		if (this.casillasCuadradas) {
			if (this.pAreaJuego.getHeight() > this.pAreaJuego.getWidth()) {
				final int pixelsSobran = this.pAreaJuego.getHeight() - this.pAreaJuego.getWidth();
				this.origenY = pixelsSobran / 2;
				this.pixelsPorFila = this.pixelsPorColumna;
				this.finY = Math.round(this.origenY + this.pixelsPorFila * this.filasTablero + 0.5f);
				
			} else {
				final int pixelsSobran = this.pAreaJuego.getWidth() - this.pAreaJuego.getHeight();
				this.origenX = pixelsSobran / 2;
				this.pixelsPorColumna = this.pixelsPorFila;
				this.finX = Math.round(this.origenX + this.pixelsPorColumna * this.colsTablero + 0.5f);
				
			}
		}
	}

	public void finish() {
		if (this.hilo != null) {
			this.hilo.interrupt();
		}
		this.dispose();
	}

	public boolean estaEnTablero(final CoordTablero ct) {
		return ct.getFila() >= 0 && ct.getFila() < this.filasTablero && ct.getColumna() >= 0
				&& ct.getColumna() < this.colsTablero;
	}

	public int getAnchoCasilla() {
		return (int) this.pixelsPorColumna;
	}

	public int getAltoCasilla() {
		return (int) this.pixelsPorFila;
	}

	public CoordTablero readInicioDrag() {
		while (this.arrastresRaton.isEmpty() && this.isVisible()) {
			try {
				Thread.sleep(10L);
			} catch (InterruptedException ex) {
			}
		}
		if (!this.isVisible()) {
			return null;
		}
		return this.arrastresRaton.get(0);
	}

	public CoordTablero getFinalDrag() {
		if (this.arrastresRaton.size() < 2) {
			return null;
		}
		final CoordTablero destino = this.arrastresRaton.get(1);
		this.arrastresRaton.remove(0);
		this.arrastresRaton.remove(0);
		return destino;
	}

	public void showMessage(final String s) {
		this.lMensaje.setText(s);
	}

	public boolean isClosed() {
		return !this.isVisible();
	}

	public void addObjeto(final ObjetoDeJuego oj, final CoordTablero c) {
		this.setPosTablero(oj, c);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					VentanaJuegoTablero.this.pAreaJuego.add(oj);
					VentanaJuegoTablero.this.pAreaJuego.repaint();
				}
			});
		} catch (Exception ex) {
		}
	}
	
	public void removeObjeto(final ObjetoDeJuego oj) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					VentanaJuegoTablero.this.pAreaJuego.remove(oj);
					VentanaJuegoTablero.this.pAreaJuego.repaint();
				}
			});
		} catch (Exception ex) {
		}
	}

	public void setPosTablero(final ObjetoDeJuego oj, final CoordTablero ct) {
		oj.setLocation(this.coordToPixs(ct));
	}

	public void movePosTablero(final ObjetoDeJuego oj, final CoordTablero ct) {
		
		if (oj != null) {
			if (this.hilo == null) {
				(this.hilo = new HiloAnimacion()).start();
			}
			final Point pHasta = this.coordToPixs(ct);
			final Animacion a = new Animacion(oj.getX(), pHasta.getX(), oj.getY(), pHasta.getY(), this.tiempoAnimMsg,
					oj);
			if (this.animacionesPendientes.indexOf(a) == -1) {
				this.animacionesPendientes.add(a);
			} else {
				final int pos = this.animacionesPendientes.indexOf(a);
				this.animacionesPendientes.get(pos).xHasta = pHasta.getX();
				this.animacionesPendientes.get(pos).yHasta = pHasta.getY();
				this.animacionesPendientes.get(pos).msFaltan = this.tiempoAnimMsg;
			}
		}
	}
	public void movePosTableroBala(final ObjetoDeJuego oj, final CoordTablero ct) {
		
		if (oj != null) {
			if (this.hilo2 == null) {
				(this.hilo2 = new HiloAnimacion2()).start();
			}
			final Point pHasta = this.coordToPixs(ct);
			final Animacion a = new Animacion(oj.getX(), pHasta.getX(), oj.getY(), pHasta.getY(), this.tiempoAnimMsg,
					oj);
			if (this.animacionesPendientes2.indexOf(a) == -1) {
				this.animacionesPendientes2.add(a);
			} else {
				final int pos = this.animacionesPendientes2.indexOf(a);
				this.animacionesPendientes2.get(pos).xHasta = pHasta.getX();
				this.animacionesPendientes2.get(pos).yHasta = pHasta.getY();
				this.animacionesPendientes2.get(pos).msFaltan = this.tiempoAnimMsg;
			}
		}
	}
	public void setTiempoPasoAnimacion(final long tiempoAnimMsg, final int numMovtos) {
		if (tiempoAnimMsg < 100L || numMovtos < 2 || numMovtos > tiempoAnimMsg) {
			return;
		}
		this.tiempoAnimMsg = tiempoAnimMsg;
		this.tiempoFrameAnimMsg = tiempoAnimMsg / numMovtos;
	}

	public CoordTablero getPosTablero(final ObjetoDeJuego oj) {
		final CoordTablero ct = this.pixsToCoord(Math.round(oj.getX() + this.pixelsPorColumna / 2.0f),
				Math.round(oj.getY() + this.pixelsPorFila / 2.0f));
		return ct;
	}

	public void esperaUnRato(final int msg) {
		try {
			Thread.sleep(msg);
		} catch (InterruptedException ex) {
		}
	}

	public void esperaAFinAnimaciones() {
		do {
			try {
				Thread.sleep(this.tiempoFrameAnimMsg);
			} catch (InterruptedException ex) {
			}
		} while (!this.animacionesPendientes.isEmpty());
	}

	public void animaciones3(final ObjetoDeJuego oj, final CoordTablero ct) {
		if (oj != null) {
			if (this.hilo3 == null) {
				(this.hilo3 = new HiloAnimacion3()).start();
			}
			final Point pHasta = this.coordToPixs(ct);
			final Animacion a = new Animacion(oj.getX(), pHasta.getX(), oj.getY(), pHasta.getY(), this.tiempoAnimMsg,
					oj);
			if (this.animacionesPendientes3.indexOf(a) == -1) {
				this.animacionesPendientes3.add(a);
			} else {
				final int pos = this.animacionesPendientes3.indexOf(a);
				this.animacionesPendientes3.get(pos).xHasta = pHasta.getX();
				this.animacionesPendientes3.get(pos).yHasta = pHasta.getY();
				this.animacionesPendientes3.get(pos).msFaltan = this.tiempoAnimMsg;
			}
		}
	}

	public static void main(final String[] args) {
		final int FILAS = 3;
		final int COLS = 10;
		final ObjetoDeJuego[][] tablero = new ObjetoDeJuego[FILAS][COLS];
		final VentanaJuegoTablero v = new VentanaJuegoTablero(562, 317, FILAS, COLS, false);
		v.showMessage("Juego en curso");
		final ObjetoDeJuego o1 = new ObjetoDeJuego("MiniZombie.png", true, v.getAnchoCasilla(), v.getAltoCasilla());
		final ObjetoDeJuego o2 = new ObjetoDeJuego("MiniZombie.png", true, v.getAnchoCasilla(), v.getAltoCasilla());
		final ObjetoDeJuego o3 = new ObjetoDeJuego("MiniZombie.png", true, v.getAnchoCasilla(), v.getAltoCasilla());
		final ObjetoDeJuego o4 = new ObjetoDeJuego("MegaZombie.png", true, v.getAnchoCasilla(), v.getAltoCasilla());
		v.addObjeto(o1, new CoordTablero(2, 1));
		v.addObjeto(o2, new CoordTablero(2, 2));
		v.addObjeto(o3, new CoordTablero(2, 3));
		v.addObjeto(o4, new CoordTablero(2, 4));
		tablero[2][1] = o1;
		tablero[2][2] = o2;
		tablero[2][3] = o3;
		tablero[2][4] = o4;
		while (!v.isClosed()) {
			final CoordTablero c1 = v.readInicioDrag();
			if (c1 != null) {
				final CoordTablero c2 = v.getFinalDrag();
				System.out.println("Drag en " + c1 + " --> " + c2);
				if (tablero[c1.getFila()][c1.getColumna()] == null) {
					continue;
				}
				final ObjetoDeJuego inicio = tablero[c1.getFila()][c1.getColumna()];
				final ObjetoDeJuego finl = tablero[c2.getFila()][c2.getColumna()];
				tablero[c1.getFila()][c1.getColumna()] = finl;
				v.setPosTablero(tablero[c2.getFila()][c2.getColumna()] = inicio, c2);
				if (finl == null) {
					continue;
				}
				v.setPosTablero(finl, c1);
			}
		}
		v.finish();
	}

	static /* synthetic */ void access$2(final VentanaJuegoTablero ventanaJuegoTablero,
			final ArrayList arrastresRaton) {
		ventanaJuegoTablero.arrastresRaton = (ArrayList<CoordTablero>) arrastresRaton;
	}

	static /* synthetic */ void access$11(final VentanaJuegoTablero ventanaJuegoTablero,
			final CoordTablero pulsacionRaton) {
		ventanaJuegoTablero.pulsacionRaton = pulsacionRaton;
	}

	class HiloAnimacion extends Thread {
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					Thread.sleep(VentanaJuegoTablero.this.tiempoFrameAnimMsg);
				} catch (InterruptedException e) {
					break;
				}
				for (int i = VentanaJuegoTablero.this.animacionesPendientes.size() - 1; i >= 0; --i) {
					final Animacion a = VentanaJuegoTablero.this.animacionesPendientes.get(i);
					if (a.oj != null) {
						a.oj.setLocation(a.calcNextFrame(VentanaJuegoTablero.this.tiempoFrameAnimMsg));
					}
					if (a.finAnimacion()) {
						VentanaJuegoTablero.this.animacionesPendientes.remove(i);
					}
				}
			}
		}
	}

	class HiloAnimacion2 extends Thread {
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					Thread.sleep(VentanaJuegoTablero.this.tiempoFrameAnimMsg);
				} catch (InterruptedException e) {
					break;
				}
				for (int i = VentanaJuegoTablero.this.animacionesPendientes2.size() - 1; i >= 0; --i) {
					final Animacion a = VentanaJuegoTablero.this.animacionesPendientes2.get(i);
					if (a.oj != null) {
						a.oj.setLocation(a.calcNextFrame(VentanaJuegoTablero.this.tiempoFrameAnimMsg));
					}
					if (a.finAnimacion()) {
						VentanaJuegoTablero.this.animacionesPendientes2.remove(i);
					}
				}
			}
		}
	}
	class HiloAnimacion3 extends Thread {
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					Thread.sleep(VentanaJuegoTablero.this.tiempoFrameAnimMsg);
				} catch (InterruptedException e) {
					break;
				}
				for (int i = VentanaJuegoTablero.this.animacionesPendientes.size() - 1; i >= 0; --i) {
					final Animacion a = VentanaJuegoTablero.this.animacionesPendientes.get(i);
					if (a.oj != null) {
						a.oj.setLocation(a.calcNextFrame(VentanaJuegoTablero.this.tiempoFrameAnimMsg));
					}
					if (a.finAnimacion()) {
						VentanaJuegoTablero.this.animacionesPendientes.remove(i);
					}
				}
			}
		}
	}
}
