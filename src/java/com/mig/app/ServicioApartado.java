/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.app;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mx.mig.dbc.Dao;
import com.mig.beans.ApartadoQr;
import com.mig.beans.EditorialQr;
import com.mig.beans.LibroQr;
import com.mig.beans.Libro;
import com.mig.beans.UsuarioQr;
import com.mig.beans.AutorQr;
import com.mig.beans.Autor;
import com.mig.beans.AuthQr;
import com.mig.beans.LibroDescripcion;
import com.mig.beans.CategoriaQr;

@WebService(name = "ServicioApartado")
public class ServicioApartado implements Serializable {

    @WebMethod(operationName = "autenticar")
    public AutenticadoRespuesta autenticar(@WebParam(name = "usuario") String usuario, @WebParam(name = "password") String password) {
        String token = null;
        AutenticadoRespuesta resp = new AutenticadoRespuesta();
        try {
            Dao db = new Dao();
            AuthQr auth = new AuthQr(db);
            token = auth.getTokenAuth(usuario, password);            
            resp.setToken(token);
            resp.setMensaje(auth.getError());
            db.desconectar();
        } catch (IOException e) {
            resp.setMensaje(e.getMessage());            
        } catch (SQLException e) {
            resp.setMensaje(e.getMessage());
        }
        
      return resp;
    }

    @WebMethod(operationName = "consultarLibros")
    public BibliotecaRespuesta consultarLibros(@WebParam(name = "usuario") String usuario, @WebParam(name = "token") String token) {

        List<LibroDescripcion> libros = new ArrayList();
        BibliotecaRespuesta resp = new BibliotecaRespuesta();

        AuthQr at = new AuthQr();
        at.setCondicion("Where auth_id = '" + token + "' And auth_usuario = '" + usuario + "' ");

        if (at.getAuth() != null) {
            try {
                Dao db = new Dao();

                LibroQr li = new LibroQr(db);
                li.setCondicion(" Order by libr_titulo ");

                for (Libro l : li.getLibros()) {
                    LibroDescripcion desc = new LibroDescripcion();
                    EditorialQr edi = new EditorialQr(db);
                    edi.setId(l.getAlibrEditorial().toString());

                    desc.setIsbn(l.getAlibLibro());
                    desc.setEditorial(edi.getEditorial().getEditDescrip());
                    desc.setEdicion(l.getAlibrEdicion());
                    desc.setTitulo(l.getAlibrTitulo());
                    String autores = "";
                    ResultSet rst = db.consultar("Select alib_autor From autorlibro Where alib_isbn = '" + l.getAlibLibro() + "' ");

                    while (rst != null && rst.next()) {
                        AutorQr au = new AutorQr(db);
                        au.setId(rst.getString("alib_autor"));
                        autores += au.getAutor().getNombre() + " " + au.getAutor().getApellidos() + " ";
                    }
                    desc.setAutores(autores);

                    ApartadoQr ap = new ApartadoQr(db);
                    ap.setCondicion("Where apar_isbn = '" + l.getAlibLibro() + "' ");
                    String estatus = ap.getApartado() != null ? "Apartado" : "Disponible";

                    CategoriaQr cate = new CategoriaQr(db);
                    cate.setId(l.getAlibrCategoria().toString());

                    desc.setCategoria(cate.getCategoria().getCateDescrip());

                    desc.setEstatus(estatus);

                    libros.add(desc);                    
                }
                resp.setLibroDescrip(libros);
                resp.setMensaje(li.getError());
                at = new AuthQr(db);
                at.borAuth(usuario);
                db.desconectar();
            } catch (IOException e) {
                resp.setMensaje(e.getMessage());                
            } catch (SQLException e) {
                resp.setMensaje(e.getMessage());                
            }
        } else {
            resp.setMensaje("Se ha terminado su sesion...");
        }

        return resp;
    }

    @WebMethod(operationName = "buscarAutor")
    public BibliotecaRespuesta buscarAutor(@WebParam(name = "usuario") String usuario, @WebParam(name = "token") String token, @WebParam(name = "consulta") String consulta) {
        List<LibroDescripcion> libros = new ArrayList();
        BibliotecaRespuesta resp = new BibliotecaRespuesta();

        AuthQr at = new AuthQr();
        at.setCondicion("Where auth_id = '" + token + "' And auth_usuario = '" + usuario + "' ");

        if (at.getAuth() != null) {
            try {
                Dao db = new Dao();

                consulta = consulta.toUpperCase();
                LibroQr li = new LibroQr(db);
                li.setCondicion(" Where libr_isbn in (Select alib_isbn From autorlibro,autor Where auto_autor = alib_autor And auto_nombre like '%" + consulta + "%') ");

                for (Libro l : li.getLibros()) {
                    LibroDescripcion desc = new LibroDescripcion();
                    EditorialQr edi = new EditorialQr(db);
                    edi.setId(l.getAlibrEditorial().toString());

                    desc.setIsbn(l.getAlibLibro());
                    desc.setEditorial(edi.getEditorial().getEditDescrip());
                    desc.setEdicion(l.getAlibrEdicion());
                    desc.setTitulo(l.getAlibrTitulo());
                    String autores = "";
                    ResultSet rst = db.consultar("Select alib_autor From autorlibro Where alib_isbn = '" + l.getAlibLibro() + "' ");

                    while (rst != null && rst.next()) {
                        AutorQr au = new AutorQr(db);
                        au.setId(rst.getString("alib_autor"));
                        autores += au.getAutor().getNombre() + " " + au.getAutor().getApellidos() + " ";
                    }
                    desc.setAutores(autores);

                    ApartadoQr ap = new ApartadoQr(db);
                    ap.setCondicion("Where apar_isbn = '" + l.getAlibLibro() + "' ");
                    String estatus = ap.getApartado() != null ? "Apartado" : "Disponible";

                    CategoriaQr cate = new CategoriaQr(db);
                    cate.setId(l.getAlibrCategoria().toString());

                    desc.setCategoria(cate.getCategoria().getCateDescrip());

                    desc.setEstatus(estatus);

                    libros.add(desc);
                }
                resp.setLibroDescrip(libros);
                resp.setMensaje(li.getError());
                at = new AuthQr(db);
                at.borAuth(usuario);
                db.desconectar();
            } catch (IOException e) {
                resp.setMensaje(e.getMessage());
            } catch (SQLException e) {
                resp.setMensaje(e.getMessage());
            }
        } else {
            resp.setMensaje("Su sesion ha terminado...");
        }

        return resp;
    }

    @WebMethod(operationName = "buscarTitulo")
    public BibliotecaRespuesta buscarTitulo(@WebParam(name = "usuario") String usuario, @WebParam(name = "token") String token, @WebParam(name = "consulta") String consulta) {
        List<LibroDescripcion> libros = new ArrayList();
        BibliotecaRespuesta resp = new BibliotecaRespuesta();
        
        AuthQr at = new AuthQr();
        at.setCondicion("Where auth_id = '" + token + "' And auth_usuario = '" + usuario + "' ");

        if (at.getAuth() != null) {
            try {
                Dao db = new Dao();

                consulta = consulta.toUpperCase();
                LibroQr li = new LibroQr(db);
                li.setCondicion(" Where libr_isbn like '%" + consulta + "%' ");

                for (Libro l : li.getLibros()) {
                    LibroDescripcion desc = new LibroDescripcion();
                    EditorialQr edi = new EditorialQr(db);
                    edi.setId(l.getAlibrEditorial().toString());

                    desc.setIsbn(l.getAlibLibro());
                    desc.setEditorial(edi.getEditorial().getEditDescrip());
                    desc.setEdicion(l.getAlibrEdicion());
                    desc.setTitulo(l.getAlibrTitulo());
                    String autores = "";
                    ResultSet rst = db.consultar("Select alib_autor From autorlibro Where alib_isbn = '" + l.getAlibLibro() + "' ");

                    while (rst != null && rst.next()) {
                        AutorQr au = new AutorQr(db);
                        au.setId(rst.getString("alib_autor"));
                        autores += au.getAutor().getNombre() + " " + au.getAutor().getApellidos() + " ";
                    }
                    desc.setAutores(autores);

                    ApartadoQr ap = new ApartadoQr(db);
                    ap.setCondicion("Where apar_isbn = '" + l.getAlibLibro() + "' ");
                    String estatus = ap.getApartado() != null ? "Apartado" : "Disponible";

                    CategoriaQr cate = new CategoriaQr(db);
                    cate.setId(l.getAlibrCategoria().toString());
                    desc.setCategoria(cate.getCategoria().getCateDescrip());
                    desc.setEstatus(estatus);
                    libros.add(desc);                    
                }
                
                resp.setLibroDescrip(libros);
                resp.setMensaje(li.getError());
                at = new AuthQr(db);
                at.borAuth(usuario);
                db.desconectar();
            } catch (IOException e) {
                resp.setMensaje(e.getMessage());
            } catch (SQLException e) {
                resp.setMensaje(e.getMessage());
            }

        } else {
            at.borAuth(usuario);
            resp.setMensaje("Su sesion ha terminado...");
        }

        return resp;
    }

    @WebMethod(operationName = "apartar")
    public ApartadoRespuesta apartar(@WebParam(name = "usuario") String usuario, @WebParam(name = "token") String token, @WebParam(name = "isbn") String isbn) {

        LibroDescripcion desc = new LibroDescripcion();

        ApartadoRespuesta resp = new ApartadoRespuesta();
        
        
        AuthQr at = new AuthQr();
        at.setCondicion("Where auth_id = '" + token + "' And auth_usuario = '" + usuario + "' ");

        if (at.getAuth() != null) {
            try {
                Dao db = new Dao();

                ApartadoQr ap = new ApartadoQr(db);
                ap.setCondicion(" Where apar_isbn = '" + isbn + "' ");

                if (ap.getApartado() != null) {
                    at = new AuthQr(db);
                    at.borAuth(usuario);
                    db.desconectar();
                    resp.setMensaje("uh! alguien lo acaba de apartar!...");
                } else {

                    Long id = ap.apartar(usuario, isbn);
                    LibroQr li = new LibroQr(db);
                    li.setCondicion(" Where libr_isbn = '" + isbn + "'");
                    
                    for (Libro l : li.getLibros()) {
                        desc = new LibroDescripcion();
                        EditorialQr edi = new EditorialQr(db);
                        edi.setId(l.getAlibrEditorial().toString());

                        desc.setIsbn(l.getAlibLibro());
                        desc.setEditorial(edi.getEditorial().getEditDescrip());
                        desc.setEdicion(l.getAlibrEdicion());
                        desc.setTitulo(l.getAlibrTitulo());
                        String autores = "";
                        ResultSet rst = db.consultar("Select alib_autor From autorlibro Where alib_isbn = '" + l.getAlibLibro() + "' ");

                        while (rst != null && rst.next()) {
                            AutorQr au = new AutorQr(db);
                            au.setId(rst.getString("alib_autor"));
                            autores += au.getAutor().getNombre() + " " + au.getAutor().getApellidos() + " ";
                        }
                        desc.setAutores(autores);
                        desc.setEstatus("Apartado por mi");
                    }
                    resp.setApartadoId(id);                    
                    resp.setLibroApartado(desc);
                    resp.setMensaje(ap.getError());
                    
                    at = new AuthQr(db);
                    at.borAuth(usuario);
                    db.desconectar();
                }
            } catch (IOException e) {
                resp.setMensaje(e.getMessage());
            } catch (SQLException e) {
                resp.setMensaje(e.getMessage());
            }
        } else {
            resp.setMensaje("Su sesion ha terminado...");
        }

        return resp;
    }

    @WebMethod(operationName = "cancelarApartado")
    public BibliotecaRespuesta cancelarApartado(@WebParam(name = "usuario") String usuario, @WebParam(name = "apartado") Long apartado, @WebParam(name = "token") String token) {

        BibliotecaRespuesta resp = new BibliotecaRespuesta();
        
        AuthQr at = new AuthQr();
        at.setCondicion(" Where auth_id = '" + token + "' And auth_usuario = '" + usuario + "' ");

        if (at.getAuth() != null) {
            try {
                Dao db = new Dao();
                ApartadoQr ap = new ApartadoQr(db);
                ap.setCondicion(" Where apar_usuario = '" + usuario + "' And apar_apartado = '" + apartado.toString() + "'");

                if (ap.getApartado() != null) {
                    ap.borApartado(apartado);
                }
                resp.setMensaje(ap.getError());
                
            } catch (IOException e) {
                resp.setMensaje(e.getMessage());
            } catch (SQLException e) {
                resp.setMensaje(e.getMessage());
            }

        } else {
            resp.setMensaje("Su sesion ha terminado...");
        }
      return resp;  
    }

    @WebMethod(operationName = "misLibrosApartados")
    public BibliotecaRespuesta misLibrosApartados(@WebParam(name = "usuario") String usuario, @WebParam(name = "token") String token) {
        
        BibliotecaRespuesta resp = new BibliotecaRespuesta();
        List<LibroDescripcion> listLibros = new ArrayList();
        AuthQr at = new AuthQr();
        at.setCondicion("Where auth_id = '" + token + "' And auth_usuario = '" + usuario + "' ");

        if (at.getAuth() != null) {
            try {
                Dao db = new Dao();

                LibroQr li = new LibroQr(db);
                li.setCondicion(" Where libr_isbn in (Select apar_isbn From apartado Where apar_usuario = '" + usuario + "') ");

                for (Libro l : li.getLibros()) {
                    LibroDescripcion desc = new LibroDescripcion();
                    EditorialQr edi = new EditorialQr(db);
                    edi.setId(l.getAlibrEditorial().toString());

                    desc.setIsbn(l.getAlibLibro());
                    desc.setEditorial(edi.getEditorial().getEditDescrip());
                    desc.setEdicion(l.getAlibrEdicion());
                    desc.setTitulo(l.getAlibrTitulo());
                    String autores = "";
                    ResultSet rst = db.consultar("Select alib_autor From autorlibro Where alib_isbn = '" + l.getAlibLibro() + "' ");

                    while (rst != null && rst.next()) {
                        AutorQr au = new AutorQr(db);
                        au.setId(rst.getString("alib_autor"));
                        autores += au.getAutor().getNombre() + " " + au.getAutor().getApellidos() + " ";
                    }
                    desc.setAutores(autores);

                    ApartadoQr ap = new ApartadoQr(db);
                    ap.setCondicion("Where apar_isbn = '" + l.getAlibLibro() + "' ");
                    String estatus = ap.getApartado() != null ? "Apartado" : "Disponible";

                    CategoriaQr cate = new CategoriaQr(db);
                    cate.setId(l.getAlibrCategoria().toString());
                    desc.setCategoria(cate.getCategoria().getCateDescrip());
                    desc.setEstatus(estatus);
                    listLibros.add(desc);
                }
                resp.setLibroDescrip(listLibros);
                resp.setMensaje(li.getError());
                at = new AuthQr(db);
                at.borAuth(usuario);
                db.desconectar();
            } catch (IOException e) {
                resp.setMensaje(e.getMessage());
            } catch (SQLException e) {
                resp.setMensaje(e.getMessage());
            }
        } else {
            at.borAuth(usuario);
            resp.setMensaje("Su sesion ha terminado...");
        }

        return resp;
    }
}
