package org.vaadin.backend.domain;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NamedQueries({@NamedQuery(name = "Field.findAll", query = "SELECT f FROM Field f"),
        @NamedQuery(name = "Field.findByName", query = "SELECT f FROM Field f WHERE LOWER(f.fieldName) LIKE :filter"),
})
@Entity
public class Field extends TimestampedEntity implements Serializable {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double size;

    @NotNull(message = "A Field Name is required")
    private String fieldName;

    private Point position;

    @Enumerated(EnumType.STRING)
    private FieldType fieldType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "field")
    private List<PlantedPlant> plantedPlants;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Garden garden;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public List<PlantedPlant> getPlantedPlants() {
        return plantedPlants;
    }

    public void setPlantedPlants(List<PlantedPlant> plantedPlants) {
        this.plantedPlants = plantedPlants;
    }

    public boolean isPersisted() {
        return id > 0;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }
}
