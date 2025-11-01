package org.example.factory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FabricaAnalisisGenetico {
    private Map<TipoAnalisis, Supplier<IAnalisisGenetico>>  analisis = new HashMap<>();

    public FabricaAnalisisGenetico(){
        analisis.put(TipoAnalisis.ALINEAMIENTO_SECUENCIAS, AlineamientoSecuencias::new);
        analisis.put(TipoAnalisis.DETECCION_MOTIVOS, DeteccionMotivos::new);
        analisis.put(TipoAnalisis.PREDICCION_ESTRUCTURA, PrediccionEstructura::new);
    }
    public IAnalisisGenetico crearAnalisis(TipoAnalisis tipoAnalisis){
        return analisis.get(tipoAnalisis).get();
    }
}
