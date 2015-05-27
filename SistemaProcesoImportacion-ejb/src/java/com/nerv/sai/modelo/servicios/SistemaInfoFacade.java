package com.nerv.sai.modelo.servicios;

import com.nerv.sai.modelo.entidad.SistemaInfo;
import com.nerv.sai.modelo.fachada.AbstractFacade;
import com.nerv.sai.modelo.local.SistemaInfoFacadeLocal;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.hibernate.Hibernate;

/**
 *
 * @author santosdx
 */
@Stateless(name = "EJBServicioSistemaInfo")
public class SistemaInfoFacade extends AbstractFacade<SistemaInfo> implements SistemaInfoFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SistemaInfoFacade() {
        super(SistemaInfo.class);
    }

    public SistemaInfo obtenerUltimaVersion() {
        
        SistemaInfo objSistema = null;

        try {
            Query query = em.createNamedQuery(SistemaInfo.FIND_BY_FECHA_VIGENCIA_NULL);
            //query.setParameter("fechaVigencia", TemporalType.DATE);

            List<SistemaInfo> listaDatos = Collections.EMPTY_LIST;
            listaDatos = query.getResultList();
            if (listaDatos.isEmpty()) {
                return null;
            } else {
                objSistema = listaDatos.get(0);
            }
            System.out.println("Versión: "+ objSistema.getVersion());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        return objSistema;
    }
    
}
