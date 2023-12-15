package Models;

import Enums.PrivacyOption;
import Enums.ReactType;
import Models.Users.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
//import org.w3c.dom.Comment;
import org.w3c.dom.ls.LSOutput;


public class Post {
    private User createdBy;
    private LocalDateTime creationDate;
    private String content;
    private static int postCounter=1;
    private final int  postId =postCounter;
    private PrivacyOption privacyOption;
    private ArrayList<User> tagged = new ArrayList<>();
    private ArrayList<React> reacts=new ArrayList<>();

    private ArrayList<Comment> comments = new ArrayList<>();

    public void setReacts(ArrayList<React> reacts) {
        this.reacts = reacts;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getPostId() {
        return postId;
    }

    // post without tag
    public Post(User createdBy, LocalDateTime creationDate,  PrivacyOption privacyOption , String content) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.content = content;
        this.privacyOption = privacyOption;
        postCounter++;
    }

    // post with tag
    public Post(User createdBy, LocalDateTime creationDate, PrivacyOption privacyOption , String content, ArrayList<User> tagged ) {
        this(createdBy,creationDate,privacyOption,content);
        this.tagged = tagged;
    }

    public void setTaggedUser(User user)
    {
        tagged.add(user);
    }


    public ArrayList<User> getTaggedUsers()
    {
        return tagged;
    }

    public String getContent(){
        return content;
    }

    //Comments
    public void addComment(User user, String text) {
        Comment newComment = new Comment(user, text);
        comments.add(newComment);
    }

    //React
    public ArrayList<React> getReacts() {
        return reacts;
    }

    public void addReact(User user, ReactType react) {
        React newReact = new React(user, react);
        reacts.add(newReact);
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public PrivacyOption isPrivacyOption() {
        return privacyOption;
    }

    public ArrayList<Comment> getComments() {

        return comments;
    }
}