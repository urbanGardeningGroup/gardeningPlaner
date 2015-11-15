package org.vaadin.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by synto on 11.11.2015.
 */
@NamedQueries({@NamedQuery(
        name = "PlantedPlant.findAll",
        query = "SELECT pp FROM PlantedPlant pp"
)})
@Entity
public class PlantedPlant extends Plant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantedPlant_id;
    @OneToOne(fetch = FetchType.EAGER)
    private PlantedPlant nextNodeRight;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "nextNodeRight")
    private PlantedPlant getNextNodeLeft;
    @OneToOne(fetch = FetchType.EAGER)
    private PlantedPlant getNextNodeTop;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "getNextNodeTop")
    private PlantedPlant getGetNextNodeBottom;

    @Override
    public long getId() {
        return plantedPlant_id;
    }

    @Override
    public void setId(long id) {
        plantedPlant_id = id;
    }


}
