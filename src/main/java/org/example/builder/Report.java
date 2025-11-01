package org.example.builder;

import java.util.List;
import java.util.Map;

public class Report {
    private Map<String,String> alineamientoSecuencias;
    private List<Map<String,String>> deteccionMotivos;
    private List<Map<String,String>> prediccionEstructura;

    public static ReportBuilder builder = new ReportBuilder();

    public Map<String, String> getAlineamientoSecuencias() {
        return alineamientoSecuencias;
    }

    public void setAlineamientoSecuencias(Map<String, String> alineamientoSecuencias) {
        this.alineamientoSecuencias = alineamientoSecuencias;
    }

    public List<Map<String, String>> getDeteccionMotivos() {
        return deteccionMotivos;
    }

    public void setDeteccionMotivos(List<Map<String, String>> deteccionMotivos) {
        this.deteccionMotivos = deteccionMotivos;
    }

    public List<Map<String, String>> getPrediccionEstructura() {
        return prediccionEstructura;
    }

    public void setPrediccionEstructura(List<Map<String, String>> prediccionEstructura) {
        this.prediccionEstructura = prediccionEstructura;
    }
}
