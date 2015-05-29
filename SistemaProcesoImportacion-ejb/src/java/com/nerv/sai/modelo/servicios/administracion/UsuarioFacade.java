package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.fachada.AbstractFacade;
import com.nerv.sai.modelo.local.administracion.UsuarioFacadeLocal;
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
        Usuario resultado = null;
        try {
            Query query = em.createNamedQuery(Usuario.FINE_BYE_NICKNAME);
            query.setParameter("nickname", nickname);

            List<Usuario> listaResultado = Collections.EMPTY_LIST;
            listaResultado = query.getResultList();
            if (listaResultado.isEmpty()) {
                return null;
            } else {
                resultado = listaResultado.get(0);
            }            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        return resultado;
    }
}