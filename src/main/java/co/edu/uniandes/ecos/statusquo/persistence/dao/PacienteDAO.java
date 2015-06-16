/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.persistence.dao;

import co.edu.uniandes.ecos.statusquo.persistence.entities.Paciente;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dev
 */
@Stateless
@LocalBean
public class PacienteDAO extends AbstractDAO<Paciente> {

    @PersistenceContext(unitName = "ExperimentoECOS1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteDAO() {
        super(Paciente.class);
    }
}
