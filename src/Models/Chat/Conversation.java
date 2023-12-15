package Models.Chat;

import Models.Users.User;

import java.util.ArrayList;

public class Conversation {
    public static int idCounter=1;
    private final  int id=idCounter;
    private User user1;
    private User user2;
    private ArrayList<Message> messages = new ArrayList<Message>();

    public Conversation( User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        idCounter++;
    }
    // Getters
    public int getId() {
        return id;
    }

    public User getUser1() {
        return user1;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public User getUser2() {
        return user2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    // Setters

    public void sendMessage(Message message) {
        messages.add(message);
    }
}
