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
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import mx.mig.dbc.Dao;

public class EditorialQr implements Serializable {

    private boolean dbExterno = false;
    private Dao db;
    private Editorial edit;
    private List<Editorial> lista = new ArrayList();
    private String error;

    public EditorialQr() {
        this.dbExterno = true;
    }

    public EditorialQr(Dao db) {
        this.db = db;
        this.dbExterno = true;
    }

    public void setId(String id) {
        try {

            db = dbExterno ? db : new Dao();

            ResultSet rst = db.consultar("Select edit_editorial,edit_descrip From Editorial Where edit_editorial = '" + id + "'");            

            while (rst != null && rst.next()) {
                edit = new Editorial();
                edit.setEditEditorial(rst.getLong("edit_editorial"));
                edit.setEditDescrip(rst.getString("edit_descrip"));
            }

        } catch (Exception e) {
            error = e.getMessage();
        } finally {
            if (!dbExterno) {
                try {
                    db.desconectar();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }
        }
    }

    public void setCondicion(String condicion) {
        try {

            db = dbExterno ? db : new Dao();

            ResultSet rst = db.consultar("Select edit_editorial,edit_descrip From Editorial " + condicion);
            
            while (rst != null && rst.next()) {
                edit = new Editorial();
                edit.setEditEditorial(rst.getLong("edit_editorial"));
                edit.setEditDescrip(rst.getString("edit_descrip"));
                lista.add(edit);
            }

        } catch (Exception e) {
            error = e.getMessage();
        } finally {
            if (!dbExterno) {
                try {
                    db.desconectar();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }
        }
    }

    public List<Editorial> getEditoriales() {
        return lista;
    }

    public Editorial getEditorial() {
        return edit;
    }

    public String getError() {
        return error;
    }
}
