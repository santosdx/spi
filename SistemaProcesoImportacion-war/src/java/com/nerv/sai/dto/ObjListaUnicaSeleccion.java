package com.nerv.sai.dto;

/**
 * Clase que representa un objeto para el componente de UnicaSeleccion.
 * @author santosdx
 */
public class ObjListaUnicaSeleccion {
    
    private String id;
    private String label;
    
    public ObjListaUnicaSeleccion(){
        
    }

    public ObjListaUnicaSeleccion(String id, String label){
        this.id=id;
        this.label=label;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
        
}
