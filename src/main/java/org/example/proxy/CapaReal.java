package org.example.proxy;

import org.example.strategy.GenerarReporte;
import org.example.strategy.GestionAplicativo;
import org.example.strategy.IniciarAnalisis;

public class CapaReal implements IProxy {
    @Override
    public void inicioAnalisis(String opcion) {
        GestionAplicativo gestionAplicativo = new GestionAplicativo();
        if(opcion.equalsIgnoreCase("GenerarReporte")){
            gestionAplicativo.setMetodo(new GenerarReporte());
            gestionAplicativo.comenzar("");
        }else{
            gestionAplicativo.setMetodo(new IniciarAnalisis());
            gestionAplicativo.comenzar(opcion);
        }


    }
}
