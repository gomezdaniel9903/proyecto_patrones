package org.example.facade;

import org.example.builder.IReportBuilder;
import org.example.builder.ReportBuilder;
import org.example.singleton.*;

import java.util.List;
import java.util.Map;

public class ConstruccionReporteFinal {
    public void calcular(){
        AnalisisSeleccionados analisisSeleccionados = AnalisisSeleccionados.getInstance();
        List<String> opciones = analisisSeleccionados.getDatos();
        IReportBuilder builder = new ReportBuilder();
        builder.reset();
        for(String opt:opciones){
            switch (opt) {
                case "Calcular Alineamiento":
                    RespuestaAlineamientoSecuencias respuestaAlineamientoSecuencias = RespuestaAlineamientoSecuencias.getInstance();
                    Map<String,String> respuesta = respuestaAlineamientoSecuencias.getDatos();
                    builder.alineamientoSecuencias(respuesta);
                    break;
                case "Detección de Motivo":
                    RespuestaDeteccionMotivos respuestaDeteccionMotivos = RespuestaDeteccionMotivos.getInstance();
                    List<Map<String,String>> respuestaMotivos = respuestaDeteccionMotivos.getDatos();
                    builder.deteccionMotivos(respuestaMotivos);
                    break;
                case "Predicción de Estructura":
                    RespuestaPrediccionEstructura respuestaPrediccionEstructura = RespuestaPrediccionEstructura.getInstance();
                    List<Map<String,String>> respuestaPrediccion = respuestaPrediccionEstructura.getDatos();

                    builder.prediccionEstructura(respuestaPrediccion);
                    break;
            }
        }

        ReporteGenerado reporteGenerado = ReporteGenerado.getInstance();
        reporteGenerado.setBuilder(builder);
    }

}
