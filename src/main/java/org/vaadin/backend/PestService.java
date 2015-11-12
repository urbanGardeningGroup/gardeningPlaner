package org.vaadin.backend;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.vaadin.backend.domain.Customer;
import org.vaadin.backend.domain.CustomerStatus;
import org.vaadin.backend.domain.Gender;
import org.vaadin.backend.domain.Pest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Stateless
public class PestService {

    @PersistenceContext(unitName = "pest-pu")
    private EntityManager entityManager;

    public void saveOrPersist(Pest entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(Pest entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<Pest> findAll() {
        CriteriaQuery<Pest> cq = entityManager.getCriteriaBuilder().
                createQuery(Pest.class);
        cq.select(cq.from(Pest.class));
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
            entityManager.createQuery("DELETE FROM Pest p WHERE p.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
