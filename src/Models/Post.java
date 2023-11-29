package Models;

import java.util.Date;
import java.util.ArrayList;


public class Post {
    private User createdBy;
    private Date creationDate;
    private String content;
    //  protected User[] tagged;
    private static int postCounter=1;
    private final int  postId =postCounter;
    private boolean privacyOption; //(0 friends , 1 public)
    ArrayList<User> tagged = new ArrayList<>();

    // post without tag
    public Post(User createdBy, Date creationDate,  boolean privacyOption , String content)
    {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.content = content;
        this.privacyOption = privacyOption;
        postCounter++;
    }

    // post with tag
    public Post(User createdBy, Date creationDate, boolean privacyOption , String content, ArrayList<User> tagged )
    {
        this(createdBy,creationDate,privacyOption,content);
        this.tagged = tagged;
    }

    public void editPost(String content , boolean privacyOption )
    {
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


}