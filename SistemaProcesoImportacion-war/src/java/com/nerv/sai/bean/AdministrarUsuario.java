package com.nerv.sai.bean;

import com.nerv.sai.modelo.entidad.Usuario;
import com.nerv.sai.modelo.local.UsuarioFacadeLocal;
import com.nerv.sai.utilidad.Mensaje;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 * Clase que maneja la entidad Usuario y la Vista de administración de usuarios.
 * @author santosdx
 */
@ManagedBean(name = "MbAdministrarUsuario")
@SessionScoped
public class AdministrarUsuario {
    @EJB
    private UsuarioFacadeLocal eJBServicioUsuario;
    
    final static Logger LOGGER = Logger.getLogger(Practica.class);
    
    private boolean esNuevoUsuario;
    
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;
    
    public AdministrarUsuario(){
        
    }
    
    @PostConstruct
    public void init(){
        inicializarVariables();
    }

    public void nuevoUsuario(){        
        if(getUsuarioSeleccionado().getNickname() != null){
            if(geteJBServicioUsuario().buscarUsuarioByNickname(getUsuarioSeleccionado().getNickname()) == null){
                geteJBServicioUsuario().create(getUsuarioSeleccionado());
                inicializarVariables();
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
    
    public void actualziarUsuario(){
        if(getUsuarioSeleccionado().getId() != null){
            geteJBServicioUsuario().edit(getUsuarioSeleccionado());
            inicializarVariables();
            //LOGGER.info("actualizo usuario");
            Mensaje.agregarMensajeGrowlInfo("Exito!", "Usuario actualizado.");
        }else{
            //LOGGER.info("seleccionar usuario");
            Mensaje.agregarMensajeGrowlWarn("Atención!", "Debe seleccionar un usuario.");
        }        
    }
    
    private void inicializarVariables(){
        setListaUsuarios(geteJBServicioUsuario().findAll());
        setUsuarioSeleccionado(new Usuario(null, null, null, null));
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
    
    
}
