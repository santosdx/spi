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
@Table(name = "modulo_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModuloPermiso.findAll", query = "SELECT m FROM ModuloPermiso m"),
    @NamedQuery(name = "ModuloPermiso.findById", query = "SELECT m FROM ModuloPermiso m WHERE m.id = :id"),
    @NamedQuery(name = "ModuloPermiso.findByIdModuloIdPermiso", query = "SELECT u FROM ModuloPermiso u WHERE u.idModulo = :idModulo AND u.idPermiso = :idPermiso"),
    @NamedQuery(name = "ModuloPermiso.deleteAllModuloPermisoByIdModulo", query = "DELETE FROM ModuloPermiso p WHERE p.idModulo = :idModulo"),})
public class ModuloPermiso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String FINE_BYE_IDMODULO_IDPERMISO = "ModuloPermiso.findByIdModuloIdPermiso";
    public static final String DELETE_ALL_MODULO_PERMISOS_BY_IDMODULO = "ModuloPermiso.deleteAllModuloPermisoByIdModulo";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modulo")
    private int idModulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_permiso")
    private int idPermiso;
    
    public ModuloPermiso() {
    }

    public ModuloPermiso(Integer id) {
        this.id = id;
    }

    public ModuloPermiso(Integer idModulo, int idPermiso) {
        this.idModulo = idModulo;
        this.idPermiso = idPermiso;
    }
    
    public Integer getId() {
        return id;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    
    
    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof ModuloPermiso)) {
            return false;
        }
        ModuloPermiso other = (ModuloPermiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nerv.sai.modelo.entidad.ModuloPermiso[ id=" + id + " ]";
    }
    
}
