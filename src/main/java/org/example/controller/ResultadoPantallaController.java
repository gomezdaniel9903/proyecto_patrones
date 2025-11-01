package org.example.controller;

import org.example.singleton.RespuestaAlineamientoSecuencias;
import org.example.singleton.RespuestaDeteccionMotivos;
import org.example.singleton.RespuestaPrediccionEstructura;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
class ResultadoPantallaController {
    @GetMapping("/verPantalla")
    public ResponseEntity<String> verPantalla() {
        RespuestaDeteccionMotivos respuestaDeteccionMotivos = RespuestaDeteccionMotivos.getInstance();
        List<Map<String, String>> datosDeteccionMotivos = respuestaDeteccionMotivos.getDatos();
        RespuestaPrediccionEstructura respuestaPrediccionEstructura = RespuestaPrediccionEstructura.getInstance();
        List<Map<String,String>> datosPrediccionMotivos = respuestaPrediccionEstructura.getDatos();
        RespuestaAlineamientoSecuencias respuestaAlineamientoSecuencias = RespuestaAlineamientoSecuencias.getInstance();
        Map<String,String> datosAlineamientoSecuencias = respuestaAlineamientoSecuencias.getDatos();

        StringBuilder html = new StringBuilder();

        html.append("<html><head>")
                .append("<meta charset='UTF-8'>")
                .append("<title>Resultados del análisis</title>")
                .append("<style>")
                .append("table { border-collapse: collapse; width: 80%; margin: 20px 0; }")
                .append("th, td { border: 1px solid #aaa; padding: 8px; text-align: left; }")
                .append("th { background-color: #ddd; }")
                .append("h3 { color: #333; }")
                .append("</style>")
                .append("</head><body>");

        if(datosAlineamientoSecuencias != null){
            List<Map<String,String>> tablaAlineamientos = List.of(datosAlineamientoSecuencias);
            // ====== Tabla 1 ======
            html.append("<h3>Tabla 1 - Alineamientos Secuencias</h3>");
            html.append(generarTablaHtml(tablaAlineamientos, "Nombre","Secuencia 1","Nombre", "Secuencia 2", "Resultado"));
        }

        if(datosDeteccionMotivos != null){
            // ====== Tabla 2 ======
            html.append("<h3>Tabla 2 - Detección de Motivos</h3>");
            html.append(generarTablaHtml(datosDeteccionMotivos, "Nombre","Secuencia", "Resultado"));
        }

        if(datosPrediccionMotivos != null){
            // ====== Tabla 3 ======
            html.append("<h3>Tabla 3 - Predicción de Estructura</h3>");
            html.append(generarTablaHtml(datosPrediccionMotivos, "Nombre","Secuencia", "Resultado"));
        }



        html.append("</body></html>");

        return ResponseEntity.ok(html.toString());
    }

    private String generarTablaHtml(List<Map<String, String>> datos, String... columnas) {
        if (datos == null || datos.isEmpty()) {
            return "<p><i>Sin datos disponibles.</i></p>";
        }

        StringBuilder tabla = new StringBuilder("<table><tr>");
        for (String col : columnas) {
            tabla.append("<th>").append(col).append("</th>");
        }
        tabla.append("</tr>");

        for (Map<String, String> fila : datos) {
            tabla.append("<tr>");
            for (String col : fila.values()) {
                tabla.append("<td>").append(col).append("</td>");
            }
            tabla.append("</tr>");
        }

        tabla.append("</table>");
        return tabla.toString();
    }


}
