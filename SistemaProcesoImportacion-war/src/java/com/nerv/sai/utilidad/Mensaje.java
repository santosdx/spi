package com.nerv.sai.utilidad;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que permite administrar los métodos de utilidad para el manejo de 
 * mensajes.
 * @author santosdx
 */
public class Mensaje {


    /**
     * Método que permite vizualizar un mensaje de tipo INFO; Recibe compo 
     * parametro un String con el cuemrpo del mensaje y un String con el titulo
     * @param titulo
     * @param mensaje 
     */
    public static void agregarMensajeGrowlInfo(String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
    } 
    
    /**
     * Método que permite vizualizar un mensaje de tipo WARN; Recibe compo 
     * parametro un String con el cuemrpo del mensaje y un String con el titulo
     * @param titulo
     * @param mensaje 
     */
    public static void agregarMensajeGrowlWarn(String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje));
    }
     
    /**
     * Método que permite vizualizar un mensaje de tipo ERROR; Recibe compo 
     * parametro un String con el cuemrpo del mensaje y un String con el titulo
     * @param titulo
     * @param mensaje 
     */
    public static void agregarMensajeGrowlError(String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));
    }
    
     /**
     * Método que permite vizualizar un mensaje de tipo FATAL; Recibe compo 
     * parametro un String con el cuemrpo del mensaje y un String con el titulo
     * @param titulo
     * @param mensaje 
     */
    public static void agregarMensajeGrowlFatal(String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
    }  
}
