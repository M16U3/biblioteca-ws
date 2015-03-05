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

public class Auth implements Serializable {
    
    private String authId;
    private String authUsuario;
    private Date authFecha;

    public Date getAuthFecha() {
        return authFecha;
    }

    public String getAuthId() {
        return authId;
    }

    public String getAuthUsuario() {
        return authUsuario;
    }

    public void setAuthFecha(Date authFecha) {
        this.authFecha = authFecha;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public void setAuthUsuario(String authUsuario) {
        this.authUsuario = authUsuario;
    }
    
}
