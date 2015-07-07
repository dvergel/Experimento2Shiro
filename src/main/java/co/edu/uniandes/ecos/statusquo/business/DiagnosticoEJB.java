/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.business;

import co.edu.uniandes.ecos.statusquo.persistence.dao.DiagnosticoDAO;
import co.edu.uniandes.ecos.statusquo.persistence.entities.Diagnostico;
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
public class DiagnosticoEJB {

    @EJB
    DiagnosticoDAO facade;
    
    public List<Diagnostico> consultar() throws Exception {
        return facade.findAll();
    }

    public Diagnostico consultarId(final Long id) throws Exception {
        return facade.find(id);
    }
    
    public List<Diagnostico> consultarTop10() throws Exception {
        return facade.findByNamedQuery("Diagnostico.findByGroupCatalizador", new HashMap<String, Object>(),0,9);
    }
    
    public List<Diagnostico> consultarCatalizador(final String catalizador) throws Exception {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("catalizadoresdolor", catalizador);
        return facade.findByNamedQuery("Diagnostico.findByCatalizadoresdolor", params);
    }

    public void save(final Diagnostico selectedRecord) throws Exception {
        if (selectedRecord.getId() == null) {
            facade.create(selectedRecord);
        } else {
            facade.edit(selectedRecord);
        }
    }

    public void remove(final Diagnostico selectedRecord) throws Exception {
        if (selectedRecord.getId() != null) {
            facade.remove(selectedRecord);
        }
    }
}
