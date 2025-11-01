package org.example.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opcionesAnalisis")
public class OpcionesController {

    @GetMapping
    public List<String> obtenerOpciones() {
        return List.of("Calcular Alineamiento", "Detección de Motivo", "Predicción de Estructura");
    }
}