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

    /**
     * Método que permite crear un registro en la entidad perfil y retorna el
     * id o llave con el cual se ingreso a la base de datos ese registro.
     * En caso de error, u otra inconsistencia, retornara -1.
     * @param perfil
     * @return 
     */
    @Override
    public Integer createAndGetKey(Perfil perfil) {        
        Integer resultado = -1;
        try {
            em.persist(perfil);
            em.flush();
            resultado = perfil.getId();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        return resultado;
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
