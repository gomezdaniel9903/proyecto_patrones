package org.example.service;

import org.example.model.PrediccionEstructuraEntity;
import org.example.repository.PrediccionEstructuraEntityRepository;
import org.example.singleton.RespuestaPrediccionEstructura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GuardadoPrediccionEstructura {
    @Autowired
    private PrediccionEstructuraEntityRepository reposotoryEntidad;
    private PrediccionEstructuraEntity entidad;
    public void guardado() {
        try {
            RespuestaPrediccionEstructura datosGenes = RespuestaPrediccionEstructura.getInstance();
            List<Map<String, String>> datos = datosGenes.getDatos();

            for(Map<String,String> dato: datos){
                entidad =  new PrediccionEstructuraEntity();
                entidad.setNombre(dato.get("nombre"));
                entidad.setSecuencia(dato.get("secuencia"));
                entidad.setRespuesta(dato.get("respuesta"));
                reposotoryEntidad.save(entidad);
            }


        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo FASTA", e);
        }

    }
}
