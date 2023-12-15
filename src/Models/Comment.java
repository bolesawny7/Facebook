package Models;

import Enums.ReactType;
import Models.Users.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {
    private static int commentCounter = 1;
    private int commentID = commentCounter;
    private LocalDateTime creationDate;
    private final User user;
    private String text;
    private ArrayList<Comment> replies = new ArrayList<>();
    private ArrayList<React> reacts = new ArrayList<>();

    ///--------------------constructor------------------------///
    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
        this.creationDate = LocalDateTime.now();
        commentCounter += 1;
    }

    public int getCommentID() {
        return commentID;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    ///--------------------methods------------------------///
    public void addReact(User user, ReactType react) {
        React newReact = new React(user, react);
        reacts.add(newReact);
    }

}
