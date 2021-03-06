package org.vaadin.backend;

import org.vaadin.backend.domain.Plant;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class PlantService {

    @PersistenceContext(unitName = "plant-pu")
    private EntityManager entityManager;

    public void saveOrPersist(Plant entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(Plant entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<Plant> findAll() {
        CriteriaQuery<Plant> cq = entityManager.getCriteriaBuilder().
                createQuery(Plant.class);
        cq.select(cq.from(Plant.class));
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
            entityManager.createQuery("DELETE FROM Plant p WHERE p.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
