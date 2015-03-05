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

public class Libro implements Serializable {
    
    private String alibLibro;
    private String alibrTitulo;
    private String alibrEdicion;
    private Long alibrEditorial;
    private Long alibrCategoria;

    public Long getAlibrCategoria() {
        return alibrCategoria;
    }

    public void setAlibrCategoria(Long alibrCategoria) {
        this.alibrCategoria = alibrCategoria;
    }
    
    
    public String getAlibLibro() {
        return alibLibro;
    }

    public String getAlibrEdicion() {
        return alibrEdicion;
    }

    public Long getAlibrEditorial() {
        return alibrEditorial;
    }

    public String getAlibrTitulo() {
        return alibrTitulo;
    }

    public void setAlibLibro(String alibLibro) {
        this.alibLibro = alibLibro;
    }

    public void setAlibrEdicion(String alibrEdicion) {
        this.alibrEdicion = alibrEdicion;
    }

    public void setAlibrEditorial(Long alibrEditorial) {
        this.alibrEditorial = alibrEditorial;
    }

    public void setAlibrTitulo(String alibrTitulo) {
        this.alibrTitulo = alibrTitulo;
    }
    
    
}
