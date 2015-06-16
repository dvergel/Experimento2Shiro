/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.exceptions;

/**
 * ExceptionApp
 * 
 * excepcion personalizada para administrar los mensajes de los error de la aplicacion
 * 
 * @author Dev
 * @version 1.0
 * @since 1.0
 */
public class ExceptionApp extends Exception {
    /**
     *
     * @param msg
     */
    public ExceptionApp(String msg) {
        super(msg);
    }
}
