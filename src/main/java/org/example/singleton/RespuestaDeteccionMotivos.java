package org.example.singleton;

import java.util.List;
import java.util.Map;

public class RespuestaDeteccionMotivos {

    private static RespuestaDeteccionMotivos respuestaDeteccionMotivos;
    private List<Map<String, String>> datos;
    private RespuestaDeteccionMotivos() {

    }
    public static synchronized RespuestaDeteccionMotivos getInstance(){
        if(respuestaDeteccionMotivos == null){
            respuestaDeteccionMotivos = new RespuestaDeteccionMotivos();
        }
        return respuestaDeteccionMotivos;
    }

    public List<Map<String, String>> getDatos() {
        return datos;
    }

    public void setDatos(List<Map<String, String>> datos) {
        this.datos = datos;
    }

    public void clear() {
        if (datos != null) {
            datos.clear();
        }
        datos = null;
    }
}
