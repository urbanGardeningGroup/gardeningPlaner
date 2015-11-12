package org.vaadin.backend;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.vaadin.backend.domain.Field;
import org.vaadin.backend.domain.CustomerStatus;
import org.vaadin.backend.domain.Gender;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Stateless
public class FieldService {

    @PersistenceContext(unitName = "field-pu")
    private EntityManager entityManager;

    public void saveOrPersist(Field entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(Field entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<Field> findAll() {
        CriteriaQuery<Field> cq = entityManager.getCriteriaBuilder().
                createQuery(Field.class);
        cq.select(cq.from(Field.class));
        return entityManager.createQuery(cq).getResultList();
    }


    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {
           //TODO: Create Test Data Import
        }
    }

    public void resetTestData() {
        if(!findAll().isEmpty()) {
            entityManager.createQuery("DELETE FROM Field f WHERE f.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
