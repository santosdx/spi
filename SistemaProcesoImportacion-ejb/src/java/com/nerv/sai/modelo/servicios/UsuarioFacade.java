package com.nerv.sai.modelo.servicios;

import com.nerv.sai.modelo.fachada.AbstractFacade;
import com.nerv.sai.modelo.local.UsuarioFacadeLocal;
import com.nerv.sai.modelo.entidad.Usuario;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
    
    @Override
    public Usuario buscarUsuarioByNickname(String nickname) {        
        Usuario usuario = null;
        try {
            Query query = em.createNamedQuery(Usuario.FINE_BYE_NICKNAME);
            query.setParameter("nickname", nickname);

            List<Usuario> listaUsuarios = Collections.EMPTY_LIST;
            listaUsuarios = query.getResultList();
            if (listaUsuarios.isEmpty()) {
                return null;
            } else {
                usuario = listaUsuarios.get(0);
            }            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        return usuario;
    }
}
