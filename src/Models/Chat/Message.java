package Models.Chat;

public class Message {
    private String content;
    private String date;
    private String sender;
    private String receiver;

    public Message(String content, String date, String sender, String receiver) {
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    // Getters
    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    //Setters

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
