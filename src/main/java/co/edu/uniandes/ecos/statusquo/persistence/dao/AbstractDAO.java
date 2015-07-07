package co.edu.uniandes.ecos.statusquo.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

public abstract class AbstractDAO<T> {

    private Class<T> entityClass;

    public AbstractDAO(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(final T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(final T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(final T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public void refresh(final T entity) {
        getEntityManager().refresh(entity);
    }

    public T find(final Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(final int firstResult, final int maxResults) {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        final Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }

    public List<T> findByNamedQuery(final String queryName, final Map<String, Object> params) {
        final Query q = getEntityManager().createNamedQuery(queryName);

        final Set<Entry<String, Object>> paramList = params.entrySet();

        for (Entry<String, Object> entry : paramList) {
            q.setParameter(entry.getKey(), entry.getValue());
        }

        return q.getResultList();

    }

    public List<T> findByNamedQuery(final String queryName, final Map<String, Object> params, final int firstResult, final int maxResults) {
        final Query q = getEntityManager().createNamedQuery(queryName);

        final Set<Entry<String, Object>> paramList = params.entrySet();

        for (Entry<String, Object> entry : paramList) {
            q.setParameter(entry.getKey(), entry.getValue());
        }
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();

    }

    public T findSingleByNamedQuery(final String queryName, final Map<String, Object> params) {
        final TypedQuery<T> q = getEntityManager().createNamedQuery(queryName, entityClass);

        final Set<Entry<String, Object>> paramList = params.entrySet();

        for (Entry<String, Object> entry : paramList) {
            q.setParameter(entry.getKey(), entry.getValue());
        }

        return q.getSingleResult();
    }

    public List<T> findByNamedQuery(final String queryName) {
        Query q = getEntityManager().createNamedQuery(queryName);

        return q.getResultList();

    }

    public List<?> findByJPQLQuery(final String jpqlQuery, final Map<String, Object> params) {
        final TypedQuery<T> q = getEntityManager().createQuery(jpqlQuery, entityClass);

        final Set<Entry<String, Object>> paramList = params.entrySet();

        for (Entry<String, Object> entry : paramList) {
            q.setParameter(entry.getKey(), entry.getValue());
        }

        return q.getResultList();
    }

    public List<T> findByJPQLQuery(final String jpqlQuery) {
        final TypedQuery<T> q = getEntityManager().createQuery(jpqlQuery, entityClass);

        return q.getResultList();
    }

    public List<?> findListByNativeQuery(final String nquery) {
        final Query q = getEntityManager().createNativeQuery(nquery);
        return q.getResultList();
    }

    public Object findSingleResultByNativeQuery(final String nquery) throws NoResultException {
        final Query q = getEntityManager().createNativeQuery(nquery);
        return q.getSingleResult();
    }

    public int count() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        final Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        final Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void initTransaction(UserTransaction utx) {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
    }

    public void rollbackTransaction(UserTransaction utx) {
        try {
            utx.rollback();
        } catch (Exception ex) {
        }
    }

    public void commitTransaction(UserTransaction utx) {
        try {
            getEntityManager().flush();
            utx.commit();
        } catch (Exception ex) {
        }
    }
}
