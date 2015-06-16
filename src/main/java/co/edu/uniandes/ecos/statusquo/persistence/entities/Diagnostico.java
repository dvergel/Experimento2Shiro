/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "DIAGNOSTICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnostico.findAll", query = "SELECT d FROM Diagnostico d"),
    @NamedQuery(name = "Diagnostico.findById", query = "SELECT d FROM Diagnostico d WHERE d.id = :id"),
    @NamedQuery(name = "Diagnostico.findByCatalizadoresdolor", query = "SELECT d FROM Diagnostico d WHERE d.catalizadoresDolor = :catalizadoresdolor"),
    @NamedQuery(name = "Diagnostico.findByFormula", query = "SELECT d FROM Diagnostico d WHERE d.formula = :formula")})
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SeqDiagnostico", sequenceName = "SEQ_DIAGNOSTICO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqDiagnostico")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;
    @Size(max = 4000)
    @Column(name = "CATALIZADORES_DOLOR", length = 4000)
    private String catalizadoresDolor;
    @Size(max = 4000)
    @Column(name = "FORMULA", length = 4000)
    private String formula;
    @JoinColumn(name = "EPISODIO", referencedColumnName = "ID")
    @ManyToOne
    private Episodio episodio;

    public Diagnostico() {
        super();
    }

    public Diagnostico(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCatalizadoresDolor() {
        return catalizadoresDolor;
    }

    public void setCatalizadoresDolor(final String catalizadoresDolor) {
        this.catalizadoresDolor = catalizadoresDolor;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(final String formula) {
        this.formula = formula;
    }

    public Episodio getEpisodio() {
        return episodio;
    }

    public void setEpisodio(final Episodio episodio) {
        this.episodio = episodio;
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
        if (!(object instanceof Diagnostico)) {
            return false;
        }
        final Diagnostico other = (Diagnostico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecos.controller.persistence.entities.Diagnostico[ id=" + id + " ]";
    }
}
