package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Comment {
    private User user;
    private String text;
    private ArrayList<Comment> replies;

    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }


}
