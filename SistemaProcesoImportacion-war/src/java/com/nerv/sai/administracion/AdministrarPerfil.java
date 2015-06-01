package com.nerv.sai.administracion;

import com.nerv.sai.modelo.entidad.Perfil;
import com.nerv.sai.modelo.entidad.Permiso;
import com.nerv.sai.modelo.local.administracion.PerfilFacadeLocal;
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
 * Clase que maneja la entidad Perfil y la Vista de administración de Perfil.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarPerfil")
@ViewScoped
public class AdministrarPerfil {
    @EJB
    private PerfilFacadeLocal eJBServicioPerfil;
    
    final static Logger LOGGER = Logger.getLogger(AdministrarPerfil.class);
    
    @ManagedProperty("#{MbAdministrarPermiso}")
    private AdministrarPermiso servicioPermiso;
    
    private boolean esNuevoPerfil;
    
    private List<Perfil> listaPerfiles;
    private Perfil perfilSeleccionado;
    private List<Perfil> listaPerfilesSeleccionados;
    
    public AdministrarPerfil(){
        
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
        setListaPerfiles(geteJBServicioPerfil().findAll());
        setPerfilSeleccionado(new Perfil(null, null));
        setEsNuevoPerfil(true);
    }
    
    /**
     * Método que permite crear un nuevo perfil.
     */
    public void nuevoPerfil(){        
        if(getPerfilSeleccionado().getPerfil() != null){
            if(geteJBServicioPerfil().buscarPerfilByPerfil(getPerfilSeleccionado().getPerfil()) == null){
                                             
                Integer idPerfil = geteJBServicioPerfil().createAndGetKey(getPerfilSeleccionado());      
            
                if(getServicioPermiso().getListaPermisosSeleccionados() != null){                
                    getServicioPermiso().adicionarPermisosPerfil(idPerfil, getServicioPermiso().getListaPermisosSeleccionados());                
                }
                
                inicializarVariables();
                //LOGGER.info("nuevo usuario");
                Mensaje.agregarMensajeGrowlInfo("Exito!", "Nuevo perfil agregado.");
            }else{
                //LOGGER.info("el usuario ya existe");
                Mensaje.agregarMensajeGrowlWarn("Atención!", "El perfil ya existe.");
            }
        }else{
            //LOGGER.info("ingresar datos usuario");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe ingresar los datos.");
        }        
    }    
    
    /**
     * Método que permite actualizar los datos de un perfil.
     */
    public void actualziarPerfil(){
        if(getPerfilSeleccionado().getId() != null){                    
    
            if(getServicioPermiso().getListaPermisosSeleccionados() != null){                
                getServicioPermiso().adicionarPermisosPerfil(getPerfilSeleccionado().getId(), getServicioPermiso().getListaPermisosSeleccionados());                
            }
            geteJBServicioPerfil().edit(getPerfilSeleccionado());
            
            inicializarVariables();
            //LOGGER.info("actualizo Perfil");
            Mensaje.agregarMensajeGrowlInfo("Exito!", "Perfil actualizado.");
        }else{
            //LOGGER.info("seleccionar Perfil");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe seleccionar un perfil.");
        }        
    }
    
    /**
     * Método que permite seleccionar en la lista de permisos, los permisos que tiene
     * el perfil
     * @param perfil
     */
    public void seleccionarPerfil(Perfil perfil){
        setPerfilSeleccionado(perfil);
        
        if(getPerfilSeleccionado().getPermisos() !=null && !getPerfilSeleccionado().getPermisos().isEmpty()){
            getServicioPermiso().setListaPermisosSeleccionados(getPerfilSeleccionado().getPermisos());  
        }else{
            getServicioPermiso().setListaPermisosSeleccionados(new ArrayList<Permiso>());
        }
                
        LOGGER.info("Perfil seleccionado: "+getPerfilSeleccionado().getId());
    }    

    /**
     * Método que retorna un perfil, del listado de perfiles de a cuerdo al id
     * enviado como parametro.
     * @param id
     * @return 
     */    
    public Perfil getPerfilById(Integer id){
        Perfil resultado = null;
        for (Perfil item : getListaPerfiles()) {
            if(Integer.compare(item.getId(), id)==0){
                resultado = item;
            }
        }
        return resultado;
    }
    
    // Métodos Set y Get para los atributos de la clase
    
    public PerfilFacadeLocal geteJBServicioPerfil() {
        return eJBServicioPerfil;
    }

    public void seteJBServicioPerfil(PerfilFacadeLocal eJBServicioPerfil) {
        this.eJBServicioPerfil = eJBServicioPerfil;
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        setEsNuevoPerfil(false);
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public boolean getEsNuevoPerfil() {
        return esNuevoPerfil;
    }

    public void setEsNuevoPerfil(boolean esNuevoPerfil) {
        this.esNuevoPerfil = esNuevoPerfil;
    }

    public List<Perfil> getListaPerfilesSeleccionados() {
        return listaPerfilesSeleccionados;
    }

    public void setListaPerfilesSeleccionados(List<Perfil> listaPerfilesSeleccionados) {
        this.listaPerfilesSeleccionados = listaPerfilesSeleccionados;
    }

    public AdministrarPermiso getServicioPermiso() {
        return servicioPermiso;
    }

    public void setServicioPermiso(AdministrarPermiso servicioPermiso) {
        this.servicioPermiso = servicioPermiso;
    }
    
}
