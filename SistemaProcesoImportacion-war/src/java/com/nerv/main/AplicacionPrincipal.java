package com.nerv.main;

import com.nerv.sai.modelo.entidad.SistemaInfo;
import com.nerv.sai.modelo.local.administracion.SistemaInfoFacadeLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Clase que administra el bean de la ventana principal de la aplicación.
 * @author santosdx
 */
@ManagedBean(name="aplicacionPrincipal")
@ApplicationScoped
public class AplicacionPrincipal {
    @EJB
    private SistemaInfoFacadeLocal eJBServicioSistemaInfo;
    
    private SistemaInfo sistemaInfo;
    
    public AplicacionPrincipal(){
        
    }
    
    @PostConstruct
    public void init(){
        setSistemaInfo(geteJBServicioSistemaInfo().obtenerUltimaVersion());
    }

    // Lista de métodos Set y Get de la clase
    
    public SistemaInfoFacadeLocal geteJBServicioSistemaInfo() {
        return eJBServicioSistemaInfo;
    }

    public void seteJBServicioSistemaInfo(SistemaInfoFacadeLocal eJBServicioSistemaInfo) {
        this.eJBServicioSistemaInfo = eJBServicioSistemaInfo;
    }

    public SistemaInfo getSistemaInfo() {
        return sistemaInfo;
    }

    public void setSistemaInfo(SistemaInfo sistemaInfo) {
        this.sistemaInfo = sistemaInfo;
    }
    
    
}
