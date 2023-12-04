package Services;

import Enums.FriendType;
import Enums.Gender;
import Enums.PrivacyOption;
import Models.Chat.Conversation;
import Models.Group;
import Models.Post;
import Models.Users.Client;
import Models.Users.User;
import Views.Dashboard;
import Services.PostService;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserService {
    Scanner input = new Scanner(System.in);
    Dashboard dashboard = new Dashboard();
    private String email;
    private String password;
    public User currentUser;
    static ArrayList<Client> clients = new ArrayList<Client>();

    public User login() {
        boolean isLogin = false;
        String ans = new String();
        do {
            System.out.println("enter your email");
            String email = input.next();
            System.out.println("enter your password");
            String password = input.next();
            for (Client client : clients) {
                if (client.getEmail().equals(email) && client.getPassword().equals(password)) {
                    System.out.println("Login successful");
                    isLogin = true;
                    currentUser = client;
                }
            }
            if (!isLogin) {
                System.out.println("Login failed");
                System.out.println("Do You Wish to try again? (y/n)");
                ans = input.next().toLowerCase();
            }
        } while (!isLogin && ans.equals("y"));
        if (ans.equals("n")) {
            return null;
        }
        return currentUser;
    }

    public void signUp() {
        Scanner userData = new Scanner(System.in);
        System.out.println("enter your email");
        String email = userData.next();
        System.out.println("enter your first name");
        String first_name = userData.next();
        System.out.println("enter your last name");
        String last_name = userData.next();
        System.out.println("enter your password");
        String password = userData.next();
        System.out.println("enter your gender ( male , female )");
        String genderInput = userData.next();
        Gender gender = Gender.valueOf(genderInput.toLowerCase());
        System.out.println("enter your birth_date => year, month, day");
        int year = userData.nextInt();
        int month = userData.nextInt();
        int day = userData.nextInt();
        Date birthDate = new Date(year, month, day);
        Client user = new Client(email, last_name, first_name, password, gender, birthDate);
        clients.add(user);
    }

    public void seeFriends() {

        ArrayList<User> friendsName = currentUser.getFriends();
        ArrayList<Enums.FriendType> friendsType = currentUser.getFriendType();
        for (int i = 0; i < friendsName.size(); i++) {
            System.out.println("user name:" + friendsName.get(i).getAccountName() + " user type:" + friendsType.get(i) + "\n");
        }
        System.out.println("press y or any key to return to UserDashboard ");
        String ans = input.next();
    }

    public Post seePosts()
    {
        ArrayList<Post> posts = currentUser.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            System.out.println("created date:" + posts.get(i).getCreationDate() + "\n");
            System.out.println("privacy option:" + posts.get(i).isPrivacyOption() + "\n");
            System.out.println("created by :" + posts.get(i).getCreatedBy() + "\n");
            System.out.println(posts.get(i).getContent());
            System.out.println("Press 1 to choose post, 2 to see next post, 3 to return to UserDashboard");
            String ans = input.next().toLowerCase();
            switch (ans) {
                case "1":
                    return posts.get(i);
                case "2":
                    continue;
                case "3":
                    return null;
            }
            System.out.println();
        }
        return null;
    }

    public Post seeTimeline()
    {
        ArrayList <Post> timeline =  PostService.Timeline(clients);

        for (int i = 0; i < timeline.size(); i++) {
            System.out.println("created date:" + timeline.get(i).getCreationDate() + "\n");
            System.out.println("privacy option:" + timeline.get(i).isPrivacyOption() + "\n");
            System.out.println("created by :" + timeline.get(i).getCreatedBy() + "\n");
            System.out.println(timeline.get(i).getContent());
            System.out.println("Press 1 to choose post, 2 to see next post, 3 to return to UserDashboard");
            String ans = input.next().toLowerCase();
            switch (ans) {
                case "1":
                    return timeline.get(i);
                case "2":
                    continue;
                case "3":
                    return null;
            }
            System.out.println();
        }
        return null;
    }

    public void seeGroups() {
        ArrayList<Group> groups = currentUser.getGroups();
        groups.forEach((Group group) -> {
            System.out.println("group name :" + group.getName() + "\n");
            System.out.println("group description:" + group.getDescription() + "\n");
            System.out.println("members of group:" + group.getMembers() + "\n");
            System.out.println("posts of group :" + group.getPosts() + "\n");
        });
    }

    public void seeConversations() {
        ArrayList<User> friendsName = currentUser.getFriendsConversations();
        ArrayList<Conversation> friendsConversations = currentUser.getConversations();

        for (int i = 0; i < friendsName.size(); i++) {
            System.out.println("user name: " + i + 1 + "-" + friendsName.get(i).getAccountName());
        }
    }

    public void getConversations(int i) {

        ArrayList<User> friendsName = currentUser.getFriendsConversations();
        ArrayList<Conversation> friendsConversations = currentUser.getConversations();

        System.out.println("user name: " + friendsName.get(i).getAccountName() + "\n");
        System.out.println(friendsConversations.get(i));
    }

    public void getFriendRequests(User currentUser) {
        for (int i = 0; i < currentUser.friendRequest.size(); i++) {
            System.out.println(currentUser.friendRequest.get(i).getAccountName());
            System.out.println("1-accept Request \n2-reject Request \n3-pass");
            String y = input.next();
            switch (y) {
                case "1":
                    System.out.println("restricted or regular");
                    String FriendTypeInput = input.next();
                    FriendType friendType = Enums.FriendType.valueOf(FriendTypeInput.toLowerCase());
                    currentUser.acceptRequest(currentUser.friendRequest.get(i), friendType);
                    currentUser.spliceArray(currentUser.friendRequest.get(i));
                    break;
                case "2":
                    currentUser.spliceArray(currentUser.friendRequest.get(i));
                    break;
                default:
                    break;
            }
        }
    }

    public ArrayList<User> search(String input){
        ArrayList <User> possibleUsers=new ArrayList<User>();
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getAccountName().contains(input)){
                possibleUsers.add(clients.get(i));
            }
        }
        return possibleUsers;
    }
    public void sendFriendRequest(User currentUser) {
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(clients.get(i).getAccountName());
            System.out.println("1-Send Request \n2-pass");
            String y = input.next();
            switch (y) {
                case "1":
                    System.out.println("Request sent");
                    clients.get(i).friendRequest.add(currentUser);
                    break;
                default:
                    break;
            }
        }
    }


    public void writePost(User currentUser) {
        System.out.println("Select Privacy option (friends ,public)");
        String privacyOptionInput = input.next().toUpperCase();
        PrivacyOption privacyOption = Enums.PrivacyOption.valueOf(privacyOptionInput);
        System.out.println("Enter the post content");
        String postContent=input.next();
        LocalDateTime now = LocalDateTime.now();
        Post newPost=new Post(currentUser, now ,privacyOption,postContent);
        String choice;
        do {
            System.out.println("Do you want to tag a friend? y/n");
            choice=input.next().toLowerCase();
            newPost.setTaggedUser(currentUser);
        }while (choice.equals("y"));
//        Post(User createdBy, Date creationDate, privacyOption , String content)
    }

    public void joinGroup() {

    }

}
