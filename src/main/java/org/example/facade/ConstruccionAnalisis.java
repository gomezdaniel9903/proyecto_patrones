package org.example.facade;


public class ConstruccionAnalisis {

    private final CalcularAlineamientoSecuencias calcularAlineamientoSecuencias;
    private final CalcularDeteccionMotivos calcularDeteccionMotivos;
    private final CalcularPrediccionEstructura calcularPrediccionEstructura;
    private final ConstruccionReporteFinal construccionReporteFinal;

    public ConstruccionAnalisis(CalcularAlineamientoSecuencias calcularAlineamientoSecuencias,
                                CalcularDeteccionMotivos calcularDeteccionMotivos,
                                CalcularPrediccionEstructura calcularPrediccionEstructura,
                                ConstruccionReporteFinal construccionReporteFinal) {
        this.calcularAlineamientoSecuencias = calcularAlineamientoSecuencias;
        this.calcularDeteccionMotivos = calcularDeteccionMotivos;
        this.calcularPrediccionEstructura = calcularPrediccionEstructura;
        this.construccionReporteFinal = construccionReporteFinal;
    }



    public void calculos(Boolean calcularAlineamientoSecuencias,Boolean calcularDeteccionMotivos,Boolean calcularPrediccionEstructura, Boolean construccionReporteFinal) {


        if(calcularAlineamientoSecuencias){
            this.calcularAlineamientoSecuencias.calcular();
        }else if(calcularDeteccionMotivos){
            this.calcularDeteccionMotivos.calcular();
        }else if(calcularPrediccionEstructura){
            this.calcularPrediccionEstructura.calcular();
        }else if(construccionReporteFinal){
            this.construccionReporteFinal.calcular();
        }
    }

}
