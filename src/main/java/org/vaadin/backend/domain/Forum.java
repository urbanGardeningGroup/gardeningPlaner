package org.vaadin.backend.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({    @NamedQuery(
        name = "Forum.findAll",
        query = "SELECT f FROM Forum f"
)})
@Entity
public class Forum extends TimestampedEntity implements Serializable {

    @Version
    int version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forum")
    private List<Comment> comments;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public List<Comment> getComments()
    {
          return comments;
    }

    public void setComments(List<Comment>  comments)
      {
          this.comments = comments;
      }

    public void addComment(Comment comment)
      {
          comments.add(comment);
      }
}
