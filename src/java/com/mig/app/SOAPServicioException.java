/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.app;

/**
 *
 * @author miguel
 */
import javax.xml.ws.WebFault;
import javax.xml.soap.SOAPException;

@WebFault(name="SOAPServicioException")
public class SOAPServicioException extends SOAPException {
    
    public SOAPServicioException(String mensaje) {
        super(mensaje);
    }
    
    public SOAPServicioException(String mensaje, Throwable causa) {
        super(mensaje,causa);
    }
    
}
