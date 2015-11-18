package org.vaadin.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by synto on 11.11.2015.
 */
@MappedSuperclass
public abstract class TimestampedEntity implements Serializable {
    /**
     * Update date
     */
    @Basic(optional = false)
    @Column(name = "updatedAt", insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    /**
     * Creation date
     */
    @Basic(optional = false)
    @Column(name = "createdAt", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * @return the updatedAt
     */

    Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the createdAt
     */

    Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.setCreatedAt((new Date()));
    }

    @PreUpdate
    protected void onPersist() {
        this.setUpdatedAt(new Date());
    }
}
