package Models;

import Enums.ReactType;
import Models.Users.User;

import java.util.ArrayList;

public class Comment {
    private final User user;
    private String text;
    private ArrayList<Comment> replies;
    private ArrayList<React> reacts;

    ///--------------------constructor------------------------///
    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }

    ///--------------------getters && Setters------------------------///
    public User getUser() {
        return user;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Comment> replies) {
        this.replies = replies;
    }

    public ArrayList<React> getReacts() {
        return reacts;
    }

    public void setReacts(ArrayList<React> reacts) {
        this.reacts = reacts;
    }


    ///--------------------methods------------------------///
    public void addReact(User user, ReactType react) {
        React newReact = new React(user, react);
        reacts.add(newReact);
    }

    public void changeReact(User user, ReactType react) {
        this.reacts.forEach((React r) -> {
            if (r.getUser().equals(user) && r.getReact() != react) {
                r.setReact(react);
            }
        });
    }

}
