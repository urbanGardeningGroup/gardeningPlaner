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
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @OneToOne
    private int parentId;
    @ManyToOne
    private Forum forum;
    @OneToOne
    private int userId;
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

    public void setId(int id)
     {
         id = id;
     }

    public int getId()
     {
         return id;
     }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

     public int getParentId()
    {
        return parentId;
    }

     public void setUserId(int userId)
     {
         this.userId = userId;
     }

     public int getUserId()
     {
         return userId;
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
