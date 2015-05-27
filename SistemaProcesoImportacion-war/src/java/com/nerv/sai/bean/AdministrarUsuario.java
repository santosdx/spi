package com.nerv.sai.bean;

import com.nerv.sai.modelo.entidad.Usuario;
import com.nerv.sai.modelo.local.UsuarioFacadeLocal;
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
        
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;
    
    public AdministrarUsuario(){
        
    }
    
    @PostConstruct
    public void init(){
        Usuario nuevoUsuario = new Usuario(0, null, null, null, null);
        setUsuarioSeleccionado(nuevoUsuario);
        setListaUsuarios(geteJBServicioUsuario().findAll());
    }

    public void nuevoUsuario(){
        Usuario nuevoUsuario = new Usuario(0, null, null, null, null);
        setUsuarioSeleccionado(nuevoUsuario);
        LOGGER.info("nuevo");
    }    
    
    public void guardarUsuario(){
        if(getUsuarioSeleccionado().getId() == 0){
            geteJBServicioUsuario().create(getUsuarioSeleccionado());
            setListaUsuarios(geteJBServicioUsuario().findAll());
            nuevoUsuario();
            LOGGER.info("guardo nuevo usuario");
        }else{
            geteJBServicioUsuario().edit(getUsuarioSeleccionado());
            setListaUsuarios(geteJBServicioUsuario().findAll());
            nuevoUsuario();
            LOGGER.info("actualizo usuario");
        }
        
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
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    
}
