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
    HashMap<User, FriendType> FriendType = new HashMap<User, FriendType>();
    HashMap<User, Conversation> FriendChat = new HashMap<User, Conversation>();
    public Post posts[];
    public Group groups[];

}
