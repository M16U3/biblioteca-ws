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


public class LibroDescripcion implements Serializable {
    
    private String isbn;
    private String autores;
    private String titulo;
    private String editorial;
    private String edicion;
    private String estatus;
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    

    public String getAutores() {
        return autores;
    }

    public String getEdicion() {
        return edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
}
