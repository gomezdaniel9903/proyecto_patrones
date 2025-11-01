package org.example.factory;

import org.example.singleton.DatosGenes;
import org.example.singleton.RespuestaAlineamientoSecuencias;

import java.util.*;

public class AlineamientoSecuencias implements IAnalisisGenetico{
    private Map<String, String> datosCargados;
    @Override
    public void calcular() {
        DatosGenes datosGenes = DatosGenes.getInstance();
        datosCargados =  datosGenes.getDatos();
        List<Double> longitudes = new ArrayList<>();
        List<String> nombres = new ArrayList<>();
        Map<String, String> primerosDos = datosCargados.entrySet()
                .stream()
                .limit(2)
                .collect(
                        LinkedHashMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        Map::putAll
                );
        primerosDos.forEach((key, value) -> {
            longitudes.add((double) value.length());
            nombres.add(key);
        });

        int indiceMax = longitudes.indexOf(Collections.max(longitudes));
        int indiceMin = longitudes.indexOf(Collections.min(longitudes));
        String nombreCadenaMayorLongitud = nombres.get(indiceMax);
        String nombreCadenaMenorLongitud = nombres.get(indiceMin);

        double porcentajeSimilitud = Math.round((longitudes.get(indiceMin) / longitudes.get(indiceMax)) * 100);;

        RespuestaAlineamientoSecuencias respuestaAlineamientoSecuencias = RespuestaAlineamientoSecuencias.getInstance();
        String respuestaString = "";
        if(porcentajeSimilitud == 100){
            respuestaString = "Las cadenas tienen un 100% de similitud";
        }else{
            respuestaString = String.format(
                    "La cadena más extensa es '%s'. Similitud con '%s': %.0f%%",
                    nombreCadenaMayorLongitud.replace(",", " "),
                    nombreCadenaMenorLongitud.replace(",", " "),
                    porcentajeSimilitud
            );
        }
        Map<String, String> respuesta = new LinkedHashMap<>();
        respuesta.put("nombre1", datosCargados.keySet().toArray()[0].toString());
        respuesta.put("cadenaUno", datosCargados.values().toArray()[0].toString());
        respuesta.put("nombre2", datosCargados.keySet().toArray()[1].toString());
        respuesta.put("cadenaDos", datosCargados.values().toArray()[1].toString());
        respuesta.put("respuesta",respuestaString);
        respuestaAlineamientoSecuencias.setDatos(respuesta);

    }
}
