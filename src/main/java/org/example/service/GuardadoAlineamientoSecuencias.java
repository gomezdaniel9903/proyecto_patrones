package org.example.service;

import org.example.model.AlineamientoSecuenciasEntity;
import org.example.repository.AlineamientoSecuenciasEntityRepository;
import org.example.singleton.RespuestaAlineamientoSecuencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service

public class GuardadoAlineamientoSecuencias {

    @Autowired
    private AlineamientoSecuenciasEntityRepository reposotoryEntidad;
    private AlineamientoSecuenciasEntity entidad;
    public void guardado() {
        try {
            RespuestaAlineamientoSecuencias datosGenes = RespuestaAlineamientoSecuencias.getInstance();
            Map<String, String> datos = datosGenes.getDatos();

            entidad =  new AlineamientoSecuenciasEntity();
            entidad.setNombre1(datos.get("nombre1"));
            entidad.setNombre2(datos.get("nombre2"));
            entidad.setSecuencia1(datos.get("cadenaUno"));
            entidad.setSecuencia2(datos.get("cadenaDos"));
            entidad.setRespuesta(datos.get("respuesta"));
            reposotoryEntidad.save(entidad);

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo FASTA", e);
        }

    }
}
