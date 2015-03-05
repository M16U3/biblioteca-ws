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



public class UsuarioQr implements Serializable {
 
    private boolean dbExterno = false;
    private Dao db;
    private Usuario usua;
    private List<Usuario> lista = new ArrayList();
    private String error;

    public UsuarioQr() {
        dbExterno = false;
    }

    public UsuarioQr(Dao db) {
        this.db = db;
        this.dbExterno = true;
    }

    public void setId(String id) {

        try {

            db = dbExterno ? db : new Dao();

            ResultSet rst = db.consultar("Select usua_usuario,usua_password,usua_nombre,usua_email From Usuario Where usua_usuario = '" + id + "'");             

            while (rst != null && rst.next()) {
               usua = new Usuario();
               usua.setUsuaNombre(rst.getString("usua_usuario"));
               usua.setUsuaPasword(rst.getString("usua_password"));
               usua.setUsuaEmail(rst.getString("usua_email"));
               usua.setUsuaUsuario(rst.getString("usua_usuario"));               
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

            ResultSet rst = db.consultar("Select usua_usuario,usua_password,usua_nombre,usua_email From Usuario "+condicion);            

            while (rst != null && rst.next()) {
               usua = new Usuario();
               usua.setUsuaNombre(rst.getString("usua_usuario"));
               usua.setUsuaPasword(rst.getString("usua_password"));
               usua.setUsuaEmail(rst.getString("usua_email"));
               usua.setUsuaUsuario(rst.getString("usua_usuario"));    
               lista.add(usua);
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

    public List<Usuario> getUsuarios() {
        return lista;
    }

    public Usuario getUsuario() {
        return usua;
    }

    public String getError() {
        return error;
    }
    
}
