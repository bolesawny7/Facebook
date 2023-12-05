package Models;

import Enums.ReactType;
import Models.Users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {
    private LocalDateTime creationDate;
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

    public void addReply(User user, String text) {
        Comment newReply = new Comment(user, text);
        replies.add(newReply);
    }
    public ArrayList<React> getReacts() {
        return reacts;
    }

    public void setReacts(ArrayList<React> reacts) {
        this.reacts = reacts;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
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
