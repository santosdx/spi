package com.nerv.sai.modelo.servicios.administracion;

import com.nerv.sai.modelo.local.administracion.UsuarioPerfilFacadeLocal;
import com.nerv.sai.modelo.entidad.UsuarioPerfil;
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
public class UsuarioPerfilFacade extends AbstractFacade<UsuarioPerfil> implements UsuarioPerfilFacadeLocal {
    @PersistenceContext(unitName = "SistemaProcesoImportacion-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioPerfilFacade() {
        super(UsuarioPerfil.class);
    }

    /**
     * Método que permite buscar una asiganación de perfil a usuario, retornando
     * el objeto de la relación, o en caso de no encontrar nada un null
     * @param idUsuario
     * @param idPerfil
     * @return 
     */
    @Override
    public UsuarioPerfil buscarAsignacionUsuarioPerfil(int idUsuario, int idPerfil) {        
        UsuarioPerfil resultado = null;
        try {
            Query query = em.createNamedQuery(UsuarioPerfil.FINE_BYE_IDUSUARIO_IDPERFIL);
            query.setParameter("idUsuario", idUsuario);
            query.setParameter("idPerfil", idPerfil);

            List<UsuarioPerfil> listaResultado = Collections.EMPTY_LIST;
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
}
