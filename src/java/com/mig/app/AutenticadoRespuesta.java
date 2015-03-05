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


public class AutenticadoRespuesta implements Serializable {
    
    private String token;
    private String mensaje;

    public AutenticadoRespuesta() {
    }

    public AutenticadoRespuesta(String token, String mensaje) {
        this.token = token;
        this.mensaje = mensaje;
    }

    
    public String getMensaje() {
        return mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
