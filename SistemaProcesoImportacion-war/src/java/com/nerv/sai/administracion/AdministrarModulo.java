package com.nerv.sai.administracion;

import com.nerv.sai.modelo.entidad.Modulo;
import com.nerv.sai.modelo.local.administracion.ModuloFacadeLocal;
import com.nerv.sai.utilidad.Mensaje;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 * Clase que maneja la entidad Modulo y la Vista de administración de Modulo.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarModulo")
@SessionScoped
public class AdministrarModulo {
    @EJB
    private ModuloFacadeLocal eJBServicioModulo;
    
    final static Logger LOGGER = Logger.getLogger(AdministrarModulo.class);
    
    private boolean esNuevoModulo;
    
    private List<Modulo> listaModulos;
    private Modulo moduloSeleccionado;
    
    public AdministrarModulo(){
        
    }
    
    @PostConstruct
    public void init(){
        inicializarVariables();
    }

    public void nuevoModulo(){        
        if(getModuloSeleccionado().getModulo() != null){
            if(geteJBServicioModulo().buscarModuloByModulo(getModuloSeleccionado().getModulo()) == null){               
                geteJBServicioModulo().create(getModuloSeleccionado());
                inicializarVariables();
                //LOGGER.info("nuevo Modulo");
                Mensaje.agregarMensajeGrowlInfo("Exito!", "Nuevo Modulo agregado.");
            }else{
                //LOGGER.info("el Modulo ya existe");
                Mensaje.agregarMensajeGrowlWarn("Atención!", "El Modulo ya existe.");
            }
        }else{
            //LOGGER.info("ingresar datos Modulo");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe ingresar los datos.");
        }        
    }    
    
    public void actualziarModulo(){
        if(getModuloSeleccionado().getId() != null){            
            geteJBServicioModulo().edit(getModuloSeleccionado());
            inicializarVariables();
            //LOGGER.info("actualizo Modulo");
            Mensaje.agregarMensajeGrowlInfo("Exito!", "Modulo actualizado.");
        }else{
            //LOGGER.info("seleccionar Modulo");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe seleccionar un Modulo.");
        }        
    }
    
    private void inicializarVariables(){
        setListaModulos(geteJBServicioModulo().findAll());
        setModuloSeleccionado(new Modulo(null, null));
        setEsNuevoModulo(true);
    }
    
    // Métodos Set y Get para los atributos de la clase
    
    public ModuloFacadeLocal geteJBServicioModulo() {
        return eJBServicioModulo;
    }

    public void seteJBServicioModulo(ModuloFacadeLocal eJBServicioModulo) {
        this.eJBServicioModulo = eJBServicioModulo;
    }

    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public Modulo getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(Modulo moduloSeleccionado) {
        setEsNuevoModulo(false);
        this.moduloSeleccionado = moduloSeleccionado;
    }

    public boolean getEsNuevoModulo() {
        return esNuevoModulo;
    }

    public void setEsNuevoModulo(boolean esNuevoModulo) {
        this.esNuevoModulo = esNuevoModulo;
    }
    
    
}
