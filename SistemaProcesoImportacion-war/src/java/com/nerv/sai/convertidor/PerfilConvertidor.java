package com.nerv.sai.convertidor;

import com.nerv.sai.administracion.AdministrarPerfil;
import com.nerv.sai.modelo.entidad.Perfil;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;


/**
 * Clase que implementa Converter, para el manejo de los objetos en el componente de UnicaSeleccion.
 * @author santosdx
 */
@FacesConverter(value ="perfilConverter")
public class PerfilConvertidor implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(value != null && value.trim().length() > 0) {
            try {  
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                
                AdministrarPerfil service = (AdministrarPerfil) viewMap.get("MbAdministrarPerfil");
                
                return service.getPerfilById(Integer.parseInt(value));
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
            return String.valueOf(((Perfil) o).getId());
        }
        else {
            return null;
        }        
    }
    
}
