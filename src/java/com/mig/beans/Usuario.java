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

public class Usuario implements Serializable {
    
    private String usuaUsuario;
    private String usuaPasword;
    private String usuaNombre;
    private String usuaEmail;

    public String getUsuaEmail() {
        return usuaEmail;
    }

    public String getUsuaNombre() {
        return usuaNombre;
    }

    public String getUsuaPasword() {
        return usuaPasword;
    }

    public String getUsuaUsuario() {
        return usuaUsuario;
    }

    public void setUsuaEmail(String usuaEmail) {
        this.usuaEmail = usuaEmail;
    }

    public void setUsuaNombre(String usuaNombre) {
        this.usuaNombre = usuaNombre;
    }

    public void setUsuaPasword(String usuaPasword) {
        this.usuaPasword = usuaPasword;
    }

    public void setUsuaUsuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }
     
    
}
