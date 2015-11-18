package org.vaadin.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Garden.findAll", query = "SELECT g FROM Garden g"),
        @NamedQuery(name = "Garden.findByName", query = "SELECT g FROM Garden g WHERE LOWER(g.gardenName) LIKE :filter OR LOWER(g.gardenShortName) LIKE :filter"),
})
@Entity
public class Garden extends TimestampedEntity implements Serializable {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "A Garden Name is required")
    private String gardenName;
    private String gardenShortName;

    @Enumerated(EnumType.STRING)
    private GardenType gardenType;
    @Enumerated(EnumType.STRING)
    private Climate climate;
    @OneToMany(mappedBy = "garden", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Field> fields;
    private double size;
    @OneToOne(mappedBy = "garden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;
    @ManyToMany(mappedBy = "supportedGarden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> supporters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Climate getClimate() {
        return this.climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public GardenType getGardenType() {
        return gardenType;
    }

    public void setGardenType(GardenType gardenType) {
        this.gardenType = gardenType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public String getGardenShortName() {
        return gardenShortName;
    }

    public void setGardenShortName(String gardenShortName) {
        this.gardenShortName = gardenShortName;
    }

    public boolean isPersisted() {
        return id > 0;
    }
}
