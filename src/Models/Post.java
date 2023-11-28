package Models;

import javax.xml.crypto.Data;
import java.util.Date;

public class Post {
    private User createdBy;
    private Date creationDate;
    private String content;
    protected User[] tagged;
    private boolean privacyoption; //(0 friends , 1 public)
    //private React[] reacts;


    public Post(User createdBy, Date creationDate, String content, User[] tagged, boolean privacyoption) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.content = content;
        this.tagged = tagged;
        this.privacyoption = privacyoption;
    }
}