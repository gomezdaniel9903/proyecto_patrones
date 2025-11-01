package org.example.factory;

import org.example.singleton.DatosGenes;
import org.example.singleton.RespuestaDeteccionMotivos;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeteccionMotivos implements  IAnalisisGenetico{
    private Map<String, String> datosCargados;
    @Override
    public void calcular() {
        DatosGenes datosGenes = DatosGenes.getInstance();
        datosCargados =  datosGenes.getDatos();
        String patron = "GGC";

        Pattern pattern = Pattern.compile(patron);
        List<Map<String, String>> respuestaFinal = new ArrayList<>();
        RespuestaDeteccionMotivos respuestaDeteccionMotivos = RespuestaDeteccionMotivos.getInstance();
        List<Map<String,String>> datosPrevios = respuestaDeteccionMotivos.getDatos();
        datosCargados.forEach((key, value) -> {
            if(datosPrevios == null || datosPrevios.stream().noneMatch(m -> m.containsValue(value))){
                Matcher matcher = pattern.matcher(value);
                List<String> posicionesPatronAux = new ArrayList<>();
                Map<String, String> respuesta = new LinkedHashMap<>();
                while (matcher.find()) {
                    posicionesPatronAux.add(String.valueOf(matcher.start()));
                }

                String posicionesFormateadas = posicionesPatronAux.isEmpty()
                        ? "No se encontraron coincidencias"
                        : String.join(";", posicionesPatronAux);

                String textoRespuesta = String.format(
                        "Patrón '%s' encontrado en las posiciones: %s para la secuencia %s",
                        patron,
                        posicionesFormateadas.replace(",", ";"), // reemplaza comas internas
                        key.replace(",", "") // limpia comas del nombre
                );

                respuesta.put("nombre",key);
                respuesta.put("secuencia",value);
                respuesta.put("respuesta", textoRespuesta.replace("\n", " "));
                respuestaFinal.add(respuesta);
            }
        });


        if(datosPrevios != null){
            respuestaFinal.addAll(datosPrevios);
        }
        respuestaDeteccionMotivos.setDatos(respuestaFinal);
    }
}
