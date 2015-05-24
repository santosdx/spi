package com.nerv.demo.bean;

import com.nerv.demo.componente.lista.UnicaSeleccion;
import com.nerv.demo.dto.ObjListaUnicaSeleccion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 * Clase de practica para el manejo del componente de lista unica seleccion.
 * @author santosdx
 */
@ManagedBean
@SessionScoped
public class Practica {
    
    final static Logger LOGGER = Logger.getLogger(Practica.class);
    
    private UnicaSeleccion cboPractica;
    
    public Practica(){
        
    }
    
    @PostConstruct
    public void init(){
        setCboPractica(new UnicaSeleccion());
        
        List<ObjListaUnicaSeleccion> lista = new ArrayList<>();
        lista.add(new ObjListaUnicaSeleccion("1", "Uva"));
        lista.add(new ObjListaUnicaSeleccion("2", "Mango"));
        lista.add(new ObjListaUnicaSeleccion("3", "Pera"));
        
        getCboPractica().setLista(lista);        
    }
    
    public void consultarDato(){
        if(getCboPractica().getObjSeleccion() != null){
            LOGGER.info("Selección,["+getCboPractica().getObjSeleccion().getId()+"] "+getCboPractica().getObjSeleccion().getLabel());
        }else{
            LOGGER.info("Debe seleccionar un item.");
        }        
    }

    public UnicaSeleccion getCboPractica() {
        return cboPractica;
    }

    public void setCboPractica(UnicaSeleccion cboPractica) {
        this.cboPractica = cboPractica;
    }
    
    
}
