package BusinessLogic.Post;

import Exceptions.LogicException.PostLengthException;

public class Post {
    protected String content;
    protected String id;
    protected int likes;

    public Post(String content, String id, int likes)
            throws PostLengthException {
        this.id = id;
        if(!content.isEmpty() && content.length() < 250)
            this.content = content;
        else
            throw new PostLengthException();
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public void like() {
        ++likes;
    }
}
