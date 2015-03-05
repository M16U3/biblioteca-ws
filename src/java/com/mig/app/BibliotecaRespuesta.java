/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.app;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.util.List;
import com.mig.beans.LibroDescripcion;

public class BibliotecaRespuesta implements Serializable {
    
    private List<LibroDescripcion> libroDescrip;
    private String mensaje;
    

    public BibliotecaRespuesta() {
    }

    public BibliotecaRespuesta(List<LibroDescripcion> libroDescrip, String mensaje) {
        this.libroDescrip = libroDescrip;
        this.mensaje = mensaje;
    }
    

    public String getMensaje() {
        return mensaje;
    }

    public List<LibroDescripcion> getLibroDescrip() {
        return libroDescrip;
    }

    public void setLibroDescrip(List<LibroDescripcion> libroDescrip) {
        this.libroDescrip = libroDescrip;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
