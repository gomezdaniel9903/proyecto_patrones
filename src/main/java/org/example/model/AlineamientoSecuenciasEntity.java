package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alineamiento_secuencias")
public class AlineamientoSecuenciasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre1;
    private String nombre2;
    private String secuencia1;
    private String secuencia2;
    private String respuesta;


    public AlineamientoSecuenciasEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecuencia1() {
        return secuencia1;
    }

    public void setSecuencia1(String secuencia1) {
        this.secuencia1 = secuencia1;
    }

    public String getSecuencia2() {
        return secuencia2;
    }

    public void setSecuencia2(String secuencia2) {
        this.secuencia2 = secuencia2;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
}