package com.nerv.sai.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author santosdx
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u order by u.nombres"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNickname", query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByNicknamePassword", query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname AND u.password = :password"),})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String FINE_BYE_NICKNAME = "Usuario.findByNickname";
    public static final String FINE_BYE_NICKNAME_PASSWORD = "Usuario.findByNicknamePassword";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 80)
    @NotNull
    @Column(name = "nickname")
    private String nickname;
    @Size(max = 80)
    @NotNull
    @Column(name = "password")
    private String password;    
    @Size(max = 150)
    @NotNull
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 150)
    @NotNull
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 150)
    @NotNull
    @Column(name = "correo")
    private String correo;
    
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private Perfil perfil;
    //private List<Perfil> perfiles;
    
    
    public Usuario() {
    }

    public Usuario(String nickname, String nombres, String apellidos, String correo) {        
        this.nickname=nickname;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.correo=correo;
    }
    
    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = WordUtils.capitalize(nombres.trim());
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = WordUtils.capitalize(apellidos.trim());
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    

    /*
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
    */
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nerv.sai.modelo.entidad.Usuario[ id=" + id + " ]";
    }
    
}
