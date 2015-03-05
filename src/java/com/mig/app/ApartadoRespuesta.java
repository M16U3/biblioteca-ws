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

public class ApartadoRespuesta implements Serializable {
    
    private Long apartadoId;
    private String mensaje;
    private LibroDescripcion libroApartado;

    public ApartadoRespuesta() {
    }

    public ApartadoRespuesta(Long apartadoId, String mensaje, LibroDescripcion libroApartado) {
        this.apartadoId = apartadoId;
        this.mensaje = mensaje;
        this.libroApartado = libroApartado;
    }

    public Long getApartadoId() {
        return apartadoId;
    }

    public LibroDescripcion getLibroApartado() {
        return libroApartado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setApartadoId(Long apartadoId) {
        this.apartadoId = apartadoId;
    }

    public void setLibroApartado(LibroDescripcion libroApartado) {
        this.libroApartado = libroApartado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
