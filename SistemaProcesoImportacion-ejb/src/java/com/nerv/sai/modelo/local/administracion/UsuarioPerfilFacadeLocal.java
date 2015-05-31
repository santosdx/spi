/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nerv.sai.modelo.local.administracion;

import com.nerv.sai.modelo.entidad.UsuarioPerfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santosdx
 */
@Local
public interface UsuarioPerfilFacadeLocal {

    void create(UsuarioPerfil usuarioPerfil);

    void edit(UsuarioPerfil usuarioPerfil);

    void remove(UsuarioPerfil usuarioPerfil);

    UsuarioPerfil find(Object id);

    List<UsuarioPerfil> findAll();

    List<UsuarioPerfil> findRange(int[] range);

    int count();
    
    public UsuarioPerfil buscarAsignacionUsuarioPerfil(int idUsuario, int idPerfil);
   
}
