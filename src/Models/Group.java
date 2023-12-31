package Models;

import Enums.PrivacyOption;
import Models.Users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


public class Group {
    private String CreationDate;
    private String name;
    private String description;
    public ArrayList<User> members = new ArrayList<>();
    public ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<User> Admins = new ArrayList<>();

    public ArrayList<User> getAdmins() {
        return Admins;
    }

    public void setAdmins(User admin) {
        Admins.add(admin);
    }

    //-----------------------Constructors------------------------------------///

    Group(String name) {
        this.name = name;
    }

    public Group(String name, String description) {
        this(name);
        this.description = description;
    }


    //-----------------------Setters------------------------------------//

    public void editDesc(String description) {
        this.description = description;
    }

    public void editName(String name) {
        this.name = name;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public void createPost(User user, LocalDateTime date, PrivacyOption privacyOption, String content, ArrayList<User> tagged) {
        Post post = new Post(user, date, privacyOption, content, tagged);
    }

    public void createPost(User user, LocalDateTime date, PrivacyOption privacyOption, String content) {
        Post post = new Post(user, date, privacyOption, content);
    }

    //-----------------------Getters------------------------------------

    public ArrayList<User> getMembers() {
        return members;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
