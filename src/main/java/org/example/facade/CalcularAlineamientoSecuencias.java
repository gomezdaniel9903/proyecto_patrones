package org.example.facade;

import org.example.factory.FabricaAnalisisGenetico;
import org.example.factory.IAnalisisGenetico;
import org.example.factory.TipoAnalisis;

public class CalcularAlineamientoSecuencias {
    public void calcular(){
        FabricaAnalisisGenetico fabrica = new FabricaAnalisisGenetico();
        IAnalisisGenetico operacion = fabrica.crearAnalisis(TipoAnalisis.ALINEAMIENTO_SECUENCIAS);
        operacion.calcular();
    }
}
