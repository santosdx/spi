package com.nerv.sai.modelo.local.administracion;

import com.nerv.sai.modelo.entidad.Permiso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santosdx
 */
@Local
public interface PermisoFacadeLocal {

    void create(Permiso permiso);

    void edit(Permiso permiso);

    void remove(Permiso permiso);

    Permiso find(Object id);

    List<Permiso> findAll();

    List<Permiso> findRange(int[] range);

    int count();
    
    public Permiso buscarPermisoByPermiso(String permiso);
    public List<Permiso> buscarPermisosUsuarioByidPerfil(Integer idPerfil);
}
