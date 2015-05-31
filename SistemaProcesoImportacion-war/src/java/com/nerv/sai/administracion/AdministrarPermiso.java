package com.nerv.sai.administracion;

import com.nerv.sai.modelo.entidad.ModuloPermiso;
import com.nerv.sai.modelo.entidad.PerfilPermiso;
import com.nerv.sai.modelo.entidad.Permiso;
import com.nerv.sai.modelo.local.administracion.ModuloPermisoFacadeLocal;
import com.nerv.sai.modelo.local.administracion.PerfilPermisoFacadeLocal;
import com.nerv.sai.modelo.local.administracion.PermisoFacadeLocal;
import com.nerv.sai.utilidad.Mensaje;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 * Clase que maneja la entidad Permiso y la Vista de administración de Permiso.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarPermiso")
@ViewScoped
public class AdministrarPermiso {
    @EJB
    private PermisoFacadeLocal eJBServicioPermiso;
    
    @EJB
    private PerfilPermisoFacadeLocal eJBServicioPerfilPermiso;
    
    @EJB
    private ModuloPermisoFacadeLocal eJBServicioModuloPermiso;
    
    final static Logger LOGGER = Logger.getLogger(AdministrarPermiso.class);
    
    private boolean esNuevoPermiso;
    
    private List<Permiso> listaPermisos;
    private Permiso permisoSeleccionado;
    private List<Permiso> listaPermisosSeleccionados;
    
    public AdministrarPermiso(){
        
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
        setListaPermisos(geteJBServicioPermiso().findAll());
        setPermisoSeleccionado(new Permiso(null, null));
        setListaPermisosSeleccionados(new ArrayList<Permiso>());
        setEsNuevoPermiso(true);
    }
    
    /**
     * Metodo que permite crear un nuevo permiso.
     */
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
    
    /**
     * Metodo que permite actualizar los datos de un permiso.
     */
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
    
    /**
     * Método que permite asignar los permisos a un rol.
     * @param idPerfil
     * @param lista 
     */    
    public void adicionarPermisosPerfil(Integer idPerfil, List<Permiso> lista){
        geteJBServicioPerfilPermiso().eliminarPermisosPerfil(idPerfil);
        
        if(lista.size()>0){
            for (Permiso item : lista) {                
                geteJBServicioPerfilPermiso().create(new PerfilPermiso(item.getId(), idPerfil));                
            }
        }
    }

    /**
     * Método que permite asignar los permisos a un modulo.
     * @param idModulo
     * @param lista 
     */    
    public void adicionarPermisosModulo(Integer idModulo, List<Permiso> lista){
        geteJBServicioModuloPermiso().eliminarModuloPermiso(idModulo);
        
        if(lista.size()>0){
            for (Permiso item : lista) {                
                geteJBServicioModuloPermiso().create(new ModuloPermiso(idModulo,item.getId()));               
            }
        }
    }    
    
    /**
     * Método que retorna un permiso, del listado de permisos de a cuerdo al id
     * enviado como parametro.
     * @param id
     * @return 
     */    
    public Permiso getPermisoById(Integer id){
        Permiso resultado = null;
        for (Permiso item : getListaPermisos()) {
            if(Integer.compare(item.getId(), id)==0){
                resultado = item;
            }
        }
        return resultado;
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

    public List<Permiso> getListaPermisosSeleccionados() {
        return listaPermisosSeleccionados;
    }

    public void setListaPermisosSeleccionados(List<Permiso> listaPermisosSeleccionados) {
        this.listaPermisosSeleccionados = listaPermisosSeleccionados;
    }

    public PerfilPermisoFacadeLocal geteJBServicioPerfilPermiso() {
        return eJBServicioPerfilPermiso;
    }

    public void seteJBServicioPerfilPermiso(PerfilPermisoFacadeLocal eJBServicioPerfilPermiso) {
        this.eJBServicioPerfilPermiso = eJBServicioPerfilPermiso;
    }

    public ModuloPermisoFacadeLocal geteJBServicioModuloPermiso() {
        return eJBServicioModuloPermiso;
    }

    public void seteJBServicioModuloPermiso(ModuloPermisoFacadeLocal eJBServicioModuloPermiso) {
        this.eJBServicioModuloPermiso = eJBServicioModuloPermiso;
    }

    
    
    
}
