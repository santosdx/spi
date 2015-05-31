/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nerv.sai.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santosdx
 */
@Entity
@Table(name = "perfil_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilPermiso.findAll", query = "SELECT p FROM PerfilPermiso p"),
    @NamedQuery(name = "PerfilPermiso.findById", query = "SELECT p FROM PerfilPermiso p WHERE p.id = :id"),
    @NamedQuery(name = "PerfilPermiso.findByIdPermisoIdPerfil", query = "SELECT u FROM PerfilPermiso u WHERE u.idPermiso = :idPermiso AND u.idPerfil = :idPerfil"),
    @NamedQuery(name = "PerfilPermiso.deleteAllPermisoPerfilByIdPerfil", query = "DELETE FROM PerfilPermiso p WHERE p.idPerfil = :idPerfil"),})
public class PerfilPermiso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final String FINE_BYE_IDPERMISO_IDPERFIL = "PerfilPermiso.findByIdPermisoIdPerfil";
    public static final String DELETE_ALL_PERMISOS_PERFIL_BY_IDPERFIL = "PerfilPermiso.deleteAllPermisoPerfilByIdPerfil";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_permiso")
    private int idPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private int idPerfil;

    public PerfilPermiso() {
    }

    public PerfilPermiso(Integer idPermiso, int idPerfil) {
        this.idPermiso=idPermiso;
        this.idPerfil=idPerfil;
    }
    
    public PerfilPermiso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilPermiso)) {
            return false;
        }
        PerfilPermiso other = (PerfilPermiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nerv.sai.modelo.entidad.PerfilPermiso[ id=" + id + " ]";
    }
    
}
