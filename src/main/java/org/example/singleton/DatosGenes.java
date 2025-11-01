package org.example.singleton;

import java.util.Map;

public class DatosGenes {
    private static DatosGenes datosGenes;
    private Map<String, String> datos;
    private DatosGenes() {

    }
    public static synchronized DatosGenes getInstance(){
        if(datosGenes == null){
            datosGenes = new DatosGenes();
        }
        return datosGenes;
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
