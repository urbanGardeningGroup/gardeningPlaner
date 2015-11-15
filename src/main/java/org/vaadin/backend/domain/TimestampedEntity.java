package org.vaadin.backend.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by synto on 11.11.2015.
 */
@MappedSuperclass
public abstract class TimestampedEntity {
    /**
     * Update date
     */
    @Column(name = "updatedAt", insertable = false, updatable = true)
    @NotNull
    private Timestamp updatedAt;
    /**
     * Creation date
     */
    @Column(name = "createdAt", insertable = true, updatable = false)
    @NotNull
    private Timestamp createdAt;

    /**
     * @return the updatedAt
     */

    Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the createdAt
     */

    Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    void onCreate() {
        this.setCreatedAt(new Timestamp((new Date()).getTime()));
    }

    @PreUpdate
    void onPersist() {
        this.setUpdatedAt(new Timestamp((new Date()).getTime()));
    }
}
