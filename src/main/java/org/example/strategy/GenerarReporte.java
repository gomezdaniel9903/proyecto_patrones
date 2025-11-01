package org.example.strategy;

import org.example.facade.*;
import org.example.singleton.*;

public class GenerarReporte implements IGestionAplicativo {

    @Override
    public void iniciar(String opcion) {
        CalcularAlineamientoSecuencias calcularAlineamientoSecuencias = new CalcularAlineamientoSecuencias();
        CalcularDeteccionMotivos calcularDeteccionMotivos = new CalcularDeteccionMotivos();
        CalcularPrediccionEstructura calcularPrediccionEstructura = new CalcularPrediccionEstructura();
        ConstruccionReporteFinal construccionReporteFinal = new ConstruccionReporteFinal();

        ConstruccionAnalisis construccionAnalisis = new ConstruccionAnalisis(calcularAlineamientoSecuencias,calcularDeteccionMotivos
                ,calcularPrediccionEstructura,construccionReporteFinal);

        construccionAnalisis.calculos(false,false,false,true);

    }
}
