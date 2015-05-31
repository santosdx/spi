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

    @Override
    public List<Permiso> buscarPermisosUsuarioByidPerfil(Integer idPerfil) {        
        List<Permiso> resultado = null;
        try {
            String sql= "SELECT DISTINCT pe.* " +
                        "FROM modulo m " +
                        "JOIN modulo_permiso mp ON (m.id = mp.id_modulo) " +
                        "JOIN perfil_permiso pp ON (pp.id_permiso = mp.id_permiso) " +
                        "JOIN permiso pe        ON (pe.id =pp.id_permiso) " +
                        "WHERE pp.id_perfil = ?";
            
            Query query = em.createNativeQuery(sql,Permiso.class);
            query.setParameter(1, idPerfil);

            List<Permiso> listaResultado = Collections.EMPTY_LIST;
            listaResultado = query.getResultList();
            if (listaResultado.isEmpty()) {
                return null;
            } else {
                resultado = listaResultado;
            }            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        return resultado;
    }     
}
