package org.example.singleton;

import org.example.builder.IReportBuilder;

public class ReporteGenerado {
    private static ReporteGenerado reporte;
    private IReportBuilder builder;
    private ReporteGenerado() {

    }
    public static synchronized ReporteGenerado getInstance(){
        if(reporte == null){
            reporte = new ReporteGenerado();
        }
        return reporte;
    }

    public IReportBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(IReportBuilder builder) {
        this.builder = builder;
    }

    public void clear() {
        if (reporte != null) {
            reporte.clear();
        }
        reporte = null;
    }
}
