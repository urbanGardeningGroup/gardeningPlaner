package org.vaadin.backend.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({    @NamedQuery(
        name = "Garden.findAll",
        query = "SELECT g FROM Garden g"
)})
@Entity
public class Garden extends TimestampedEntity implements Serializable {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Enumerated
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setClimate(Climate climate)
     {
         this.climate = climate;
     }

    public Climate getClimate()
     {
         return this.climate;
     }

    public void setFields(List<Field> fields)
     {
         this.fields = fields;
     }

    public List<Field> getFields()
     {
         return fields;
     }

    public void setGardenType(GardenType gardenType)
     {
         this.gardenType = gardenType;
     }

    public GardenType getGardenType()
     {
         return gardenType;
     }

    public void setSize(double size)
     {
         this.size = size;
     }

    public double getSize()
     {
         return size;
     }
}
