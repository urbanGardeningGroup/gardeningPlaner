package org.vaadin.backend.domain;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

/**
 * Created by synto on 11.11.2015.
 */
public class Seed {
    @OneToOne(mappedBy = "seed", cascade = CascadeType.ALL)
    private Plant plant;

}
