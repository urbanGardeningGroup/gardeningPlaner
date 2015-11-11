package org.vaadin.backend.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User
    {
        private String name;
        private String firstName;
        private String nickname;
        private String email;
        private String password;
        private Boolean isValidated;
        private Date accountDeletedOn;
        @OneToOne(mappedBy = "owner")
        private Garden garden;
        @ManyToMany(mappedBy = "supporters", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private List<Garden> supportedGarden;

        public void setName(String name)
        {
            this.name = name;
        }
        public String getName()
        {
            return name;
        }
        
        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }
        public String getFirstName()
        {
            return firstName;
        }

        public void setNickname(String nickname)
        {
            this.nickname = nickname;
        }
        public String getNickname()
        {
            return nickname;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }
        public String getEmail()
        {
            return email;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
        public String getPassword()
        {
            return password;
        }

        public Boolean isValidated()
        {
            return isValidated;
        }

        public Boolean isDeleted()
        {
            return false;
        }
    }
