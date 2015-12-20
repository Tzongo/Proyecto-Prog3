package ventanas;

import juego.*;
import java.awt.*;

public class Animacion
{
    double xDesde;
    double xHasta;
    double yDesde;
    double yHasta;
    long msFaltan;
    public ObjetoDeJuego oj;
    
    public Animacion(final double xDesde, final double xHasta, final double yDesde, final double yHasta, final long msFaltan, final ObjetoDeJuego oj) {
        this.xDesde = xDesde;
        this.xHasta = xHasta;
        this.yDesde = yDesde;
        this.yHasta = yHasta;
        this.msFaltan = msFaltan;
        this.oj = oj;
    }
    
    public Point calcNextFrame(final long msPasados) {
        if (this.msFaltan <= msPasados) {
            this.msFaltan = 0L;
            return new Point((int)Math.round(this.xHasta), (int)Math.round(this.yHasta));
        }
        if (msPasados <= 0L) {
            return new Point((int)Math.round(this.xDesde), (int)Math.round(this.yDesde));
        }
        this.xDesde += (this.xHasta - this.xDesde) / this.msFaltan * msPasados;
        this.yDesde += (this.yHasta - this.yDesde) / this.msFaltan * msPasados;
        this.msFaltan -= msPasados;
        return new Point((int)Math.round(this.xDesde), (int)Math.round(this.yDesde));
    }
    
    public boolean finAnimacion() {
        return this.msFaltan <= 0L;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Animacion && this.oj == ((Animacion)obj).oj;
    }
    
    @Override
    public String toString() {
        return "Animacion (" + this.xDesde + "," + this.yDesde + ") -> (" + this.xHasta + "," + this.yHasta + ") msg: " + this.msFaltan;
    }
}
