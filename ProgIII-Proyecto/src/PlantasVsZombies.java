
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import juego.Bala;
import juego.Bicho;
import juego.CoordTablero;
import juego.ObjetoDeJuego;
import juego.Planta;
import juego.Puntuador;
import juego.TableroBichos;
import juego.Transparencia;
import juego.Zombie;
import ventanas.VentanaJuegoTablero;

public class PlantasVsZombies {
	private static TableroBichos tablero;
	private static int PAUSA_MOVIMIENTO_MS;
	private static Puntuador miPuntuador;

	static {
		PlantasVsZombies.PAUSA_MOVIMIENTO_MS = 1000;
		PlantasVsZombies.miPuntuador = new Puntuador();
	}

	private static boolean buscaYQuitaLineas(final TableroBichos tc) {

		return false;// seHanQuitadoLineas;
	}

	private static void quitarBicho(final TableroBichos tc, final CoordTablero ct, final VentanaJuegoTablero v) {
		final Zombie c = (Zombie) PlantasVsZombies.tablero.getBicho(ct);
		c.setPuntuador(PlantasVsZombies.miPuntuador);
		c.quitar();
	}

	private static void caenLasPiezas(final TableroBichos tc) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					final VentanaJuegoTablero v = tc.getVentana();
					while (true) {
						for (int f = tc.getFilas() - 1; f >= 0; --f) {
							for (int c = 0; c < tc.getColumnas(); ++c) {
								final CoordTablero ct = new CoordTablero(f, c);
								final Bicho cm = tc.getObjetoDC(ct);
								if (cm instanceof Zombie) {
									cm.mover();
								}
							}
						}
					}
				}
			});
		} catch (Exception ex) {
		}
	}

	private static int caramelosQuedan(final TableroBichos tc) {
		int cont = 0;
		for (int c = 0; c < tc.getColumnas(); ++c) {
			for (int f = 0; f < tc.getFilas(); ++f) {
				final CoordTablero ct = new CoordTablero(f, c);
				final Zombie cm = (Zombie) tc.getBicho(ct);
				if (cm != null) {
					++cont;
				}
			}
		}
		return cont;
	}

	private static boolean hayMovimientosPosibles(final TableroBichos tc) {
		for (int c = 1; c < tc.getColumnas(); ++c) {
			for (int f = 1; f < tc.getFilas(); ++f) {
				final CoordTablero ct = new CoordTablero(f, c);
				final Bicho cm = tc.getBicho(ct);
				if (cm != null) {
					final CoordTablero cArriba = new CoordTablero(f - 1, c);
					final Bicho cmArriba = tc.getBicho(cArriba);
					if (cmArriba != null) {
						return true;
					}
					final CoordTablero cIzqda = new CoordTablero(f, c - 1);
					final Bicho cmIzqda = tc.getBicho(cIzqda);
					if (cmIzqda != null) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void movimientos(VentanaJuegoTablero v) {
		for (int f = tablero.getFilas() - 1; f >= 0; --f) {
			for (int c = 0; c < tablero.getColumnas(); ++c) {
				System.out.println(tablero.getObjetoDC(new CoordTablero(f, c)) + " g");
				final CoordTablero ct = new CoordTablero(f, c);
				final Bicho cm = tablero.getObjetoDC(ct);
				if (cm instanceof Zombie) {
					if (cm.mover()) {
						if (tablero.getObjetoDC(new CoordTablero(f, c - 2)) instanceof Planta) {
							Planta p = (Planta) tablero.getObjetoDC(new CoordTablero(f, c - 2));
							p.quitar();
						}
						v.movePosTablero(cm.getObjeto(), cm.getPosicionTablero());
					}
				}
			}
		}
	}

	public static void movimientos3(VentanaJuegoTablero v) {
		for (int f = tablero.getFilas() - 1; f >= 0; --f) {
			for (int c = 0; c < tablero.getColumnas(); ++c) {
				final CoordTablero ct = new CoordTablero(f, c);
				final Bicho cm = tablero.getObjetoDC(ct);
				if (cm instanceof Planta) {
					ObjetoDeJuego oj = cm.getObjeto();
					Bala b = new Bala(oj.getX(), oj.getY());
					if (b.mover()) {
						v.animaciones3(oj, ct);
						v.repaint();
					}

				}
			}
		}
	}

	public static void main(final String[] args) {
		final int FILAS_COLS = 3;
		int numMovs = 0;
		final VentanaJuegoTablero v = new VentanaJuegoTablero(562, 315, 3, 10, false);
		PlantasVsZombies.tablero = new TableroBichos(3, 10, 60, 60, v);
		for (int f = 0; f < FILAS_COLS; ++f) {
			for (int c = 0; c < FILAS_COLS; ++c) {
				if (PlantasVsZombies.tablero.getObjetoDC(new CoordTablero(f, c)) instanceof Zombie) {
					((Zombie) PlantasVsZombies.tablero.getObjetoDC(new CoordTablero(f, c)))
							.setPuntuador(PlantasVsZombies.miPuntuador);
				}
			}
		}
		// velocidad zombies
		v.setTiempoPasoAnimacion(200L, 40);
		v.showMessage("Juego en curso");
		boolean finJuego = false;
		int movsSeguidosSinCaramelos = 0;
		while (!finJuego && !v.isClosed()) {
			movimientos(v);
			// movimientos3(v);
			tablero.distribuyeZombiesAlAzar(60, 60);
			System.out.println(tablero.toString());

			/*
			 * final CoordTablero c2 = v.readInicioDrag();
			 * System.out.println(c2+"c2"); if (c2 != null) { final CoordTablero
			 * c3 = v.getFinalDrag(); if (c2.distanciaCon(c3) <= 1.0) { final
			 * Bicho cm1 = PlantasVsZombies.tablero.getBicho(c2); final Bicho
			 * cm2 = PlantasVsZombies.tablero.getBicho(c3); if (cm1 != null &&
			 * cm2 != null) { final ObjetoDeJuego inicio = cm1.getObjeto();
			 * final ObjetoDeJuego finl = cm2.getObjeto();
			 * v.movePosTablero(inicio, c3); v.movePosTablero(finl, c2);
			 * PlantasVsZombies.tablero.intercambiaCaramelos(c2, c3);
			 * ++movsSeguidosSinCaramelos; ++numMovs; v.esperaAFinAnimaciones();
			 * //v.esperaAFin(); } } }
			 */

			v.esperaAFinAnimaciones();
			for (int f = 0; f < FILAS_COLS; ++f) {
				if (PlantasVsZombies.tablero.getObjetoDC(new CoordTablero(f, 0)) instanceof Zombie) {
					finJuego = true;
				}
			}
			v.showMessage("Movimientos realizados: " + numMovs);
		}

		v.showMessage(
				"Puntuaci\u00f3n final: " + PlantasVsZombies.miPuntuador.getPuntos() + ". Cerrando en 5 segundos...");
		v.esperaUnRato(5000);
		v.finish();
	}
}
