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

public class ApartadoQr implements Serializable {

    private boolean dbExterno = false;
    private List<Apartado> lista = new ArrayList();
    private Apartado apart;
    private Dao db;
    private String error;

    public ApartadoQr() {
        this.dbExterno = false;
    }

    public ApartadoQr(Dao db) {
        this.db = db;
        this.dbExterno = false;
    }

    public void setId(String id) {
        try {
            db = dbExterno ? db : new Dao();

            try {

                ResultSet rst = db.consultar("Select apar_apartado,apar_usuario,apar_isbn,apar_fecha From Apartado Where apar_apartado = '" + id + "'");
                

                while (rst != null && rst.next()) {
                    apart = new Apartado();
                    apart.setAparApartado(rst.getLong("apar_apartado"));
                    apart.setAparusuario(rst.getString("apar_usuario"));
                    apart.setAparIsbn(rst.getString("apar_isbn"));
                    apart.setAparFecha(rst.getDate("apar_fecha"));
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
        }
    }
    
    
    public void setCondicion(String condicion) {
        try {
            db = dbExterno ? db : new Dao();

            try {
                
                ResultSet rst = db.consultar("Select apar_apartado,apar_usuario,apar_isbn,apar_fecha From Apartado "+condicion);

                while (rst != null && rst.next()) {
                    apart = new Apartado();
                    apart.setAparApartado(rst.getLong("apar_apartado"));
                    apart.setAparusuario(rst.getString("apar_usuario"));
                    apart.setAparIsbn(rst.getString("apar_isbn"));
                    apart.setAparFecha(rst.getDate("apar_fecha"));
                    lista.add(apart);
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
        }
    }
    
    
    public List<Apartado> getApartados() {
        return lista;
    }
    
    public Apartado getApartado() {
        return apart;
    }
    
    public String getError() {
        return error;
    }
    
    public Long apartar(String usuario, String isbn) {
        
        Long id = 0L;
        
        try {            
           this.setCondicion(" Where apar_isbn = '"+isbn+"' ");
           if (this.getApartado() != null) {
               throw new Exception("El libro "+isbn+" ha sido apartado por otro usuario...");
           } else {
               Apartado apar = new Apartado();
               apar.setAparusuario(usuario);
               apar.setAparIsbn(isbn);
               this.agrApartado(apar);
               this.setCondicion(" Where apar_isbn = '"+isbn+"' And apar_usuario = '"+usuario+"' ");
               id = this.getApartado().getAparApartado();
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
        
     return id;   
    }
    
    public void agrApartado(Apartado apr) {        
        try {            
            db = dbExterno ? db : new Dao();            
            db.insertar("Insert Into apartado (apar_usuario,apar_isbn) VALUES ('"+apr.getAparusuario()+"','"+apr.getAparIsbn()+"') ");                        
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
    
    public void borApartado(Long id) {
        try {            
            db = dbExterno ? db : new Dao();            
            db.borrar("Delete From Apartado Where apar_apartado = '"+id.toString()+"' ");                        
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
    
    
    
}
