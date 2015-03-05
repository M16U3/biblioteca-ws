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

public class AutorQr implements Serializable {

    private boolean dbExterno = false;
    private Dao db;
    private Autor autor;
    private List<Autor> lista = new ArrayList();
    private String error;

    public AutorQr() {
        this.dbExterno = false;
    }

    public AutorQr(Dao db) {
        this.db = db;
        this.dbExterno = true;
    }

    public void setId(String id) {
        try {

            db = dbExterno ? db : new Dao();
            ResultSet rst = db.consultar("Select auto_autor,auto_nombre,auto_apellidos From Autor Where auto_autor = '" + id + "'");
            
            while (rst != null && rst.next()) {
                autor = new Autor();
                autor.setAutoAutor(rst.getLong("auto_autor"));
                autor.setNombre(rst.getString("auto_nombre"));
                autor.setApellidos(rst.getString("auto_apellidos"));
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
            ResultSet rst = db.consultar("Select auto_autor,auto_nombre,auto_apellidos From Autor " + condicion);            

            while (rst != null && rst.next()) {
                autor = new Autor();
                autor.setAutoAutor(rst.getLong("auto_autor"));
                autor.setNombre(rst.getString("auto_nombre"));
                autor.setApellidos(rst.getString("auto_apellidos"));
                lista.add(autor);
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

    public List<Autor> getAutores() {
        return lista;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getError() {
        return error;
    }
}
