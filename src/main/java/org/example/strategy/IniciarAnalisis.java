package org.example.strategy;

import org.example.facade.*;

public class IniciarAnalisis implements IGestionAplicativo {

    @Override
    public void iniciar(String opcion) {

        CalcularAlineamientoSecuencias calcularAlineamientoSecuencias = new CalcularAlineamientoSecuencias();
        CalcularDeteccionMotivos calcularDeteccionMotivos = new CalcularDeteccionMotivos();
        CalcularPrediccionEstructura calcularPrediccionEstructura = new CalcularPrediccionEstructura();
        ConstruccionReporteFinal construccionReporteFinal = new ConstruccionReporteFinal();

        ConstruccionAnalisis construccionAnalisis = new ConstruccionAnalisis(calcularAlineamientoSecuencias,calcularDeteccionMotivos
                ,calcularPrediccionEstructura,construccionReporteFinal);
        switch (opcion) {
            case "Calcular Alineamiento":
                construccionAnalisis.calculos(true,false,false,false);
                break;
            case "Detección de Motivo":
                construccionAnalisis.calculos(false,true,false,false);
                break;
            case "Predicción de Estructura":
                construccionAnalisis.calculos(false,false,true,false);
                break;
        }

    }
}
