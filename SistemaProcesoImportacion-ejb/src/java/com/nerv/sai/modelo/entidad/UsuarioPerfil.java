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
@Table(name = "usuario_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioPerfil.findAll", query = "SELECT u FROM UsuarioPerfil u"),
    @NamedQuery(name = "UsuarioPerfil.findById", query = "SELECT u FROM UsuarioPerfil u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioPerfil.findByIdUsuario", query = "SELECT u FROM UsuarioPerfil u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioPerfil.findByIdPerfil", query = "SELECT u FROM UsuarioPerfil u WHERE u.idPerfil = :idPerfil"),
    @NamedQuery(name = "UsuarioPerfil.findByIdUsuarioIdPerfil", query = "SELECT u FROM UsuarioPerfil u WHERE u.idUsuario = :idUsuario AND u.idPerfil = :idPerfil")})
public class UsuarioPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String FINE_BYE_IDUSUARIO_IDPERFIL = "Modulo.findByIdUsuarioIdPerfil";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private int idPerfil;

    public UsuarioPerfil() {
    }

    public UsuarioPerfil(Integer id) {
        this.id = id;
    }

    public UsuarioPerfil(Integer id, int idUsuario, int idPerfil) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof UsuarioPerfil)) {
            return false;
        }
        UsuarioPerfil other = (UsuarioPerfil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nerv.sai.modelo.entidad.UsuarioPerfil[ id=" + id + " ]";
    }
    
}
