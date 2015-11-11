package org.vaadin.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Plant {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private PlantStatus status;
    @Column
    private double height;

    @OneToOne(mappedBy = "plant")
    private Seed seed;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Field field;
    @ManyToMany(mappedBy = "threatenedPlants")
    private List<Pest> pests;


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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }


}
