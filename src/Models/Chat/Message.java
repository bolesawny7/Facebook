package Models.Chat;

import Models.Users.User;

import java.time.LocalDateTime;

public class Message {
    private String content;
    private LocalDateTime date;
    private User sender;
    private User receiver;

    public Message(String content, LocalDateTime date, User sender, User receiver) {
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }


    // Getters
    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    //Setters

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
