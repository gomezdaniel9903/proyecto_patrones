package org.example.controller;
import org.example.proxy.IProxy;
import org.example.proxy.ProxyAplicativo;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/fasta")
public class WebController {
    @Autowired
    private ParseoArchivoFasta parseoArchivoFastaService;
    @Autowired
    private GuardadoAlineamientoSecuencias guardadoAlineamientoSecuencias;
    @Autowired
    private GuardadoDeteccionMotivos guardadoDeteccionMotivos;
    @Autowired
    private GuardadoPrediccionEstructura guardadoPrediccionEstructura;
    @Autowired
    private VerificacionResultadoBD verificacionResultadoBD;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFasta(
            @RequestParam("file") MultipartFile file,
            @RequestParam("opciones[]") List<String> opcionesSeleccionadas) {
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.toLowerCase().endsWith(".fasta") && !fileName.toLowerCase().endsWith(".txt"))) {
            return ResponseEntity
                    .badRequest()
                    .body("Solo se permiten archivos con extensión .fasta o .txt");
        }
        try{
            IProxy iProxy =  new ProxyAplicativo(file,opcionesSeleccionadas,parseoArchivoFastaService,guardadoAlineamientoSecuencias,
                    guardadoDeteccionMotivos,guardadoPrediccionEstructura, verificacionResultadoBD);
            iProxy.inicioAnalisis("");

        }catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body("Error procesando archivo: " + fileName +
                            " | Detalle: " + e.getMessage());
        }

        return null;
    }

}
