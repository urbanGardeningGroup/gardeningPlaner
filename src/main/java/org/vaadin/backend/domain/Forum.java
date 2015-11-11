package org.vaadin.backend.domain;
import javax.persistence.*;
import java.util.List;

@Entity
public class Forum {
    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forum")
    private List<Comment> comments;

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
