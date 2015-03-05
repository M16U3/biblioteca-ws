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
import java.util.Date;


public class Apartado implements Serializable {
    
    private Long aparApartado;
    private String aparusuario;
    private Date aparFecha;
    private String aparIsbn;

    public Long getAparApartado() {
        return aparApartado;
    }

    public Date getAparFecha() {
        return aparFecha;
    }

    public String getAparIsbn() {
        return aparIsbn;
    }

    public String getAparusuario() {
        return aparusuario;
    }

    public void setAparApartado(Long aparApartado) {
        this.aparApartado = aparApartado;
    }

    public void setAparFecha(Date aparFecha) {
        this.aparFecha = aparFecha;
    }

    public void setAparIsbn(String aparIsbn) {
        this.aparIsbn = aparIsbn;
    }

    public void setAparusuario(String aparusuario) {
        this.aparusuario = aparusuario;
    }
    
}
