package Models;

import Enums.ReactType;
import Models.Users.User;

import java.util.Date;
import java.util.ArrayList;
//import org.w3c.dom.Comment;
import org.w3c.dom.ls.LSOutput;


public class Post {
    private User createdBy;
    private Date creationDate;
    private String content;
    //  protected User[] tagged;
    private static int postCounter=1;
    private final int  postId =postCounter;
    private boolean privacyOption; //(0 friends , 1 public)
    private ArrayList<User> tagged = new ArrayList<>();

    private ArrayList<React> reacts;

    private ArrayList<Comment> comments;


    // post without tag
    public Post(User createdBy, Date creationDate,  boolean privacyOption , String content) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.content = content;
        this.privacyOption = privacyOption;
        postCounter++;
    }

    // post with tag
    public Post(User createdBy, Date creationDate, boolean privacyOption , String content, ArrayList<User> tagged ) {
        this(createdBy,creationDate,privacyOption,content);
        this.tagged = tagged;
    }


    public void editPost(String content , boolean privacyOption ) {
        this.content = content;
        this.privacyOption = privacyOption;
    }
    public void editPost(String content )
    {
        this.content = content;
    }

    public void editPost(boolean privacyOption )
    {
        this.privacyOption = privacyOption;
    }

    public void setTaggedUser(User user)
    {
        tagged.add(user);
    }

    public void removeTaggedUser(User user)
    {
        tagged.remove(user);
    }

    public ArrayList<User> getTaggedUsers()
    {
        return tagged;
    }

    //Comments
    public void addComment(User user, String text) {
        Comment newComment = new Comment(user, text);
        comments.add(newComment);
    }
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
    public void editComment(Comment comment, String text) {
        this.comments.forEach((Comment c) -> {
            if (c.equals(comment)) {
                c.setText(text);
            }
        });
    }

    //React
    public void changeReact(User user, ReactType react) {
        this.reacts.forEach((React r) -> {
            if (r.getUser().equals(user) && r.getReact() != react) {
                r.setReact(react);
            }
        });
    }

    public void addReact(User user, ReactType react) {
        React newReact = new React(user, react);
        reacts.add(newReact);
    }

}