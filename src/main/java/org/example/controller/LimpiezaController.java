package org.example.controller;

import org.example.singleton.DatosGenes;
import org.example.singleton.RespuestaAlineamientoSecuencias;
import org.example.singleton.RespuestaDeteccionMotivos;
import org.example.singleton.RespuestaPrediccionEstructura;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LimpiezaController {

    @PostMapping("/limpiarDatos")
    public ResponseEntity<String> limpiarDatos() {
        try {
            DatosGenes.getInstance().clear();
            RespuestaAlineamientoSecuencias.getInstance().clear();
            RespuestaDeteccionMotivos.getInstance().clear();
            RespuestaPrediccionEstructura.getInstance().clear();

            return ResponseEntity.ok("Datos limpiados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al limpiar los datos: " + e.getMessage());
        }
    }
}
