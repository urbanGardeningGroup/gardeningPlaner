package org.vaadin.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({    @NamedQuery(
        name = "PestControl.findAll",
        query = "SELECT pc FROM PestControl pc"
)})
@Entity
public class PestControl extends TimestampedEntity implements Serializable {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "pestControls", cascade = CascadeType.ALL)
    private List<Pest> againstPests;
    private String name;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

}
