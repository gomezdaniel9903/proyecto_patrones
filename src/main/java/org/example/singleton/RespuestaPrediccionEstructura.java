package org.example.singleton;

import java.util.List;
import java.util.Map;

public class RespuestaPrediccionEstructura {
    private static RespuestaPrediccionEstructura respuestaPrediccionEstructura;
    private List<Map<String, String>> datos;
    private RespuestaPrediccionEstructura() {

    }
    public static synchronized RespuestaPrediccionEstructura getInstance(){
        if(respuestaPrediccionEstructura == null){
            respuestaPrediccionEstructura = new RespuestaPrediccionEstructura();
        }
        return respuestaPrediccionEstructura;
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
