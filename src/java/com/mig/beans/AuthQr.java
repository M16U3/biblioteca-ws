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
import mx.mig.util.CifraBean;

public class AuthQr implements Serializable {
    
    private boolean dbExterno = false;
    private Dao db;
    private Auth auth;
    private List<Auth> lista = new ArrayList();
    private String error;

    public AuthQr() {
        dbExterno = false;
    }

    public AuthQr(Dao db) {
        this.db = db;
        this.dbExterno = true;
    }

    public void setId(String id) {

        try {

            db = dbExterno ? db : new Dao();

          ResultSet rst = db.consultar("Select auth_id,auth_usuario,auth_fecha From Auth Where auth_id = '"+id+"'");             

            while (rst != null && rst.next()) {
                auth = new Auth();
                auth.setAuthId(rst.getString("auth_id"));
                auth.setAuthUsuario(rst.getString("auth_usuario"));
                auth.setAuthFecha(rst.getDate("auth_fecha"));
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

            ResultSet rst = db.consultar("Select auth_id,auth_usuario,auth_fecha From Auth "+condicion);

            while (rst != null && rst.next()) {
                auth = new Auth();
                auth.setAuthId(rst.getString("auth_id"));
                auth.setAuthUsuario(rst.getString("auth_usuario"));
                auth.setAuthFecha(rst.getDate("auth_fecha"));
                lista.add(auth);
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

    public List<Auth> getAuths() {
        return lista;
    }

    public Auth getAuth() {
        return auth;
    }

    public String getError() {
        return error;
    }
    
    
    public String getTokenAuth(String usuario, String password) {
        String token = null;
        
        try {
            db = dbExterno ? db : new Dao();
            
            UsuarioQr usu = new UsuarioQr(db);
            usu.setCondicion(" Where usua_usuario = '"+usuario+"' And usua_password = '"+CifraBean.hashMD5(password)+"' ");

            if (usu.getUsuario() != null) {

                this.borAuth(usuario);
                Auth auth = new Auth();
                token = CifraBean.hashMD5();
                auth.setAuthId(token);
                auth.setAuthUsuario(usuario);
                this.agrAuth(auth);
                
            } else {
                throw new Exception ("Nombre de usuario o contrasena incorrectos!");
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
        
      return token;  
    }
    
    public void agrAuth(Auth auth) {        
        try {
            db = dbExterno ? db : new Dao();
            db.insertar("Insert into Auth(auth_id,auth_usuario) VALUES ('"+auth.getAuthId()+"','"+auth.getAuthUsuario()+"')");                        
        } catch (Exception e) {
            error = e.getMessage();
        } finally {
            if (!dbExterno){
                try {
                    db.desconectar();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }
        }                
    }
    
    public void borAuth(String usuario) {
        try {
            db = dbExterno ? db : new Dao();
            db.borrar("Delete From Auth Where auth_usuario =  '"+usuario+"'");                        
        } catch (Exception e) {
            error = e.getMessage();
        } finally {
            if (!dbExterno){
                try {
                    db.desconectar();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }
        }        
    }
    
    
}
