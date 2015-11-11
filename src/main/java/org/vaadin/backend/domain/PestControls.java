package org.vaadin.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class PestControls {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "pestControls", cascade = CascadeType.ALL)
    private List<Pest> againstPests;

    @Column
    private String name;

}
