package BusinessLogic.Post;

import Exceptions.LogicException.PostLengthException;

public class Comment extends Post {

    private String replied_to; // id of post or comment whom this comment is replied to

    public Comment(String content, String id, String replied_to) throws PostLengthException {
        super(content, id, 0);
        this.replied_to = replied_to;
    }

    public String getRepliedTo() {
        return replied_to;
    }
}
