package org.example.controller;

import org.example.builder.IReportBuilder;
import org.example.builder.Report;
import org.example.proxy.IProxy;
import org.example.proxy.ProxyAplicativo;
import org.example.singleton.AnalisisSeleccionados;
import org.example.singleton.ReporteGenerado;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
class GenerarCsvController {
        @GetMapping("/exportarCsv")
        public ResponseEntity<byte[]> exportarCSV() {
            try{
                IProxy iProxy =  new ProxyAplicativo();
                iProxy.inicioAnalisis("GenerarReporte");

            }catch (Exception e){
//            return ResponseEntity.internalServerError()
//                    .body("Error procesando archivo: " + fileName +
//                            " | Opción: " + opcion +
//                            " | Detalle: " + e.getMessage());
            }
            ReporteGenerado reporteGenerado = ReporteGenerado.getInstance();
            IReportBuilder reporte = reporteGenerado.getBuilder();
            Report report = reporte.getReport();
            List<String[]> datos = new ArrayList<String[]>();
            datos.add(new String[]{"Tipo Analisis","Nombre","Secuencia","Nombre" ,"Secuencia", "Resultado"});
            AnalisisSeleccionados analisisSeleccionados = AnalisisSeleccionados.getInstance();
            List<String> opciones = analisisSeleccionados.getDatos();
            if(opciones.contains("Calcular Alineamiento")){
                Map<String,String> respuesta = report.getAlineamientoSecuencias();
                datos.add(new String[]{"Alineamiento Secuencias", respuesta.get("nombre1"),respuesta.get("cadenaUno")
                        ,respuesta.get("nombre2"),respuesta.get("cadenaDos"),respuesta.get("respuesta")});
            }

            if(opciones.contains("Detección de Motivo")){
                List<Map<String,String>> respuesta = report.getDeteccionMotivos();
                for(Map<String,String> respuestaMotivo: respuesta){
                    datos.add(new String[]{"Detección de Motivos",respuestaMotivo.get("nombre"), respuestaMotivo.get("secuencia"),"No Aplica","No aplica", respuestaMotivo.get("respuesta")});
                }
            }

            if(opciones.contains("Predicción de Estructura")){
                List<Map<String,String>> respuesta = report.getPrediccionEstructura();
                for(Map<String,String> respuestaEstructura: respuesta){
                    datos.add(new String[]{"Predicción de Estructura", respuestaEstructura.get("nombre"),respuestaEstructura.get("secuencia"),"No aplica","No aplica", respuestaEstructura.get("respuesta")});
                }
            }


            StringBuilder csvBuilder = new StringBuilder();

            for (String[] fila : datos) {
                csvBuilder.append(String.join(",", fila)).append("\n");
            }

            byte[] csvBytes = csvBuilder.toString().getBytes(StandardCharsets.UTF_8);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"datos.csv\"")
                    .contentType(new MediaType("text", "csv", StandardCharsets.UTF_8))
                    .body(csvBytes);
        }
}
