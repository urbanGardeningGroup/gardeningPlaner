package org.vaadin.backend;

import org.vaadin.backend.domain.Comment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class CommentService {

    @PersistenceContext(unitName = "comment-pu")
    private EntityManager entityManager;

    public void saveOrPersist(Comment entity) {
        if (entity.getId() > 0) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void deleteEntity(Comment entity) {
        if (entity.getId() > 0) {
            // reattach to remove
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    public List<Comment> findAll() {
        CriteriaQuery<Comment> cq = entityManager.getCriteriaBuilder().
                createQuery(Comment.class);
        cq.select(cq.from(Comment.class));
        return entityManager.createQuery(cq).getResultList();
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {
            // TODO: create Comment Test Set
        }
    }

    public void resetTestData() {
        if (!findAll().isEmpty()) {
            entityManager.createQuery("DELETE FROM Comment c WHERE c.id > 0").
                    executeUpdate();
        }
        ensureTestData();
    }

}
