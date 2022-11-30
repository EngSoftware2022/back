package seapa.back.Repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class BaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Object entity) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(entity);
    }

    public <E> void delete(Long id, Class<E> entityClass) {
        Session session = this.getSession();
        session.delete(session.load(entityClass, id));
    }

    public void delete(Object entity) {
        this.getSession().delete(entity);
    }

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public <E> E findById(Long id, Class<E> entityClass) {
        return this.entityManager.find(entityClass, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
