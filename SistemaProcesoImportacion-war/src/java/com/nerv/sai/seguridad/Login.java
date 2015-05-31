package com.nerv.sai.seguridad;

import com.nerv.sai.modelo.entidad.Modulo;
import com.nerv.sai.modelo.entidad.ModuloPermiso;
import com.nerv.sai.modelo.entidad.Usuario;
import com.nerv.sai.modelo.local.administracion.ModuloFacadeLocal;
import com.nerv.sai.modelo.local.administracion.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 * Clase de acceso.
 * @author Santiago
 */
@SessionScoped
public class Login implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @EJB
    private UsuarioFacadeLocal eJBServicioUsuario;
    
    @EJB
    private ModuloFacadeLocal eJBServicioModulo;
    
    private Usuario usuarioLogueado;
    private String username;
    private String password;
    private boolean loggedIn;
    private List<Modulo> listaModulos;
    
    public Login(){
    
    }

    /**
     * Metodo que permite iniciar una sesion para el usuario en la aplicacion.
     * @param event
     */
    public void login(ActionEvent event) {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        //System.out.println("ctxPath:"+ctxPath);

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;

        setUsuarioLogueado(geteJBServicioUsuario().buscarUsuarioByNicknamePassword(username, password));
        
        if(usuarioLogueado != null) {
            setLoggedIn(true);
            setListaModulos(geteJBServicioModulo().getModulesPerfilByIdPerfil(getUsuarioLogueado().getPerfil().getId()));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuarioLogueado.getNombres());
        } else {
            setLoggedIn(false);
            context.addCallbackParam("loggedIn", isLoggedIn());
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de acceso", "Credenciales invalidas");
        }
        
        context.addCallbackParam("loggedIn", isLoggedIn());
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        if (isLoggedIn()){
            context.addCallbackParam("view", ctxPath+"/index.xhtml");
        }
    }

    /**
     * Metodo que permite cerrar la sesion del usuario.
     */
    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        setLoggedIn(false);
        FacesContext contex = FacesContext.getCurrentInstance();
        try {
            contex.getExternalContext().redirect("login.xhtml" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UsuarioFacadeLocal geteJBServicioUsuario() {
        return eJBServicioUsuario;
    }

    public void seteJBServicioUsuario(UsuarioFacadeLocal eJBServicioUsuario) {
        this.eJBServicioUsuario = eJBServicioUsuario;
    }   

    public ModuloFacadeLocal geteJBServicioModulo() {
        return eJBServicioModulo;
    }

    public void seteJBServicioModulo(ModuloFacadeLocal eJBServicioModulo) {
        this.eJBServicioModulo = eJBServicioModulo;
    }

        
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
 
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    
    
    
}
