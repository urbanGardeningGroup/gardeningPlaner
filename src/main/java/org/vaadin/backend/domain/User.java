package org.vaadin.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NamedQueries({@NamedQuery(
        name = "User.findAll",
        query = "SELECT u FROM User u"
)})
@Entity
public class User extends TimestampedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String FACEBOOK_ID;
    @Column
    private String GOOGLEPLUS_ID;

    @Column
    private String name;
    @Column
    private String firstName;
    @Column
    private String nickname;
    @Column
    private String email;

    //private String password;
    private Boolean isValidated;
    private Date accountDeletedOn;
    @OneToOne()
    private Garden garden;
    @ManyToMany()
    @JoinTable(
            name = "USER_GARDENSUPPORTER",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "GARDEN_ID", referencedColumnName = "id")})
    private List<Garden> supportedGarden;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isValidated() {
        return isValidated;
    }

    public Boolean isDeleted() {
        return false;
    }

    public boolean isPersisted() {
        return id > 0;
    }
}
