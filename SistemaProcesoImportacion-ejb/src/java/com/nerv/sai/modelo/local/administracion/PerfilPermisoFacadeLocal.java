/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nerv.sai.modelo.local.administracion;

import com.nerv.sai.modelo.entidad.PerfilPermiso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santosdx
 */
@Local
public interface PerfilPermisoFacadeLocal {

    void create(PerfilPermiso perfilPermiso);

    void edit(PerfilPermiso perfilPermiso);

    void remove(PerfilPermiso perfilPermiso);

    PerfilPermiso find(Object id);

    List<PerfilPermiso> findAll();

    List<PerfilPermiso> findRange(int[] range);

    int count();
    
    public PerfilPermiso buscarAsignacionPermisoPerfil(int idPermiso, int idPerfil);
    
    public int eliminarPermisosPerfil(int idPerfil);
}
