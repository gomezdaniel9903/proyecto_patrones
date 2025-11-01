package org.example.service;

import org.example.model.AlineamientoSecuenciasEntity;
import org.example.model.DeteccionMotivosEntity;
import org.example.model.PrediccionEstructuraEntity;
import org.example.repository.AlineamientoSecuenciasEntityRepository;
import org.example.repository.DeteccionMotivosEntityRepository;
import org.example.repository.PrediccionEstructuraEntityRepository;
import org.example.singleton.RespuestaAlineamientoSecuencias;
import org.example.singleton.RespuestaDeteccionMotivos;
import org.example.singleton.RespuestaPrediccionEstructura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VerificacionResultadoBD {

    @Autowired
    private AlineamientoSecuenciasEntityRepository alineamientoSecuenciasRepository;
    @Autowired
    private DeteccionMotivosEntityRepository deteccionMotivosEntityRepository;
    @Autowired
    private PrediccionEstructuraEntityRepository prediccionEstructuraEntityRepository;

    public List<String> verificacion(List<String> opciones, Map<String, String> resultadoParseo) {
        List<String> opcionesFinales = new ArrayList<>();
        try {


            if(opciones.contains("Calcular Alineamiento")) {
                String cadenaUno = resultadoParseo.values().toArray()[0].toString();
                String cadenaDos = resultadoParseo.values().toArray()[1].toString();

                List<AlineamientoSecuenciasEntity> verificacion = alineamientoSecuenciasRepository.findBySecuencia1AndSecuencia2(cadenaUno, cadenaDos);
                if(!verificacion.isEmpty()){
                    Map<String,String> retorno =  new LinkedHashMap<>();
                    retorno.put("nombre1",verificacion.get(0).getNombre1());
                    retorno.put("cadenaUno",cadenaUno);
                    retorno.put("nombre2",verificacion.get(0).getNombre2());
                    retorno.put("cadenaDos",cadenaDos);
                    retorno.put("respuesta",verificacion.get(0).getRespuesta());
                    RespuestaAlineamientoSecuencias respuestaAlineamientoSecuencias = RespuestaAlineamientoSecuencias.getInstance();
                    respuestaAlineamientoSecuencias.setDatos(retorno);
                }else{
                    opcionesFinales.add("Calcular Alineamiento");
                }
            }

            if(opciones.contains("Detección de Motivo")){
                List<Map<String,String>> datosPrevios = new ArrayList<>();
                resultadoParseo.forEach((key, value) -> {
                    List<DeteccionMotivosEntity> registro = deteccionMotivosEntityRepository.findBySecuencia(value);
                    if(!registro.isEmpty()){
                        Map<String,String> retorno =  new LinkedHashMap<>();
                        retorno.put("nombre",registro.get(0).getNombre());
                        retorno.put("secuencia",value);
                        retorno.put("respuesta",registro.get(0).getRespuesta());
                        datosPrevios.add(retorno);
                    }
                });
                if(!datosPrevios.isEmpty() && datosPrevios.size() == resultadoParseo.size()){
                    RespuestaDeteccionMotivos respuestaDeteccionMotivos = RespuestaDeteccionMotivos.getInstance();
                    respuestaDeteccionMotivos.setDatos(datosPrevios);
                }else if(!datosPrevios.isEmpty() && datosPrevios.size() < resultadoParseo.size()){
                    opcionesFinales.add("Detección de Motivo");
                    RespuestaDeteccionMotivos respuestaDeteccionMotivos = RespuestaDeteccionMotivos.getInstance();
                    respuestaDeteccionMotivos.setDatos(datosPrevios);
                }else if(datosPrevios.isEmpty()){
                    opcionesFinales.add("Detección de Motivo");
                }

            }

            if(opciones.contains("Predicción de Estructura")){
                List<Map<String,String>> datosPreviosEstructura = new ArrayList<>();
                resultadoParseo.forEach((key, value) -> {
                    List<PrediccionEstructuraEntity> registro = prediccionEstructuraEntityRepository.findBySecuencia(value);
                    if(!registro.isEmpty()){
                        Map<String,String> retorno =  new LinkedHashMap<>();
                        retorno.put("nombre",registro.get(0).getNombre());
                        retorno.put("secuencia",value);
                        retorno.put("respuesta",registro.get(0).getRespuesta());
                        datosPreviosEstructura.add(retorno);
                    }
                });
                if(!datosPreviosEstructura.isEmpty() && datosPreviosEstructura.size() == resultadoParseo.size()){
                    RespuestaPrediccionEstructura respuestaPrediccionEstructura = RespuestaPrediccionEstructura.getInstance();
                    respuestaPrediccionEstructura.setDatos(datosPreviosEstructura);
                }else if(!datosPreviosEstructura.isEmpty() && datosPreviosEstructura.size() < resultadoParseo.size()){
                    opcionesFinales.add("Predicción de Estructura");
                    RespuestaPrediccionEstructura respuestaPrediccionEstructura = RespuestaPrediccionEstructura.getInstance();
                    respuestaPrediccionEstructura.setDatos(datosPreviosEstructura);
                }else if(datosPreviosEstructura.isEmpty()){
                    opcionesFinales.add("Predicción de Estructura");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo FASTA", e);
        }

        return opcionesFinales;

    }

}
