package com.nerv.demo.utilidad;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que permite administrar los métodos de utilidad para el manejo de mensajes.
 * @author santosdx
 */
public class Mensaje {


    /**
     * Método que permite vizualizar un mensaje; Recibe compo parametro un String 
     * con el cuemrpo del mensaje
     * @param mensaje 
     */
    public static void agregarMensajeGrowl(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Información",  "Mensaje: " + mensaje));
    }    
}
