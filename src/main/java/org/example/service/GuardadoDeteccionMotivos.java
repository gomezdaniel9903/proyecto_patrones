package org.example.service;

import org.example.model.DeteccionMotivosEntity;
import org.example.repository.DeteccionMotivosEntityRepository;
import org.example.singleton.RespuestaDeteccionMotivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class GuardadoDeteccionMotivos {
    @Autowired
    private DeteccionMotivosEntityRepository reposotoryEntidad;
    private DeteccionMotivosEntity entidad;
    public void guardado() {
        try {
            RespuestaDeteccionMotivos datosGenes = RespuestaDeteccionMotivos.getInstance();
            List<Map<String, String>> datos = datosGenes.getDatos();

            for(Map<String, String> registro: datos) {
                entidad = new  DeteccionMotivosEntity();
                entidad.setNombre(registro.get("nombre"));
                entidad.setSecuencia(registro.get("secuencia"));
                entidad.setRespuesta(registro.get("respuesta"));
                reposotoryEntidad.save(entidad);
            }



        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo FASTA", e);
        }

    }
}
