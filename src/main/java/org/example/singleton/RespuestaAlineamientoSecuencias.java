package org.example.singleton;

import java.util.Map;

public class RespuestaAlineamientoSecuencias {

    private static RespuestaAlineamientoSecuencias respuestaAlineamientoSecuencias;
    private Map<String, String> datos;
    private RespuestaAlineamientoSecuencias() {

    }
    public static synchronized RespuestaAlineamientoSecuencias getInstance(){
        if(respuestaAlineamientoSecuencias == null){
            respuestaAlineamientoSecuencias = new RespuestaAlineamientoSecuencias();
        }
        return respuestaAlineamientoSecuencias;
    }

    public Map<String, String> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, String> datos) {
        this.datos = datos;
    }

    public void clear() {
        if (datos != null) {
            datos.clear();
        }
        datos = null;
    }
}
