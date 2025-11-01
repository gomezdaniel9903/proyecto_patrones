package org.example.builder;

import java.util.List;
import java.util.Map;

public interface IReportBuilder {
    ReportBuilder reset();
    ReportBuilder alineamientoSecuencias(Map<String,String> alineamientoSecuencias);
    ReportBuilder deteccionMotivos(List<Map<String,String>> deteccionMotivos);
    ReportBuilder prediccionEstructura(List<Map<String,String>> prediccionEstructura);
    Report getReport();
}
