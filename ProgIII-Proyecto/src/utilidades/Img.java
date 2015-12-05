package utilidades;

import java.net.*;

public class Img
{
    public static URL getURLRecurso(final String nomRecImg) {
        URL recurso = null;
        try {
            recurso = Img.class.getResource(nomRecImg).toURI().toURL();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return recurso;
    }
}
