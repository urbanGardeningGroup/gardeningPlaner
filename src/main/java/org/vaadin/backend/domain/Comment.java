package org.vaadin.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({    @NamedQuery(
        name = "Comment.findAll",
        query = "SELECT c FROM Comment c"
)})
@Entity
public class Comment extends TimestampedEntity implements Serializable{

    @Version
    int version;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Comment parentComment;
    @ManyToOne
    private Forum forum;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    @NotNull(message = "please set a title")
    private String title;
    @NotNull(message = "please provide content")
    private String content;


    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public void setId(Long id)
     {
         this.id = id;
     }

    public Long getId()
     {
         return id;
     }

    public void setParentId(Comment parentComment)
    {
        this.parentComment = parentComment;
    }

     public Comment getParentId()
    {
        return parentComment;
    }

     public void setUser(User userId)
     {
         this.user = userId;
     }

     public User getUser()
     {
         return user;
     }

     public void setContent(String content)
     {
         this.content = content;
     }

     public String getContent()
     {
         return content;
     }

     public void setTitle(String title)
     {
         this.title = title;
     }

     public String getTitle()
     {
         return title;
     }
 }
