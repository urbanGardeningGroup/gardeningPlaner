package org.vaadin.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by synto on 11.11.2015.
 */
@NamedQueries({    @NamedQuery(
        name = "Seed.findAll",
        query = "SELECT s FROM Seed s"
)})
@Entity
public class Seed extends TimestampedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "seed", cascade = CascadeType.ALL)
    private Plant plant;

    public void setId(Long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

}
