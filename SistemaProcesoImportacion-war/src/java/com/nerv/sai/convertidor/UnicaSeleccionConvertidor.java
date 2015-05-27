package com.nerv.sai.convertidor;

import com.nerv.sai.componente.lista.UnicaSeleccion;
import com.nerv.sai.dto.ObjListaUnicaSeleccion;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Clase que implementa Converter, para el manejo de los objetos en el componente de UnicaSeleccion.
 * @author santosdx
 */
@FacesConverter(value ="unicaSeleccionConverter")
public class UnicaSeleccionConvertidor implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(value != null && value.trim().length() > 0) {
            try {   
                UnicaSeleccion service = (UnicaSeleccion) uic.getAttributes().get("beanClass");
                
                return service.getById(value);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid list."));
            }
        }
        else {
            return null;
        }        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      if(o != null && !o.equals("")) {
            return String.valueOf(((ObjListaUnicaSeleccion) o).getId());
        }
        else {
            return null;
        }        
    }
    
}
