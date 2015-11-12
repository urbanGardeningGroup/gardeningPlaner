package org.vaadin.backend;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.vaadin.backend.domain.Customer;
import org.vaadin.backend.domain.CustomerStatus;
import org.vaadin.backend.domain.Garden;
import org.vaadin.backend.domain.Gender;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {
            //TODO: Ensure Testdata
        }
    }

    public void resetTestData() {
        if(!findAll().isEmpty()) {
            entityManager.createQuery("DELETE FROM Garden g WHERE g.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
