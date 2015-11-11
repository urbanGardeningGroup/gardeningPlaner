
public class CommentReplay extends  Comment {
    protected int _parentId;

    public void setParentId(int parentId)
    {
        _parentId = parentId;
    }

    public int getParentId()
    {
        return _parentId;
    }
}
