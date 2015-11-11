package org.vaadin.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pest {
    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "pests")
    private List<Plant> threatenedPlants;

    @ManyToMany(mappedBy = "againstPests")
    private List<PestControls> pestControls;


}
