package com.nerv.sai.modelo.local.administracion;

import com.nerv.sai.modelo.entidad.ModuloPermiso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santosdx
 */
@Local
public interface ModuloPermisoFacadeLocal {

    void create(ModuloPermiso moduloPermiso);

    void edit(ModuloPermiso moduloPermiso);

    void remove(ModuloPermiso moduloPermiso);

    ModuloPermiso find(Object id);

    List<ModuloPermiso> findAll();

    List<ModuloPermiso> findRange(int[] range);

    int count();
    
    public ModuloPermiso buscarAsignacionModuloPermiso(int idModulo, int idPermiso);
    
    public int eliminarModuloPermiso(int idModulo);
}
