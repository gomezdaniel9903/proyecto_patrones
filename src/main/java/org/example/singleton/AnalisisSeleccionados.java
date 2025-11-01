package org.example.singleton;

import java.util.List;

public class AnalisisSeleccionados {
    private static AnalisisSeleccionados analisisSeleccionados;
    private List<String> datos;
    private AnalisisSeleccionados() {

    }
    public static synchronized AnalisisSeleccionados getInstance(){
        if(analisisSeleccionados == null){
            analisisSeleccionados = new AnalisisSeleccionados();
        }
        return analisisSeleccionados;
    }

    public List<String> getDatos() {
        return datos;
    }

    public void setDatos(List<String> datos) {
        this.datos = datos;
    }

    public void clear() {
        if (datos != null) {
            datos.clear();
        }
        datos = null;
    }
}
