package com.nerv.sai.administracion;

import com.nerv.sai.modelo.entidad.Usuario;
import com.nerv.sai.modelo.entidad.UsuarioPerfil;
import com.nerv.sai.modelo.local.administracion.UsuarioFacadeLocal;
import com.nerv.sai.modelo.local.administracion.UsuarioPerfilFacadeLocal;
import com.nerv.sai.utilidad.Mensaje;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 * Clase que maneja la entidad Usuario y la Vista de administración de usuarios.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarUsuario")
@ViewScoped
public class AdministrarUsuario {
    @EJB
    private UsuarioFacadeLocal eJBServicioUsuario;
    
    @EJB
    private UsuarioPerfilFacadeLocal eJBServicioUsuarioPerfil;
    
    final static Logger LOGGER = Logger.getLogger(AdministrarUsuario.class);
    
    @ManagedProperty("#{MbAdministrarPerfil}")
    private AdministrarPerfil servicioPerfil;
    
    private boolean esNuevoUsuario;
    
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;
    
    public AdministrarUsuario(){
        
    }
    
    @PostConstruct
    public void init(){
        inicializarVariables();
    }

    /**
     * Método que permite crear un nuevo usuario.
     */
    public void nuevoUsuario(){
        if(getUsuarioSeleccionado().getNickname() != null){
            if(geteJBServicioUsuario().buscarUsuarioByNickname(getUsuarioSeleccionado().getNickname()) == null){
                
                Integer idUsuario = getUsuarioSeleccionado().getId();
                Integer idPerfil = servicioPerfil.getPerfilSeleccionado().getId();                         
                   
                eJBServicioUsuarioPerfil.create(new UsuarioPerfil(idUsuario, idPerfil));
     
                inicializarVariables();
                
                LOGGER.info("Id perfil:"+idPerfil);
                //LOGGER.info("nuevo usuario");
                Mensaje.agregarMensajeGrowlInfo("Exito!", "Nuevo usuario agregado.");
            }else{
                //LOGGER.info("el usuario ya existe");
                Mensaje.agregarMensajeGrowlWarn("Atención!", "El usuario ya existe.");
            }
        }else{
            //LOGGER.info("ingresar datos usuario");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe ingresar los datos.");
        }        
    }    
    
    /**
     * Método que permite actualizar los datos del usuario.
     */
    public void actualziarUsuario(){
        if(getUsuarioSeleccionado().getId() != null){
            
            geteJBServicioUsuario().edit(getUsuarioSeleccionado());                                   
            
            if(servicioPerfil.getPerfilSeleccionado() != null){
                
                Integer idUsuario = getUsuarioSeleccionado().getId();
                Integer idPerfil = servicioPerfil.getPerfilSeleccionado().getId();      
                
                if(getUsuarioSeleccionado().getPerfil() == null){               
                    eJBServicioUsuarioPerfil.create(new UsuarioPerfil(idUsuario, idPerfil));
                }else{          
                    if(Integer.compare(getUsuarioSeleccionado().getPerfil().getId(), servicioPerfil.getPerfilSeleccionado().getId()) != 0){
                        UsuarioPerfil usuarioPerfil = eJBServicioUsuarioPerfil.buscarAsignacionUsuarioPerfil(idUsuario, getUsuarioSeleccionado().getPerfil().getId());
                        usuarioPerfil.setIdPerfil(servicioPerfil.getPerfilSeleccionado().getId());
                        eJBServicioUsuarioPerfil.edit(usuarioPerfil);
                    }else{
                      Mensaje.agregarMensajeGrowlWarn("Avertencia!", "El perfil seleccionado ya esta asignado.");
                    }           
                }
            }else{
                Mensaje.agregarMensajeGrowlWarn("Avertencia!", "No selecciono un perfil para el usuario.");
            }
            
            inicializarVariables();
            //LOGGER.info("actualizo usuario");
            Mensaje.agregarMensajeGrowlInfo("Exito!", "Usuario actualizado.");
        }else{
            //LOGGER.info("seleccionar usuario");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe seleccionar un usuario.");
        }        
    }
    
    public void seleccionarUsuario(){
        //getUsuarioSeleccionado().getId();
        if(getUsuarioSeleccionado().getPerfil() != null){
            servicioPerfil.setPerfilSeleccionado(servicioPerfil.getPerfilById(getUsuarioSeleccionado().getPerfil().getId()));  
        }else{
            servicioPerfil.setPerfilSeleccionado(null);
        }
        
        //LOGGER.info("Usuario seleccionado: "+getUsuarioSeleccionado().getId());
    }
    
    /**
     * Método que permite inicializar las variables necesarias para el funcionamiento
     * de los metos de crear y actualiar.
     */
    private void inicializarVariables(){
        setListaUsuarios(geteJBServicioUsuario().findAll());
        setUsuarioSeleccionado(new Usuario(null, null, null, null));
        servicioPerfil.setPerfilSeleccionado(null);
        setEsNuevoUsuario(true);
    }
    
    
    
    
    
    
    
    
    
    
    // Métodos Set y Get para los atributos de la clase
    
    public UsuarioFacadeLocal geteJBServicioUsuario() {
        return eJBServicioUsuario;
    }

    public void seteJBServicioUsuario(UsuarioFacadeLocal eJBServicioUsuario) {
        this.eJBServicioUsuario = eJBServicioUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        setEsNuevoUsuario(false);
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public boolean getEsNuevoUsuario() {
        return esNuevoUsuario;
    }

    public void setEsNuevoUsuario(boolean esNuevoUsuario) {
        this.esNuevoUsuario = esNuevoUsuario;
    }

    public AdministrarPerfil getServicioPerfil() {
        return servicioPerfil;
    }

    public void setServicioPerfil(AdministrarPerfil servicioPerfil) {
        this.servicioPerfil = servicioPerfil;
    }
    
    
}
