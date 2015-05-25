package com.nerv.demo.bean;

import com.nerv.demo.componente.lista.UnicaSeleccion;
import com.nerv.demo.dto.ObjListaUnicaSeleccion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    private Map<String, Object> listaComponentes = new HashMap<String, Object>();
    private UnicaSeleccion cboFrutas;
    private UnicaSeleccion cboVerduras;
    private UnicaSeleccion cboCarnes;
    
    public Practica(){
        
    }
    
    @PostConstruct
    public void init(){  
        
        setCboFrutas(new UnicaSeleccion());        
        List<ObjListaUnicaSeleccion> listaFrutas = new ArrayList<>();
        listaFrutas.add(new ObjListaUnicaSeleccion("1", "Uva"));
        listaFrutas.add(new ObjListaUnicaSeleccion("2", "Mango"));
        listaFrutas.add(new ObjListaUnicaSeleccion("3", "Pera"));
        listaFrutas.add(new ObjListaUnicaSeleccion("4", "Sandia"));
        listaFrutas.add(new ObjListaUnicaSeleccion("5", "Piña"));        
        getCboFrutas().setLista(listaFrutas);        
        
        setCboVerduras(new UnicaSeleccion());
        List<ObjListaUnicaSeleccion> listaVerduras = new ArrayList<>();
        listaVerduras.add(new ObjListaUnicaSeleccion("1", "Cebolla"));
        listaVerduras.add(new ObjListaUnicaSeleccion("2", "Pimenton"));
        listaVerduras.add(new ObjListaUnicaSeleccion("3", "Zanahoria"));        
        getCboVerduras().setLista(listaVerduras);  
        
        setCboCarnes(new UnicaSeleccion());
        List<ObjListaUnicaSeleccion> listaCarnes = new ArrayList<>();
        listaCarnes.add(new ObjListaUnicaSeleccion("1", "Carne de Res"));
        listaCarnes.add(new ObjListaUnicaSeleccion("2", "Carne de Cerdo"));
        listaCarnes.add(new ObjListaUnicaSeleccion("3", "Pescado"));
        listaCarnes.add(new ObjListaUnicaSeleccion("4", "Pollo"));        
        getCboCarnes().setLista(listaCarnes);          
        
        getListaComponentes().put("fruta", getCboFrutas());
        getListaComponentes().put("verdura", getCboVerduras());
        getListaComponentes().put("carne", getCboCarnes());
        
    }
    
    public void consultarDato(){
        
        for (Map.Entry<String, Object> entrySet : getListaComponentes().entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();
            
          if(value instanceof UnicaSeleccion) {
                if(((UnicaSeleccion) value).getObjSeleccion() != null){
                  LOGGER.info("Selección,["+key+"]:("+((UnicaSeleccion) value).getObjSeleccion().getId()+")"+((UnicaSeleccion) value).getObjSeleccion().getLabel());                     
                }   
            }           
        }
        
        /*
        if(getCboPractica().getObjSeleccion() != null){
            LOGGER.info("Selección,["+getCboPractica().getObjSeleccion().getId()+"] "+getCboPractica().getObjSeleccion().getLabel());
        }else{
            LOGGER.info("Debe seleccionar un item.");
        }  */      
    }

    public UnicaSeleccion getCboFrutas() {
        return cboFrutas;
    }

    public void setCboFrutas(UnicaSeleccion cboFrutas) {
        this.cboFrutas = cboFrutas;
    }

    public UnicaSeleccion getCboVerduras() {
        return cboVerduras;
    }

    public void setCboVerduras(UnicaSeleccion cboVerduras) {
        this.cboVerduras = cboVerduras;
    }

    public UnicaSeleccion getCboCarnes() {
        return cboCarnes;
    }

    public void setCboCarnes(UnicaSeleccion cboCarnes) {
        this.cboCarnes = cboCarnes;
    }

    public Map<String, Object> getListaComponentes() {
        return listaComponentes;
    }

    public void setListaComponentes(Map<String, Object> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

    
}
