package org.vaadin.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({@NamedQuery(
        name = "Plant.findAll",
        query = "SELECT p FROM Plant p"
)})
@Entity
public class Plant extends TimestampedEntity implements Serializable {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private PlantStatus status;
    private double height;

    @OneToOne()
    private Seed seed;
    @ManyToMany(mappedBy = "threatenedPlants")
    private List<Pest> pests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlantStatus getStatus() {
        return status;
    }

    public void setStatus(PlantStatus status) {
        this.status = status;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }

    public boolean isPersisted() {
        return id > 0;
    }
}
