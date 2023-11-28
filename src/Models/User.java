package Models;
import  java.util.*;

import java.util.Date;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//getters=g,setters=s

public class User {

    private static int idCounter=1;
    private static int userPostsCounter=1;

    private String email; //done g=32 s=36
    private String first_name;//done g=48 s=52
    private String last_name;//done g=36 g =40
    //    private boolean martial_status;//enum ( married or single or engaged )
    private String password;//done g=63,s= 67
    private boolean gender;//0female 1 male
    private RelationshipStatus status;//m4 final momken ytlk aw yseeb sahbeto aw yfrke4 el5twba
    private String phone; //done
    private Date birth_date;//done
    protected HashMap<User, FriendType> FriendType = new HashMap<User, FriendType>();//problem
    protected HashMap<User, Conversation> FriendChat = new HashMap<User, Conversation>();//problem
//    public Post posts[];

    public  ArrayList<Post> posts = new ArrayList<>();

    public Group groups[];
    private final int id=idCounter;

    //constructor func
    public User(String email, String last_name, String first_name,  String password, RelationshipStatus status,  boolean gender ,Date birth_date,String phone) {
        this(email,last_name,first_name,password,status,gender,birth_date);
        this.phone=phone;
    }

    //without phone
    public User(String email, String last_name, String first_name, String password, RelationshipStatus status ,boolean gender, Date birth_date) {
      this(email, last_name, first_name, password, gender, birth_date);
      this.status= status;
    }

    //without RelationshipStatus
    public User(String email, String last_name, String first_name, String password, String phone ,boolean gender, Date birth_date) {
        this(email, last_name, first_name, password, gender, birth_date);
        this.phone=phone;
    }

    //minimum constructor
    public User(String email, String last_name, String first_name, String password, boolean gender, Date birth_date) {
        this.email = email;
        this.last_name = last_name;
        this.first_name = first_name;
        this.password = password;
        this.birth_date = birth_date;
        this.gender=gender;
        idCounter++;

    }



    //================================> getters <==========================================//
    public String getEmail() {
        return email;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public RelationshipStatus isMartial_status() {
        return status;
    }


    public String getPassword() {
        return password;
    }


    public String getPhone() {
        return phone;
    }

    public Date getBirth_date() {
        return birth_date;
    }


    public HashMap<User, Models.FriendType> getFriendType()
    {
        return FriendType;
    }


//    public HashMap<User, Conversation> getFriendChat() {
//        return FriendChat;
//    }


    //important
//    public void setFriendChat(User user,Conversation conv) {
//        FriendChat.put(user,conv);
//    }

    public ArrayList<Post>  getPosts()
    {
        return posts;
    }

    public Group[] getGroups()
    {
        return groups;
    }

    protected int getId()
    {
        return this.id;
    }

    //=====================>Setters<===================//

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setMartial_status(RelationshipStatus state) {
        status=state;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setBirth_date(Date birth_date)
    {
        this.birth_date = birth_date;
    }

    public void setFriendType(User user,FriendType type)
    {
        FriendType.put(user,type);
    }

    public void setPosts(ArrayList<Post> posts)
    {
        this.posts = posts;
    }

    public void setGroups(Group[] groups)
    {
        this.groups = groups;
    }





    //=====================>methods<===================//

    public void createPost(User user, Date date,  boolean privacyOption , String content, User[] tagged )
    {
        Post  post   = new Post(user, date, privacyOption, content, tagged);
    }

    public void createPost(User user, Date date,  boolean privacyOption , String content )
    {
        Post  post   = new Post(user, date, privacyOption, content);
    }



}