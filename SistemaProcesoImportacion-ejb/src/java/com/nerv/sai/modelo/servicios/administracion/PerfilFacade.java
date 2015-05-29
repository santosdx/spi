package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.local.administracion.PerfilFacadeLocal;
import com.nerv.sai.modelo.entidad.Perfil;
import com.nerv.sai.modelo.fachada.AbstractFacade;
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
@Stateless(name = "EJBServicioPerfil")
public class PerfilFacade extends AbstractFacade<Perfil> implements PerfilFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilFacade() {
        super(Perfil.class);
    }
    
    @Override
    public Perfil buscarPerfilByPerfil(String perfil) {        
        Perfil resultado = null;
        try {
            Query query = em.createNamedQuery(Perfil.FINE_BYE_PERFIL);
            query.setParameter("perfil", perfil);

            List<Perfil> listaResultado = Collections.EMPTY_LIST;
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
