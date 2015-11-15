package org.vaadin.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({@NamedQuery(
        name = "Pest.findAll",
        query = "SELECT p FROM Pest p"
)})
@Entity
public class Pest extends TimestampedEntity implements Serializable {
    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany()
    private List<Plant> threatenedPlants;
    @ManyToMany()
    @JoinTable(
            name = "PEST_PESTCONTROL",
            joinColumns = {@JoinColumn(name = "PEST_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "PESTCONTROL_ID", referencedColumnName = "id")})
    private List<PestControl> pestControls;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPersisted() {
        return id > 0;
    }

}
