package Models.Users;
import Enums.FriendType;
import Enums.Gender;
import Enums.PrivacyOption;
import Enums.RelationshipStatus;
import Models.Chat.Conversation;
import Models.Group;
import Models.Post;

import java.time.LocalDateTime;
import  java.util.*;

import java.util.Date;
import java.util.ArrayList;


//getters=g,setters=s

public abstract class User {

    private static int idCounter=1;
    private static int userPostsCounter=1;
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private Gender gender;
    private RelationshipStatus status;//m4 final momken ytlk aw yseeb sahbeto aw yfrke4 el5twba
    private String phone; //done
    private Date birth_date;//done
    public HashMap<User , FriendType> FriendType = new HashMap<User, FriendType>();//problem
    public HashMap<User, Conversation> FriendChat = new HashMap<User, Conversation>();//problem
    public  ArrayList<Post> posts = new ArrayList<Post>();
    public  ArrayList<User> friendRequest  = new ArrayList<User>();
    public ArrayList<Group> groups = new ArrayList<Group>();
    private final int id=idCounter;


    //constructor func
    public User(String email, String last_name, String first_name,  String password, RelationshipStatus status,  Gender gender ,Date birth_date,String phone) {
        this(email,last_name,first_name,password,status,gender,birth_date);
        this.phone=phone;
        this.posts = new ArrayList<>();
    }

    //without phone
    public User(String email, String last_name, String first_name, String password, RelationshipStatus status ,Gender gender, Date birth_date) {
      this(email, last_name, first_name, password, gender, birth_date);
      this.status= status;
    }

    //without RelationshipStatus
    public User(String email, String last_name, String first_name, String password, String phone ,Gender gender, Date birth_date) {
        this(email, last_name, first_name, password, gender, birth_date);
        this.phone=phone;
    }

    //minimum constructor
    public User(String email, String last_name, String first_name, String password, Gender gender, Date birth_date) {
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


//    public HashMap<User, Enums.FriendType> getFriendType()
//    {
//        return FriendType;
//    }


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

    public ArrayList<Group> getGroups()
    {
        return groups;
    }

    public int getId()
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

    public void setGroups(ArrayList<Group> groups)
    {
        this.groups = groups;
    }


    //=====================>methods<===================//

    public void createPost(User user, LocalDateTime date, PrivacyOption privacyOption , String content, ArrayList<User> tagged )
    {
        Post  post   = new Post(user, date, privacyOption, content, tagged);
    }

    public void createPost(User user, LocalDateTime date, PrivacyOption privacyOption , String content )
    {
        Post  post   = new Post(user, date, privacyOption, content);
    }


//    public HashMap<User , FriendType> FriendType = new HashMap<User, FriendType>();

    public ArrayList<User> getFriends(){
        return (ArrayList<User>) FriendType.keySet();
    }
    public ArrayList<Enums.FriendType> getFriendType(){
        return (ArrayList<Enums.FriendType>) FriendType.values();
    }

   public ArrayList<User> getFriendsConversations(){
        return (ArrayList<User>) FriendChat.keySet();
   }
    public ArrayList<Conversation> getConversations(){
       return (ArrayList<Conversation>) FriendChat.values();
    }

    public Set<Map.Entry<User, Conversation>> getConversatios(){
         return FriendChat.entrySet();
    }

    public String getAccountName(){
        return first_name+" "+last_name;
    }

    public void sharePost(Post post) {
        this.posts.add(post);
    }

    public void acceptRequest(User user,FriendType friendType){
        this.FriendType.put(user,friendType);
    }

    public  void spliceArray(User user){

        user.friendRequest.remove(user);

    }

}

