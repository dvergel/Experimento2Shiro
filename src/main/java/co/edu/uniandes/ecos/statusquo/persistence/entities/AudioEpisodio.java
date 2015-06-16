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
import javax.persistence.Lob;
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
@Table(name = "AUDIO_EPISODIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AudioEpisodio.findAll", query = "SELECT a FROM AudioEpisodio a"),
    @NamedQuery(name = "AudioEpisodio.findById", query = "SELECT a FROM AudioEpisodio a WHERE a.id = :id"),
    @NamedQuery(name = "AudioEpisodio.findByCampo", query = "SELECT a FROM AudioEpisodio a WHERE a.campo = :campo")})
public class AudioEpisodio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SeqAudio", sequenceName = "SEQ_AUDIO_EPISODIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqAudio")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;
    @Size(max = 30)
    @Column(name = "CAMPO", length = 30)
    private String campo;
    @Lob
    @Column(name = "AUDIO")
    private byte[] audio;
    @JoinColumn(name = "EPISODIO", referencedColumnName = "ID")
    @ManyToOne
    private Episodio episodio;

    public AudioEpisodio() {
        super();
    }

    public AudioEpisodio(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(final String campo) {
        this.campo = campo;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(final byte[] audio) {
        this.audio = audio;
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
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AudioEpisodio)) {
            return false;
        }
        final AudioEpisodio other = (AudioEpisodio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecos.controller.persistence.entities.Audioepisodio[ id=" + id + " ]";
    }
}
