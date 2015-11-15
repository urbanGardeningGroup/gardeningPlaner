package org.vaadin.backend;

import org.vaadin.backend.domain.Field;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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

    public List<Field> findByGardenID() {
        // todo: Build ID Access via new Pattern
        CriteriaQuery<Field> cq = entityManager.getCriteriaBuilder().
                createQuery(Field.class);
        cq.select(cq.from(Field.class));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Field> findByName(String filter) {
        if (filter == null || filter.isEmpty()) {
            //todo:  Use different Default Access (Security Reason)
            return findAll();
        }
        filter = filter.toLowerCase();
        return entityManager.createNamedQuery("Field.findByName",
                Field.class)
                .setParameter("filter", filter + "%").getResultList();
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
        if (!findAll().isEmpty()) {
            entityManager.createQuery("DELETE FROM Field f WHERE f.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
