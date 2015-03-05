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


public class Categoria implements Serializable {
    
    private Long cateCategoria;
    private String cateDescrip;

    public Long getCateCategoria() {
        return cateCategoria;
    }

    public String getCateDescrip() {
        return cateDescrip;
    }

    public void setCateCategoria(Long cateCategoria) {
        this.cateCategoria = cateCategoria;
    }

    public void setCateDescrip(String cateDescrip) {
        this.cateDescrip = cateDescrip;
    }
    
}
