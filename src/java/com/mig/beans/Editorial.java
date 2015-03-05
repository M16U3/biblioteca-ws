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

public class Editorial implements Serializable {
    
    private Long editEditorial;
    private String editDescrip;

    public String getEditDescrip() {
        return editDescrip;
    }

    public Long getEditEditorial() {
        return editEditorial;
    }

    public void setEditDescrip(String editDescrip) {
        this.editDescrip = editDescrip;
    }

    public void setEditEditorial(Long editEditorial) {
        this.editEditorial = editEditorial;
    }
    
}
