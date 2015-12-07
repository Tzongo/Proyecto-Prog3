
import ventanas.*;
import juego.*;

import java.awt.Point;

import javax.swing.SwingUtilities;

import accion.*;

public class PlantasVsZombies {
	private static TableroBichos tablero;
	private static int PAUSA_MOVIMIENTO_MS;
	private static Puntuador miPuntuador;

	static {
		PlantasVsZombies.PAUSA_MOVIMIENTO_MS = 500;
		PlantasVsZombies.miPuntuador = new Puntuador();
	}

	private static boolean buscaYQuitaLineas(final TableroBichos tc) {
		/*
		 * final VentanaJuegoTablero v = tc.getVentana(); boolean
		 * seHanQuitadoLineas = false; for (int f = 0; f < tc.getFilas(); ++f) {
		 * String colorAnt = ""; int numIguales = 0; for (int c = 0; c <
		 * tc.getColumnas(); ++c) { final CoordTablero ct = new CoordTablero(f,
		 * c); final String colorNuevo = (tc.getCaramelo(ct) == null) ? "" :
		 * tc.getCaramelo(ct).getColor(); if (!colorNuevo.equals("") &&
		 * colorNuevo.equals(colorAnt)) { ++numIguales; } else { if (numIguales
		 * >= 3) { for (int quitar = c - numIguales; quitar < c; ++quitar) {
		 * quitarCar(tc, new CoordTablero(f, quitar), v); } seHanQuitadoLineas =
		 * true; } colorAnt = colorNuevo; numIguales = 1; } } if (numIguales >=
		 * 3) { for (int quitar2 = tc.getColumnas() - numIguales; quitar2 <
		 * tc.getColumnas(); ++quitar2) { quitarCar(tc, new CoordTablero(f,
		 * quitar2), v); } seHanQuitadoLineas = true; } } for (int c2 = 0; c2 <
		 * tc.getColumnas(); ++c2) { String colorAnt = ""; int numIguales = 0;
		 * for (int f2 = 0; f2 < tc.getFilas(); ++f2) { final CoordTablero ct =
		 * new CoordTablero(f2, c2); final String colorNuevo =
		 * (tc.getCaramelo(ct) == null) ? "" : tc.getCaramelo(ct).getColor(); if
		 * (!colorNuevo.equals("") && colorNuevo.equals(colorAnt)) {
		 * ++numIguales; } else { if (numIguales >= 3) { for (int quitar = f2 -
		 * numIguales; quitar < f2; ++quitar) { quitarCar(tc, new
		 * CoordTablero(quitar, c2), v); } seHanQuitadoLineas = true; } colorAnt
		 * = colorNuevo; numIguales = 1; } } if (numIguales >= 3) { for (int
		 * quitar2 = tc.getFilas() - numIguales; quitar2 < tc.getFilas();
		 * ++quitar2) { quitarCar(tc, new CoordTablero(quitar2, c2), v); }
		 * seHanQuitadoLineas = true; } } if (seHanQuitadoLineas) {
		 * v.esperaUnRato(DeustoCrash.PAUSA_MOVIMIENTO_MS); }
		 */
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
		/*
		 * final VentanaJuegoTablero v = tc.getVentana(); boolean haCaidoAlguna
		 * = true; while (haCaidoAlguna) { haCaidoAlguna = false; for (int f =
		 * tc.getFilas() - 1; f >= 0; --f) { for (int c = 0; c <
		 * tc.getColumnas(); ++c) { final CoordTablero ct = new CoordTablero(f,
		 * c); final ObjetoDeustoCrash cm = tc.getObjetoDC(ct); if (cm == null)
		 * { CoordTablero cSup = new CoordTablero(f - 1, c); ObjetoDeustoCrash
		 * cmSup = tc.getObjetoDC(cSup); if (cmSup != null && cmSup instanceof
		 * Caible) { if (((Caible)cmSup).caer()) { haCaidoAlguna = true; } }
		 * else if (cmSup != null) { boolean caeIzquierda = false; if (c > 0) {
		 * cSup = new CoordTablero(f - 1, c - 1); cmSup = tc.getObjetoDC(cSup);
		 * if (cmSup != null && cmSup instanceof Caible &&
		 * ((Caible)cmSup).caerDiagonal(false)) { haCaidoAlguna = true;
		 * caeIzquierda = true; } } if (!caeIzquierda && c < tc.getColumnas() -
		 * 1) { cSup = new CoordTablero(f - 1, c + 1); cmSup =
		 * tc.getObjetoDC(cSup); if (cmSup != null && cmSup instanceof Caible &&
		 * ((Caible)cmSup).caerDiagonal(true)) { haCaidoAlguna = true; } } } } }
		 * } if (haCaidoAlguna) { DeustoCrash.tablero.reponerFilaOculta();
		 * v.esperaAFinAnimaciones(); } }
		 */
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
	public static void movimientos(VentanaJuegoTablero v){
		for (int f = tablero.getFilas() - 1; f >= 0; --f) {
			for (int c = 0; c < tablero.getColumnas(); ++c) {
				final CoordTablero ct = new CoordTablero(f, c);
				final Bicho cm = tablero.getObjetoDC(ct);
				if (cm instanceof Zombie) {
					if (cm.mover()) {
					v.movePosTablero2(cm.getObjeto());
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
		v.setTiempoPasoAnimacion(200L, 40);
		v.showMessage("Juego en curso");
		boolean finJuego = false;
		int movsSeguidosSinCaramelos = 0;
		while (!finJuego && !v.isClosed()) {
			//caenLasPiezas(PlantasVsZombies.tablero);
			movimientos(v);
			/*
			 * boolean quitadoAlgo = true; while (quitadoAlgo) { quitadoAlgo =
			 * buscaYQuitaLineas(PlantasVsZombies.tablero); if (quitadoAlgo) {
			 * movsSeguidosSinCaramelos = 0;
			 * caenLasPiezas(PlantasVsZombies.tablero); } }
			 */
			/*
			 * if (movsSeguidosSinCaramelos >= 1) { finJuego = true; } else if
			 * (!hayMovimientosPosibles(PlantasVsZombies.tablero)) { finJuego =
			 * true; } else {
			 */
			final CoordTablero c2 = v.readInicioDrag();
			if (c2 != null) {
				final CoordTablero c3 = v.getFinalDrag();
				if (c2.distanciaCon(c3) <= 1.0) {
					final Bicho cm1 = PlantasVsZombies.tablero.getBicho(c2);
					final Bicho cm2 = PlantasVsZombies.tablero.getBicho(c3);
					if (cm1 != null && cm2 != null) {
						final ObjetoDeJuego inicio = cm1.getObjeto();
						final ObjetoDeJuego finl = cm2.getObjeto();
						v.movePosTablero(inicio, c3);
						v.movePosTablero(finl, c2);
						PlantasVsZombies.tablero.intercambiaCaramelos(c2, c3);
						++movsSeguidosSinCaramelos;
						++numMovs;
						v.esperaAFinAnimaciones();
					}
				}
			}
			
			// }
			v.showMessage("Movimientos realizados: " + numMovs);
		}
		v.showMessage(
				"Puntuaci\u00f3n final: " + PlantasVsZombies.miPuntuador.getPuntos() + ". Cerrando en 5 segundos...");
		v.esperaUnRato(50000);
		v.finish();
	}
	
	
}
