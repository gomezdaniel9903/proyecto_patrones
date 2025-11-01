package org.example.service;

import org.example.model.SecuenciaGenetica;
import org.example.repository.SecuenciaGeneticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ParseoArchivoFasta {
    @Autowired
    private SecuenciaGeneticaRepository reposotoryEntidad;
    private SecuenciaGenetica entidad;
    public Map<String, String> parsearFasta(MultipartFile file) {
        Map<String, String> secuencias = new LinkedHashMap<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            String id = null;
            StringBuilder sequence = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(">")) {
                    if (id != null) {
                        secuencias.put(id, sequence.toString());
                        entidad =  new SecuenciaGenetica();
                        entidad.setNombre(id);
                        entidad.setSecuencia(sequence.toString());
                        reposotoryEntidad.save(entidad);
                    }
                    id = linea.substring(1).trim();
                    sequence = new StringBuilder();
                } else {
                    sequence.append(linea.trim());
                }
            }

            if (id != null) {
                secuencias.put(id, sequence.toString());
                entidad =  new SecuenciaGenetica();
                entidad.setNombre(id);
                entidad.setSecuencia(sequence.toString());
                reposotoryEntidad.save(entidad);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo FASTA", e);
        }

        return secuencias;
    }
}
