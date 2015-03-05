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

public class CategoriaQr implements Serializable {

    private boolean dbExterno = false;
    private Dao db;
    private Categoria cate;
    private List<Categoria> lista = new ArrayList();
    private String error;

    public CategoriaQr() {
        this.dbExterno = true;
    }

    public CategoriaQr(Dao db) {
        this.db = db;
        this.dbExterno = true;
    }

    public void setId(String id) {
        try {

            db = dbExterno ? db : new Dao();

            ResultSet rst = db.consultar("Select cate_categoria,cate_descrip From Categoria Where cate_categoria = '" + id + "'");            

            while (rst != null && rst.next()) {
                cate = new Categoria();
                cate.setCateCategoria(rst.getLong("cate_categoria"));
                cate.setCateDescrip(rst.getString("cate_descrip"));
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

            try {

                db = dbExterno ? db : new Dao();

                ResultSet rst = db.consultar("Select cate_categoria,cate_descrip From Categoria "+condicion);                

                while (rst != null && rst.next()) {
                    cate = new Categoria();
                    cate.setCateCategoria(rst.getLong("cate_categoria"));
                    cate.setCateDescrip(rst.getString("cate_descrip"));
                    lista.add(cate);
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

    public List<Categoria> getCategorias() {
        return lista;
    }

    public Categoria getCategoria() {
        return cate;
    }

    public String getError() {
        return error;
    }
}
