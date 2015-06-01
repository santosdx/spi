package com.nerv.sai.administracion;

import com.nerv.sai.modelo.entidad.Modulo;
import com.nerv.sai.modelo.entidad.Permiso;
import com.nerv.sai.modelo.local.administracion.ModuloFacadeLocal;
import com.nerv.sai.utilidad.Mensaje;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 * Clase que maneja la entidad Modulo y la Vista de administración de Modulo.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarModulo")
@ViewScoped
public class AdministrarModulo {
    @EJB
    private ModuloFacadeLocal eJBServicioModulo;
    
    final static Logger LOGGER = Logger.getLogger(AdministrarModulo.class);

    @ManagedProperty("#{MbAdministrarPermiso}")
    private AdministrarPermiso servicioPermiso;
    
    private boolean esNuevoModulo;
    
    private List<Modulo> listaModulos;
    private Modulo moduloSeleccionado;
    
    public AdministrarModulo(){
        
    }
    
    @PostConstruct
    public void init(){
        inicializarVariables();
    }

    /**
     * Método que permite inicializar las variables necesarias para el funcionamiento
     * de los metos de crear y actualiar.
     */        
    private void inicializarVariables(){
        setListaModulos(geteJBServicioModulo().findAll());
        setModuloSeleccionado(new Modulo(null, null));
        setEsNuevoModulo(true);
    }
    
    /**
     * Método que permite crear un nuevo módulo en el sistema.
     */
    public void nuevoModulo(){        
        if(getModuloSeleccionado().getModulo() != null){
            if(geteJBServicioModulo().buscarModuloByModulo(getModuloSeleccionado().getModulo()) == null){               
                                
                Integer idModulo = geteJBServicioModulo().createAndGetKey(getModuloSeleccionado());      
            
                if(getServicioPermiso().getListaPermisosSeleccionados() != null){                
                    getServicioPermiso().adicionarPermisosModulo(idModulo, getServicioPermiso().getListaPermisosSeleccionados());                
                }                              
                
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
    
    /**
     * Método que permite editar un mulo en el sistema
     */
    public void actualziarModulo(){
        if(getModuloSeleccionado().getId() != null){
            if(getServicioPermiso().getListaPermisosSeleccionados() != null){                
                getServicioPermiso().adicionarPermisosModulo(getModuloSeleccionado().getId(), getServicioPermiso().getListaPermisosSeleccionados());                
            }            
            geteJBServicioModulo().edit(getModuloSeleccionado());
            inicializarVariables();
            //LOGGER.info("actualizo Modulo");
            Mensaje.agregarMensajeGrowlInfo("Exito!", "Modulo actualizado.");
        }else{
            //LOGGER.info("seleccionar Modulo");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe seleccionar un Modulo.");
        }        
    }

   /**
     * Método que permite seleccionar en la lista de modulo, el módulo a editar
     * @param modulo
     */
    public void seleccionarPerfil(Modulo modulo){
        setModuloSeleccionado(modulo);
        
        if(getModuloSeleccionado().getPermisos() !=null && !getModuloSeleccionado().getPermisos().isEmpty()){
            getServicioPermiso().setListaPermisosSeleccionados(getModuloSeleccionado().getPermisos());  
        }else{
            getServicioPermiso().setListaPermisosSeleccionados(new ArrayList<Permiso>());
        }
                
        LOGGER.info("Módulo seleccionado: "+getModuloSeleccionado().getId());
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

    public AdministrarPermiso getServicioPermiso() {
        return servicioPermiso;
    }

    public void setServicioPermiso(AdministrarPermiso servicioPermiso) {
        this.servicioPermiso = servicioPermiso;
    }
    
    
    
}
