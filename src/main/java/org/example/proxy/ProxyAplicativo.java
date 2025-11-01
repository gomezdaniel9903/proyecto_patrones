package org.example.proxy;

import org.example.service.*;
import org.example.singleton.AnalisisSeleccionados;
import org.example.singleton.DatosGenes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class ProxyAplicativo implements IProxy {

    private  ParseoArchivoFasta parseoArchivoFastaService;
    private  GuardadoAlineamientoSecuencias guardadoSecuencias;
    private  GuardadoDeteccionMotivos guardadoDeteccionMotivos;
    private  GuardadoPrediccionEstructura guardadoPrediccionEstructura;
    private  VerificacionResultadoBD verificacionResultadoBD;
    private  MultipartFile file;
    private  List<String> opciones;
    private  CapaReal capaReal;

    public ProxyAplicativo(MultipartFile file, List<String> opciones, ParseoArchivoFasta parseoArchivoFasta,
                           GuardadoAlineamientoSecuencias guardadoSecuencias,
                           GuardadoDeteccionMotivos guardadoDeteccionMotivos,
                           GuardadoPrediccionEstructura guardadoPrediccionEstructura,
                           VerificacionResultadoBD verificacionResultadoBD) {
        this.file = file;
        this.opciones = opciones;
        this.parseoArchivoFastaService = parseoArchivoFasta;
        this.guardadoSecuencias = guardadoSecuencias;
        this.guardadoDeteccionMotivos = guardadoDeteccionMotivos;
        this.guardadoPrediccionEstructura = guardadoPrediccionEstructura;
        this.verificacionResultadoBD = verificacionResultadoBD;

    }

    public ProxyAplicativo() {

    }

    @Override
    public void inicioAnalisis(String opcion) {
        if(opcion.equalsIgnoreCase("GenerarReporte")){

            if(capaReal == null) {
                capaReal = new CapaReal();
            }

            capaReal.inicioAnalisis("GenerarReporte");

        }else{
            Map<String, String> resultadoParseo = parseoArchivoFastaService.parsearFasta(file);
            if(resultadoParseo.size() == 0){
                throw new IllegalArgumentException("el archivo está vacío o no tiene una estructura fasta para generar análisis");
            }
            resultadoParseo.forEach((key,value)->{
                if(value.isBlank()){
                    throw new IllegalArgumentException("Las cadenas genéticas no pueden ser vacías");
                }
            });

            AnalisisSeleccionados analisisSeleccionados = AnalisisSeleccionados.getInstance();
            analisisSeleccionados.setDatos(this.opciones);

            List<String> opcionesFinales = verificacionResultadoBD.verificacion(this.opciones,resultadoParseo);

            DatosGenes datosGenes = DatosGenes.getInstance();
            datosGenes.setDatos(resultadoParseo);

            if(capaReal == null) {
                capaReal = new CapaReal();
            }
            for(String opt:opcionesFinales){
                capaReal.inicioAnalisis(opt);
                switch (opt) {
                    case "Calcular Alineamiento":
                        guardadoSecuencias.guardado();
                        break;
                    case "Detección de Motivo":
                        guardadoDeteccionMotivos.guardado();
                        break;
                    case "Predicción de Estructura":
                        guardadoPrediccionEstructura.guardado();
                        break;
                }
            }

        }






    }
}
