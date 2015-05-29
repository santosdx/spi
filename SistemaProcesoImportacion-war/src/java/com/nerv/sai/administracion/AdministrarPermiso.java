package com.nerv.sai.administracion;

import com.nerv.sai.modelo.entidad.Permiso;
import com.nerv.sai.modelo.local.administracion.PermisoFacadeLocal;
import com.nerv.sai.utilidad.Mensaje;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 * Clase que maneja la entidad Permiso y la Vista de administración de Permiso.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarPermiso")
@SessionScoped
public class AdministrarPermiso {
    @EJB
    private PermisoFacadeLocal eJBServicioPermiso;
    
    final static Logger LOGGER = Logger.getLogger(AdministrarPermiso.class);
    
    private boolean esNuevoPermiso;
    
    private List<Permiso> listaPermisos;
    private Permiso permisoSeleccionado;
    
    public AdministrarPermiso(){
        
    }
    
    @PostConstruct
    public void init(){
        inicializarVariables();
    }

    public void nuevoPermiso(){        
        if(getPermisoSeleccionado().getPermiso() != null){
            if(geteJBServicioPermiso().buscarPermisoByPermiso(getPermisoSeleccionado().getPermiso()) == null){                
                geteJBServicioPermiso().create(getPermisoSeleccionado());
                inicializarVariables();
                //LOGGER.info("nuevo Permiso");
                Mensaje.agregarMensajeGrowlInfo("Exito!", "Nuevo permiso agregado.");
            }else{
                //LOGGER.info("el Permiso ya existe");
                Mensaje.agregarMensajeGrowlWarn("Atención!", "El permiso ya existe.");
            }
        }else{
            //LOGGER.info("ingresar datos Permiso");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe ingresar los datos.");
        }        
    }    
    
    public void actualziarPermiso(){
        if(getPermisoSeleccionado().getId() != null){          
            geteJBServicioPermiso().edit(getPermisoSeleccionado());
            inicializarVariables();
            //LOGGER.info("actualizo Permiso");
            Mensaje.agregarMensajeGrowlInfo("Exito!", "Permiso actualizado.");
        }else{
            //LOGGER.info("seleccionar Permiso");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe seleccionar un Permiso.");
        }        
    }
    
    private void inicializarVariables(){
        setListaPermisos(geteJBServicioPermiso().findAll());
        setPermisoSeleccionado(new Permiso(null, null));
        setEsNuevoPermiso(true);
    }
    
    // Métodos Set y Get para los atributos de la clase
    
    public PermisoFacadeLocal geteJBServicioPermiso() {
        return eJBServicioPermiso;
    }

    public void seteJBServicioPermiso(PermisoFacadeLocal eJBServicioPermiso) {
        this.eJBServicioPermiso = eJBServicioPermiso;
    }

    public List<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public Permiso getPermisoSeleccionado() {
        return permisoSeleccionado;
    }

    public void setPermisoSeleccionado(Permiso permisoSeleccionado) {
        setEsNuevoPermiso(false);
        this.permisoSeleccionado = permisoSeleccionado;
    }

    public boolean getEsNuevoPermiso() {
        return esNuevoPermiso;
    }

    public void setEsNuevoPermiso(boolean esNuevoPermiso) {
        this.esNuevoPermiso = esNuevoPermiso;
    }
    
    
}
