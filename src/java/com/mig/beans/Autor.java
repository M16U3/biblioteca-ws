/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.beans;

/**
 *
 * @author miguel
 */
import java.io.Serializable;

public class Autor implements Serializable {
    
    private Long autoAutor;
    private String nombre;
    private String apellidos;

    public String getApellidos() {
        return apellidos;
    }

    public Long getAutoAutor() {
        return autoAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setAutoAutor(Long autoAutor) {
        this.autoAutor = autoAutor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
