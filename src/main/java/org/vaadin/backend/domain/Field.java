package org.vaadin.backend.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({    @NamedQuery(
        name = "Field.findAll",
        query = "SELECT f FROM Field f"
)})
@Entity
public class Field extends TimestampedEntity implements Serializable {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	private double size;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "field")
    private List<Plant> plants;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Garden garden;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setSize(double size)
     {
         this.size = size;
     }

    public double getSize()
     {
         return size;
     }

    public void setPlants(List<Plant> plants)
     {
         this.plants = plants;
     }

    public List<Plant> getPlants()
     {
         return plants;
     }
}
