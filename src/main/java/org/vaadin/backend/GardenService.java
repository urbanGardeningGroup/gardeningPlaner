package org.vaadin.backend;

import org.vaadin.backend.domain.Garden;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class GardenService {

    @PersistenceContext(unitName = "garden-pu")
    private EntityManager entityManager;

    public void saveOrPersist(Garden entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(Garden entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<Garden> findAll() {
        CriteriaQuery<Garden> cq = entityManager.getCriteriaBuilder().
                createQuery(Garden.class);
        cq.select(cq.from(Garden.class));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Garden> findByName(String filter) {
        if (filter == null || filter.isEmpty()) {
            return findAll();
        }
        filter = filter.toLowerCase();
        return entityManager.createNamedQuery("Garden.findByName",
                Garden.class)
                .setParameter("filter", filter + "%").getResultList();
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
            entityManager.createQuery("DELETE FROM Garden g WHERE g.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
