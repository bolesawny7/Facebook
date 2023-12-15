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

}
