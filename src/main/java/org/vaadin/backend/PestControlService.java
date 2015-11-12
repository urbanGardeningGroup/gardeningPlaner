package org.vaadin.backend;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.vaadin.backend.domain.Customer;
import org.vaadin.backend.domain.CustomerStatus;
import org.vaadin.backend.domain.Gender;
import org.vaadin.backend.domain.PestControl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Stateless
public class PestControlService {

    @PersistenceContext(unitName = "pestcontrol-pu")
    private EntityManager entityManager;

    public void saveOrPersist(PestControl entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(PestControl entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<PestControl> findAll() {
        CriteriaQuery<PestControl> cq = entityManager.getCriteriaBuilder().
                createQuery(PestControl.class);
        cq.select(cq.from(PestControl.class));
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
            entityManager.createQuery("DELETE FROM PestControl pc WHERE pc.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
