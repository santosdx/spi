package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.local.administracion.PerfilPermisoFacadeLocal;
import com.nerv.sai.modelo.entidad.PerfilPermiso;
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
@Stateless
public class PerfilPermisoFacade extends AbstractFacade<PerfilPermiso> implements PerfilPermisoFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilPermisoFacade() {
        super(PerfilPermiso.class);
    }
    
    /**
     * Método que permite buscar una asiganación de permiso a perfil, retornando
     * el objeto de la relación, o en caso de no encontrar nada un null
     * @param idPerfil
     * @param idPermiso
     * @return 
     */
    @Override
    public PerfilPermiso buscarAsignacionPermisoPerfil(int idPermiso, int idPerfil) {        
        PerfilPermiso resultado = null;
        try {
            Query query = em.createNamedQuery(PerfilPermiso.FINE_BYE_IDPERMISO_IDPERFIL);
            query.setParameter("idPermiso", idPermiso);
            query.setParameter("idPerfil", idPerfil);

            List<PerfilPermiso> listaResultado = Collections.EMPTY_LIST;
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

     /**
     * Método que permite eliminar todas las asignaciones de permisos a un perfil
     * pasando como parametro el id de l perfil.
     * @param idPerfil
     * @return 
     */
    @Override
    public int eliminarPermisosPerfil(int idPerfil) {        
        int resultado=0;
        try {
            Query query = em.createNamedQuery(PerfilPermiso.DELETE_ALL_PERMISOS_PERFIL_BY_IDPERFIL);            
            query.setParameter("idPerfil", idPerfil);
            resultado = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return resultado;
    } 
}
