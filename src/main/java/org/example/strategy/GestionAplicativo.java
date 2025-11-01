package org.example.strategy;

public class GestionAplicativo  {
    private IGestionAplicativo metodo;

    public void setMetodo(IGestionAplicativo metodo) {
        this.metodo = metodo;
    }

    public void comenzar(String opcion) {
        if(metodo == null) {
            throw new IllegalArgumentException("No se seleccionó método de análisis");
        } else{
            metodo.iniciar(opcion);
        }
    }


}
