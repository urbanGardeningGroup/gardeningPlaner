package org.vaadin.backend;

import org.vaadin.backend.domain.Seed;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class SeedService {

    @PersistenceContext(unitName = "seed-pu")
    private EntityManager entityManager;

    public void saveOrPersist(Seed entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(Seed entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<Seed> findAll() {
        CriteriaQuery<Seed> cq = entityManager.getCriteriaBuilder().
                createQuery(Seed.class);
        cq.select(cq.from(Seed.class));
        return entityManager.createQuery(cq).getResultList();
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {
            //TODO: Ensure Testdata
        }
    }

    public void resetTestData() {
        if (!findAll().isEmpty()) {
            entityManager.createQuery("DELETE FROM Seed s WHERE s.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
