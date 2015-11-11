
public class Comment {
	 protected int _id;
     protected int _userId;
     protected String _title;
     protected String _content;

     public void setId(int id)
     {
         _id = id;
     }

     public int getId()
     {
         return _id;
     }

     public void setUserId(int userId)
     {
         _userId = userId;
     }

     public int getUserId()
     {
         return _userId;
     }

     public void setContent(String content)
     {
         _content = content;
     }

     public String getContent()
     {
         return _content;
     }

     public void setTitle(String title)
     {
         _title = title;
     }

     public String getTitel()
     {
         return _title;
     }
 }
