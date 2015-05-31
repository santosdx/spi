package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.local.administracion.ModuloFacadeLocal;
import com.nerv.sai.modelo.entidad.Modulo;
import com.nerv.sai.modelo.fachada.AbstractFacade;
import java.util.ArrayList;
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
@Stateless(name = "EJBServicioModulo")
public class ModuloFacade extends AbstractFacade<Modulo> implements ModuloFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloFacade() {
        super(Modulo.class);
    }
    
    @Override
    public Modulo buscarModuloByModulo(String modulo) {        
        Modulo resultado = null;
        try {
            Query query = em.createNamedQuery(Modulo.FINE_BYE_MODULO);
            query.setParameter("modulo", modulo);

            List<Modulo> listaResultado = Collections.EMPTY_LIST;
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
    public List<Modulo> getModulesPerfilByIdPerfil(int idPerfil) {
            List<Modulo> resultado = new ArrayList<Modulo>();
        try {
            //Query query = em.createNamedQuery(Modulo.FINE_MODLE_BYE_IDPERFIL);
                        
            String sql= "SELECT DISTINCT m.* " +
                        "FROM modulo m " +
                        "JOIN modulo_permiso mp ON (m.id = mp.id_modulo) " +
                        "JOIN perfil_permiso pp ON (pp.id_permiso = mp.id_permiso) " +
                        "JOIN permiso pe        ON (pe.id =pp.id_permiso) " +
                        "WHERE pp.id_perfil = ?";
            
            Query query = em.createNativeQuery(sql,Modulo.class);
            query.setParameter(1, idPerfil);
            
            List<Modulo> listaResultado = Collections.EMPTY_LIST;
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
