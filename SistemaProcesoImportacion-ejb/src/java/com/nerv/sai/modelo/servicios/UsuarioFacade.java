package com.nerv.sai.modelo.servicios;

import com.nerv.sai.modelo.fachada.AbstractFacade;
import com.nerv.sai.modelo.local.UsuarioFacadeLocal;
import com.nerv.sai.modelo.entidad.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author santosdx
 */
@Stateless(name = "EJBServicioUsuario")
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
}
