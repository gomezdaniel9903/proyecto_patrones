package org.example.factory;

import org.example.singleton.DatosGenes;
import org.example.singleton.RespuestaPrediccionEstructura;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrediccionEstructura implements  IAnalisisGenetico{
    private Map<String, String> datosCargados;
    @Override
    public void calcular() {
        DatosGenes datosGenes = DatosGenes.getInstance();
        datosCargados =  datosGenes.getDatos();
        List<String> bases = Arrays.asList("A", "T", "C", "G");
        List<Map<String,String>> respuestaFinal= new ArrayList<>();
        RespuestaPrediccionEstructura respuestaPrediccionEstructura = RespuestaPrediccionEstructura.getInstance();
        List<Map<String,String>> datosPrevios = respuestaPrediccionEstructura.getDatos();

        datosCargados.forEach((key, value) -> {
            if(datosPrevios == null || datosPrevios.stream().noneMatch(m -> m.containsValue(value))){
                List<Double> contadorBases = new ArrayList<>();
                Map<String,String> respuesta = new LinkedHashMap<>();
                for(String patron : bases ){
                    Pattern pattern = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(value);
                    int contador = 0;
                    while (matcher.find()) {
                        contador++;
                    }

                    double proporcion = value.isEmpty() ? 0.0 : (double) contador / (double) value.length();
                    contadorBases.add(proporcion*100);
                }
                String estructura = "";
                double a = contadorBases.get(0);
                double t = contadorBases.get(1);
                double c = contadorBases.get(2);
                double g = contadorBases.get(3);
                if ((a + t) > 50) {
                    estructura = "Estructura Alfa";
                } else if ((c + g) > 50) {
                    estructura = "Estructura Beta";
                } else {
                    estructura = "Estructura Mixta";
                }



                String textoRespuesta = String.format(
                        "Secuencia %s → %s (A=%.0f%%; T=%.0f%%; C=%.0f%%; G=%.0f%%)",
                        key.replace(",", ""), // quitar comas peligrosas
                        estructura,
                        a, t, c, g
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
        respuestaPrediccionEstructura.setDatos(respuestaFinal);
    }
}
