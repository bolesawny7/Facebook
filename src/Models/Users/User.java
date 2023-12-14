package Models.Users;
import Enums.FriendType;
import Enums.Gender;
import Enums.PrivacyOption;
import Enums.RelationshipStatus;
import Models.Chat.Conversation;
import Models.Group;
import Models.Post;
import Models.Users.Managements.FriendManagement;
import Models.Users.Managements.PostManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import  java.util.*;

import java.util.Date;
import java.util.ArrayList;


//getters=g,setters=s

public abstract class User implements FriendManagement, PostManagement {
    private static int idCounter=1;
    private static int userPostsCounter=1;
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private Gender gender;
    private RelationshipStatus status;//m4 final momken ytlk aw yseeb sahbeto aw yfrke4 el5twba
    private String phone; //done
    private LocalDate birth_date;//done
    public HashMap<User , FriendType> FriendType = new HashMap<User, FriendType>();
    public HashMap<User, Conversation> FriendChat = new HashMap<User, Conversation>();
    public  ArrayList<Post> posts = new ArrayList<Post>();
    public  ArrayList<User> ReceivedFriendRequests  = new ArrayList<User>();
    public  ArrayList<User> sentFriendRequests  = new ArrayList<User>();
    public ArrayList<Group> groups = new ArrayList<Group>();
    public ArrayList<Group> createdGroups = new ArrayList<Group>();
    private final int id=idCounter;


    //constructor func
    public User(String email, String last_name, String first_name,  String password, RelationshipStatus status,  Gender gender ,LocalDate birth_date,String phone) {
        this(email,last_name,first_name,password,status,gender,birth_date);
        this.phone=phone;
        this.posts = new ArrayList<>();
    }

    //without phone
    public User(String email, String last_name, String first_name, String password, RelationshipStatus status ,Gender gender, LocalDate birth_date) {
      this(email, last_name, first_name, password, gender, birth_date);
      this.status= status;
    }

    //without RelationshipStatus
    public User(String email, String last_name, String first_name, String password, String phone ,Gender gender, LocalDate birth_date) {
        this(email, last_name, first_name, password, gender, birth_date);
        this.phone=phone;
    }


    //minimum constructor
    public User(String email, String last_name, String first_name, String password, Gender gender, LocalDate birth_date) {
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
    public ArrayList<Group> getCreatedGroups() {
        return createdGroups;
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

    public LocalDate getBirth_date() {
        String pattern = "yyyy-MM-dd";
        return birth_date;
    }

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
    public Gender getGender() {
        return gender;
    }
    //=====================>Setters<===================//

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedGroups(Group createdGroup) {
        this.createdGroups.add(createdGroup);
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


    public void setBirth_date(LocalDate birth_date)
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

    public void setGroups(Group group)
    {
        this.groups.add(group);
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
    public Set<User> getFriends(){
        return FriendType.keySet();
    }
    public FriendType getFriendType(User user){
        return FriendType.get(user);
    }
   public ArrayList<User> getFriendsConversations(){
        return (ArrayList<User>) FriendChat.keySet();
   }
    public ArrayList<Conversation> getConversations(){
       return (ArrayList<Conversation>) FriendChat.values();
    }
    public Conversation getChosenUserConversation(User chosenUser){
        return FriendChat.get(chosenUser);
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
    public void acceptRequest(User user,FriendType friendType)
    {
        this.FriendType.put(user,friendType);
    }
    public ArrayList<User> getSentFriendRequests()
    {
        return sentFriendRequests;
    }
    public void SentFriendRequest(User user)
    {
        sentFriendRequests.add(user);
    }
    public void removeSentFriendRequest(User user)
    {
        for(User friendRequest: sentFriendRequests)
        {
            if(friendRequest.getId() == user.getId())
            {
                sentFriendRequests.remove(friendRequest);
                break;
            }
        }
    }
    public abstract ArrayList<Post> getTaggedPostsWithFriend(User friend);
    public ArrayList<User> getMutual(User user)
    {
        ArrayList<User> mutualFriends = new ArrayList<>();
        for (User friend : user.getFriends())
        {
            if (friend.getFriends().contains(user))
                mutualFriends.add(friend);
        }
        return mutualFriends;
    }
}

