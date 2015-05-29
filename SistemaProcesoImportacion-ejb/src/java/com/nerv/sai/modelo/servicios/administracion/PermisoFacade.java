package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.local.administracion.PermisoFacadeLocal;
import com.nerv.sai.modelo.entidad.Permiso;
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
@Stateless(name = "EJBServicioPermiso")
public class PermisoFacade extends AbstractFacade<Permiso> implements PermisoFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoFacade() {
        super(Permiso.class);
    }

    @Override
    public Permiso buscarPermisoByPermiso(String permiso) {        
        Permiso resultado = null;
        try {
            Query query = em.createNamedQuery(Permiso.FINE_BYE_PERMISO);
            query.setParameter("permiso", permiso);

            List<Permiso> listaResultado = Collections.EMPTY_LIST;
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
