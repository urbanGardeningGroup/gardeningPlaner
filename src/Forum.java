import java.util.List;

public class Forum {
	  protected List<Comment> _comments;

      public List<Comment> getComments()
      {
          return _comments;
      }

      public void setComments(List<Comment>  comments)
      {
          _comments = comments;
      }

      public void addComment(Comment comment)
      {
          _comments.add(comment);
      }
}
