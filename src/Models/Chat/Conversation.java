package Models.Chat;

import Models.Users.User;

import java.util.ArrayList;

public class Conversation {
    private String id;
    private User user1;
    private User user2;
    private ArrayList<Message> messages = new ArrayList<Message>();

    public Conversation(String id, User user1, User user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }
    // Getters
    public String getId() {
        return id;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    // Setters

    public void setUser1(User user1) {
        this.user1 = user1;
    }
    public void setUser2(User user2) {
        this.user2 = user2;
    }
    public void sendMessage(User user,Message message) {
        messages.add(message);
    }
}
