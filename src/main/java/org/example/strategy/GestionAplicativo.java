package org.example.strategy;

public class GestionAplicativo  {
    private IGestionAplicativo metodo;

    public void setMetodo(IGestionAplicativo metodo) {
        this.metodo = metodo;
    }

    public void comenzar(String opcion) {
        if(metodo == null) {
            System.out.println("No selecciono metodo de pago");
        } else{
            metodo.iniciar(opcion);
        }
    }


}
