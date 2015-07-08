/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.persistence.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findById", query = "SELECT p FROM Paciente p WHERE p.id = :id"),
    @NamedQuery(name = "Paciente.findByIdentificacion", query = "SELECT p FROM Paciente p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Paciente.findByPrimernombre", query = "SELECT p FROM Paciente p WHERE p.primerNombre = :primernombre"),
    @NamedQuery(name = "Paciente.findBySegundonombre", query = "SELECT p FROM Paciente p WHERE p.segundoNombre = :segundonombre"),
    @NamedQuery(name = "Paciente.findByPrimerapellido", query = "SELECT p FROM Paciente p WHERE p.primerApellido = :primerapellido"),
    @NamedQuery(name = "Paciente.findBySegundoapellido", query = "SELECT p FROM Paciente p WHERE p.segundoApellido = :segundoapellido")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SeqPaciente", sequenceName = "SEQ_PACIENTE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqPaciente")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "IDENTIFICACION", unique = true)
    private Long identificacion;
    @Size(max = 250)
    @Column(name = "PRIMER_NOMBRE", length = 250)
    private String primerNombre;
    @Size(max = 250)
    @Column(name = "SEGUNDO_NOMBRE", length = 250)
    private String segundoNombre;
    @Size(max = 250)
    @Column(name = "PRIMER_APELLIDO", length = 250)
    private String primerApellido;
    @Size(max = 250)
    @Column(name = "SEGUNDO_APELLIDO", length = 250)
    private String segundoApellido;
    @Size(max = 250)
    @Column(name = "PASSWORD", length = 250)
    private String password;
    @Size(max = 250)
    @Column(name = "CORREO_ELECTRONICO", length = 250)
    private String correoElectronico;
    @Size(max = 250)
    @Column(name = "USUARIO", length = 250)
    private String usuario;
    @Size(max = 250)
    @Column(name = "ROL", length = 250)
    private String rol;
    @OneToMany(mappedBy = "paciente")
    private List<Episodio> episodios;

    public Paciente() {
        super();
    }

    public Paciente(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(final Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(final String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(final String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(final String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(final String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @XmlTransient
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(final List<Episodio> episodios) {
        this.episodios = episodios;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecos.controller.persistence.entities.Pacientes[ id=" + id + " ]";
    }
}
