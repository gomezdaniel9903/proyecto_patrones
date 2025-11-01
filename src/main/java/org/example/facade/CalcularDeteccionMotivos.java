package org.example.facade;

import org.example.factory.FabricaAnalisisGenetico;
import org.example.factory.IAnalisisGenetico;
import org.example.factory.TipoAnalisis;

public class CalcularDeteccionMotivos {
    public void calcular(){
        FabricaAnalisisGenetico fabrica = new FabricaAnalisisGenetico();
        IAnalisisGenetico operacion = fabrica.crearAnalisis(TipoAnalisis.DETECCION_MOTIVOS);
        operacion.calcular();
    }
}
