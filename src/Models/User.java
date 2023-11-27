package Models;
import  java.util.*;

import java.util.Date;

public class User {
    private String email;
    private String last_name;
    private String first_name;
    private boolean martial_status;
    private String password;
    private String phone;
    private Date birth_date;
    protected HashMap<User, FriendType> FriendType = new HashMap<User, FriendType>();
    protected HashMap<User, Conversation> FriendChat = new HashMap<User, Conversation>();
    public Post posts[];
    public Group groups[];

    //constructor func
    public User(String email, String last_name, String first_name, boolean martial_status, String password, String phone, Date birth_date) {
        this.email = email;
        this.last_name = last_name;
        this.first_name = first_name;
        this.martial_status = martial_status;
        this.password = password;
        this.phone = phone;
        this.birth_date = birth_date;
    }


    //getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public boolean isMartial_status() {
        return martial_status;
    }

    public void setMartial_status(boolean martial_status) {
        this.martial_status = martial_status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public HashMap<User, Models.FriendType> getFriendType() {
        return FriendType;
    }

    public void setFriendType(User user,FriendType type) {
        FriendType.put(user,type);
    }

    public HashMap<User, Conversation> getFriendChat() {
        return FriendChat;
    }


    //important
    public void setFriendChat(User user,Conversation conv) {
        FriendChat.put(user,conv);
    }

    public Post[] getPosts() {
        return posts;
    }

    public void setPosts(Post[] posts) {
        this.posts = posts;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }
}
