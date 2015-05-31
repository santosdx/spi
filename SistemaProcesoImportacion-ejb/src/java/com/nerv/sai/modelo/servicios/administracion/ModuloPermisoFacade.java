package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.entidad.ModuloPermiso;
import com.nerv.sai.modelo.fachada.AbstractFacade;
import com.nerv.sai.modelo.local.administracion.ModuloPermisoFacadeLocal;
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
@Stateless
public class ModuloPermisoFacade extends AbstractFacade<ModuloPermiso> implements ModuloPermisoFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloPermisoFacade() {
        super(ModuloPermiso.class);
    }

    @Override
    public ModuloPermiso buscarAsignacionModuloPermiso(int idModulo, int idPermiso) {
      ModuloPermiso resultado = null;
        try {
            Query query = em.createNamedQuery(ModuloPermiso.FINE_BYE_IDMODULO_IDPERMISO);
            query.setParameter("idModulo", idModulo);
            query.setParameter("idPermiso", idPermiso);

            List<ModuloPermiso> listaResultado = Collections.EMPTY_LIST;
            listaResultado = query.getResultList();
            if (listaResultado.isEmpty()) {
                resultado = null;
            } else {
                resultado = listaResultado.get(0);
            }            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        return resultado;        
    }

    @Override
    public int eliminarModuloPermiso(int idModulo) {
        int resultado=0;
        try {
            Query query = em.createNamedQuery(ModuloPermiso.DELETE_ALL_MODULO_PERMISOS_BY_IDMODULO);            
            query.setParameter("idModulo", idModulo);
            resultado = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return resultado;        
    }
    
}
