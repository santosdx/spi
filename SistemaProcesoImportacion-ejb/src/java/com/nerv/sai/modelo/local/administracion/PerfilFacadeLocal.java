package com.nerv.sai.modelo.local.administracion;

import com.nerv.sai.modelo.entidad.Perfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santosdx
 */
@Local
public interface PerfilFacadeLocal {

    void create(Perfil perfil);
    
    public Integer createAndGetKey(Perfil perfil);

    void edit(Perfil perfil);

    void remove(Perfil perfil);

    Perfil find(Object id);

    List<Perfil> findAll();

    List<Perfil> findRange(int[] range);

    int count();
    
    public Perfil buscarPerfilByPerfil(String perfil);
}
