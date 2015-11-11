package org.vaadin.backend.domain;
import javax.persistence.*;
import java.util.List;

@Entity
public class Field {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
	private double size;

    @Column
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "field")
    private List<Plant> plants;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Garden garden;

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
