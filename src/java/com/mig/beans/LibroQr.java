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

public class LibroQr implements Serializable {

    private boolean dbExterno = false;
    private Dao db;
    private Libro libro;
    private List<Libro> lista = new ArrayList();
    private String error;

    public LibroQr() {
        dbExterno = false;
    }

    public LibroQr(Dao db) {
        this.db = db;
        this.dbExterno = true;
    }

    public void setId(String id) {

        try {

            db = dbExterno ? db : new Dao();

            ResultSet rst = db.consultar("Select libr_isbn,libr_titulo,libr_edicion,libr_editorial,libr_categoria From Libro "
                    + "Where libr_libro = '" + id + "'");
            
            while (rst != null && rst.next()) {
                libro = new Libro();
                libro.setAlibrEditorial(rst.getLong("libr_editorial"));
                libro.setAlibrEdicion(rst.getString("libr_edicion"));
                libro.setAlibLibro(rst.getString("libr_isbn"));
                libro.setAlibrTitulo(rst.getString("libr_titulo"));
                libro.setAlibrCategoria(rst.getLong("libr_categoria"));
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
            ResultSet rst = db.consultar("Select libr_isbn,libr_titulo,libr_edicion,libr_editorial,libr_categoria From Libro " + condicion);
            
            while (rst != null && rst.next()) {
                libro = new Libro();
                libro.setAlibrEditorial(rst.getLong("libr_editorial"));
                libro.setAlibrEdicion(rst.getString("libr_edicion"));
                libro.setAlibLibro(rst.getString("libr_isbn"));
                libro.setAlibrTitulo(rst.getString("libr_titulo"));
                libro.setAlibrCategoria(rst.getLong("libr_categoria"));
                lista.add(libro);
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

    public List<Libro> getLibros() {
        return lista;
    }

    public Libro getLibro() {
        return libro;
    }

    public String getError() {
        return error;
    }    
    
}
