package org.example.builder;

import java.util.List;
import java.util.Map;

public class ReportBuilder implements IReportBuilder {
    private Report report;

    public ReportBuilder() {
        this.reset();
    }

    @Override
    public ReportBuilder reset() {
        this.report = new Report();
        return this;
    }

    @Override
    public ReportBuilder alineamientoSecuencias(Map<String,String> alineamientoSecuencias) {
        report.setAlineamientoSecuencias(alineamientoSecuencias);
        return this;
    }

    @Override
    public ReportBuilder deteccionMotivos(List<Map<String,String>> deteccionMotivos) {
        report.setDeteccionMotivos(deteccionMotivos);
        return this;
    }

    @Override
    public ReportBuilder prediccionEstructura(List<Map<String,String>> prediccionEstructura) {
        report.setPrediccionEstructura(prediccionEstructura);
        return this;
    }

    @Override
    public Report getReport() {
        return this.report;
    }
}
