/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.business;

import co.edu.uniandes.ecos.statusquo.persistence.dao.EpisodioDAO;
import co.edu.uniandes.ecos.statusquo.persistence.entities.Episodio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
@Stateless
@LocalBean
public class EpisodioEJB {

    @EJB
    EpisodioDAO facade;

    public Episodio consultarId(Long id) throws Exception {
        return facade.find(id);
    }

    public List<Episodio> consultarLista(final Long pacienteId) throws Exception {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("paciente", pacienteId);
        return facade.findByNamedQuery("Episodios.findByPaciente", params);
    }

    public void save(final Episodio entidad) throws Exception {
        if (entidad.getId() == null) {
            facade.create(entidad);
        } else {
            facade.edit(entidad);
        }
    }

    public void remove(final Episodio entidad) throws Exception {
        facade.remove(entidad);
    }
}
