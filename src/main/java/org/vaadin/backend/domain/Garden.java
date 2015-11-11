package org.vaadin.backend.domain;
import javax.persistence.*;
import java.util.List;

@Entity
public class Garden {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated
	private GardenType gardenType;
    @Enumerated(EnumType.STRING)
    private Climate climate;
    @OneToMany(mappedBy = "garden", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Field> fields;
    @Column
    private double size;
    @OneToOne(mappedBy = "garden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;0
    @ManyToMany(mappedBy = "supportedGarden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> supporters;

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
