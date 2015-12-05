

import ventanas.*;
import juego.*;

public class DeustoCrash
{
    private static TableroBichos tablero;
    private static int PAUSA_MOVIMIENTO_MS;
    private static Puntuador miPuntuador;
    
    static {
        DeustoCrash.PAUSA_MOVIMIENTO_MS = 500;
        DeustoCrash.miPuntuador = new Puntuador();
    }
    
    private static boolean buscaYQuitaLineas(final TableroBichos tc) {
        final VentanaJuegoTablero v = tc.getVentana();
        boolean seHanQuitadoLineas = false;
        for (int f = 0; f < tc.getFilas(); ++f) {
            String colorAnt = "";
            int numIguales = 0;
            for (int c = 0; c < tc.getColumnas(); ++c) {
                final CoordTablero ct = new CoordTablero(f, c);
                final String colorNuevo = (tc.getCaramelo(ct) == null) ? "" : tc.getCaramelo(ct).getColor();
                if (!colorNuevo.equals("") && colorNuevo.equals(colorAnt)) {
                    ++numIguales;
                }
                else {
                    if (numIguales >= 3) {
                        for (int quitar = c - numIguales; quitar < c; ++quitar) {
                            quitarCar(tc, new CoordTablero(f, quitar), v);
                        }
                        seHanQuitadoLineas = true;
                    }
                    colorAnt = colorNuevo;
                    numIguales = 1;
                }
            }
            if (numIguales >= 3) {
                for (int quitar2 = tc.getColumnas() - numIguales; quitar2 < tc.getColumnas(); ++quitar2) {
                    quitarCar(tc, new CoordTablero(f, quitar2), v);
                }
                seHanQuitadoLineas = true;
            }
        }
        for (int c2 = 0; c2 < tc.getColumnas(); ++c2) {
            String colorAnt = "";
            int numIguales = 0;
            for (int f2 = 0; f2 < tc.getFilas(); ++f2) {
                final CoordTablero ct = new CoordTablero(f2, c2);
                final String colorNuevo = (tc.getCaramelo(ct) == null) ? "" : tc.getCaramelo(ct).getColor();
                if (!colorNuevo.equals("") && colorNuevo.equals(colorAnt)) {
                    ++numIguales;
                }
                else {
                    if (numIguales >= 3) {
                        for (int quitar = f2 - numIguales; quitar < f2; ++quitar) {
                            quitarCar(tc, new CoordTablero(quitar, c2), v);
                        }
                        seHanQuitadoLineas = true;
                    }
                    colorAnt = colorNuevo;
                    numIguales = 1;
                }
            }
            if (numIguales >= 3) {
                for (int quitar2 = tc.getFilas() - numIguales; quitar2 < tc.getFilas(); ++quitar2) {
                    quitarCar(tc, new CoordTablero(quitar2, c2), v);
                }
                seHanQuitadoLineas = true;
            }
        }
        if (seHanQuitadoLineas) {
            v.esperaUnRato(DeustoCrash.PAUSA_MOVIMIENTO_MS);
        }
        return seHanQuitadoLineas;
    }
    
    private static void quitarCar(final TableroCaramelos tc, final CoordTablero ct, final VentanaJuegoTablero v) {
        final CarameloUD c = DeustoCrash.tablero.getCaramelo(ct);
        c.setPuntuador(DeustoCrash.miPuntuador);
        c.quitar();
    }
    
    private static void caenLasPiezas(final TableroCaramelos tc) {
        final VentanaJuegoTablero v = tc.getVentana();
        boolean haCaidoAlguna = true;
        while (haCaidoAlguna) {
            haCaidoAlguna = false;
            for (int f = tc.getFilas() - 1; f >= 0; --f) {
                for (int c = 0; c < tc.getColumnas(); ++c) {
                    final CoordTablero ct = new CoordTablero(f, c);
                    final ObjetoDeustoCrash cm = tc.getObjetoDC(ct);
                    if (cm == null) {
                        CoordTablero cSup = new CoordTablero(f - 1, c);
                        ObjetoDeustoCrash cmSup = tc.getObjetoDC(cSup);
                        if (cmSup != null && cmSup instanceof Caible) {
                            if (((Caible)cmSup).caer()) {
                                haCaidoAlguna = true;
                            }
                        }
                        else if (cmSup != null) {
                            boolean caeIzquierda = false;
                            if (c > 0) {
                                cSup = new CoordTablero(f - 1, c - 1);
                                cmSup = tc.getObjetoDC(cSup);
                                if (cmSup != null && cmSup instanceof Caible && ((Caible)cmSup).caerDiagonal(false)) {
                                    haCaidoAlguna = true;
                                    caeIzquierda = true;
                                }
                            }
                            if (!caeIzquierda && c < tc.getColumnas() - 1) {
                                cSup = new CoordTablero(f - 1, c + 1);
                                cmSup = tc.getObjetoDC(cSup);
                                if (cmSup != null && cmSup instanceof Caible && ((Caible)cmSup).caerDiagonal(true)) {
                                    haCaidoAlguna = true;
                                }
                            }
                        }
                    }
                }
            }
            if (haCaidoAlguna) {
                DeustoCrash.tablero.reponerFilaOculta();
                v.esperaAFinAnimaciones();
            }
        }
    }
    
    private static int caramelosQuedan(final TableroCaramelos tc) {
        int cont = 0;
        for (int c = 0; c < tc.getColumnas(); ++c) {
            for (int f = 0; f < tc.getFilas(); ++f) {
                final CoordTablero ct = new CoordTablero(f, c);
                final CarameloUD cm = tc.getCaramelo(ct);
                if (cm != null) {
                    ++cont;
                }
            }
        }
        return cont;
    }
    
    private static boolean hayMovimientosPosibles(final TableroCaramelos tc) {
        for (int c = 1; c < tc.getColumnas(); ++c) {
            for (int f = 1; f < tc.getFilas(); ++f) {
                final CoordTablero ct = new CoordTablero(f, c);
                final CarameloUD cm = tc.getCaramelo(ct);
                if (cm != null) {
                    final CoordTablero cArriba = new CoordTablero(f - 1, c);
                    final CarameloUD cmArriba = tc.getCaramelo(cArriba);
                    if (cmArriba != null) {
                        return true;
                    }
                    final CoordTablero cIzqda = new CoordTablero(f, c - 1);
                    final CarameloUD cmIzqda = tc.getCaramelo(cIzqda);
                    if (cmIzqda != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void main(final String[] args) {
        final int FILAS_COLS = 8;
        int numMovs = 0;
        final VentanaJuegoTablero v = new VentanaJuegoTablero(900, 600, FILAS_COLS, FILAS_COLS, true);
        DeustoCrash.tablero = new TableroCaramelos(FILAS_COLS, v);
        for (int f = 0; f < FILAS_COLS; ++f) {
            for (int c = 0; c < FILAS_COLS; ++c) {
                if (DeustoCrash.tablero.getObjetoDC(new CoordTablero(f, c)) instanceof Muro) {
                    ((Muro)DeustoCrash.tablero.getObjetoDC(new CoordTablero(f, c))).setPuntuador(DeustoCrash.miPuntuador);
                }
            }
        }
        v.setTiempoPasoAnimacion(200L, 40);
        v.showMessage("Juego en curso");
        boolean finJuego = false;
        int movsSeguidosSinCaramelos = 0;
        while (!finJuego && !v.isClosed()) {
            boolean quitadoAlgo = true;
            while (quitadoAlgo) {
                quitadoAlgo = buscaYQuitaLineas(DeustoCrash.tablero);
                if (quitadoAlgo) {
                    movsSeguidosSinCaramelos = 0;
                    caenLasPiezas(DeustoCrash.tablero);
                }
            }
            if (movsSeguidosSinCaramelos >= 1) {
                finJuego = true;
            }
            else if (!hayMovimientosPosibles(DeustoCrash.tablero)) {
                finJuego = true;
            }
            else {
                final CoordTablero c2 = v.readInicioDrag();
                if (c2 != null) {
                    final CoordTablero c3 = v.getFinalDrag();
                    if (c2.distanciaCon(c3) <= 1.0) {
                        final CarameloUD cm1 = DeustoCrash.tablero.getCaramelo(c2);
                        final CarameloUD cm2 = DeustoCrash.tablero.getCaramelo(c3);
                        if (cm1 != null && cm2 != null) {
                            final ObjetoDeJuego inicio = cm1.getObjeto();
                            final ObjetoDeJuego finl = cm2.getObjeto();
                            v.movePosTablero(inicio, c3);
                            v.movePosTablero(finl, c2);
                            DeustoCrash.tablero.intercambiaCaramelos(c2, c3);
                            ++movsSeguidosSinCaramelos;
                            ++numMovs;
                            v.esperaAFinAnimaciones();
                        }
                    }
                }
            }
            v.showMessage("Movimientos realizados: " + numMovs);
        }
        v.showMessage("Puntuaci\u00f3n final: " + DeustoCrash.miPuntuador.getPuntos() + ". Cerrando en 5 segundos...");
        v.esperaUnRato(5000);
        v.finish();
    }
}
