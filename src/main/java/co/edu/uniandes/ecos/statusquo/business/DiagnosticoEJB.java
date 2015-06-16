/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.business;

import co.edu.uniandes.ecos.statusquo.persistence.dao.DiagnosticoDAO;
import co.edu.uniandes.ecos.statusquo.persistence.entities.Diagnostico;
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

    public Diagnostico consultarId(final Long id) throws Exception {
        return facade.find(id);
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
