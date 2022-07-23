package BusinessLogic.Post;

import Exceptions.LogicException.PostLengthException;

public class Post {
    private String content;
    private String id;

    public Post(String content, String id)
            throws PostLengthException {
        this.id = id;
        if(!content.isEmpty() && content.length() < 250)
            this.content = content;
        else
            throw new PostLengthException();
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }
}
