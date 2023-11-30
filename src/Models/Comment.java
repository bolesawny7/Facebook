package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Comment {
    private User user;
    private String text;
    private ArrayList<Comment> replies;
    private ArrayList<React>reacts;

    ///--------------------constructor------------------------///
    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public void addReact(User user,ReactType react){
        React newReact = new React(user ,react);
        reacts.add(newReact);
    }
//    public void changeReact(User user,ReactType react){
//
//    }
}
