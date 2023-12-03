package Services;

import Enums.Gender;
import Models.Chat.Conversation;
import Models.Group;
import Models.Post;
import Models.React;
import Models.Users.Client;
import Models.Users.User;
import Views.Dashboard;


import java.util.*;

public class UserService {
    Dashboard dashboard = new Dashboard();
    private String email;
    private String password;
    public User account;
    private ArrayList<Client> clients = new ArrayList<Client>();
    public void login(UserService userService) {
        Scanner input = new Scanner(System.in);
        boolean isLogin = false;
        String ans;
        do {
            System.out.println("enter your email");
            String email = input.next();
            System.out.println("enter your password");
            String password = input.next();
            for (Client client : clients) {
                if (client.getEmail().equals(email) && client.getPassword().equals(password)) {
                    System.out.println("Login successful");
                    isLogin = true;
                    this.account=client;
                }
            }
            if (!isLogin) {
                System.out.println("Login failed");
            }
            System.out.println("Do You Wish to try again? (y/n)");
            ans = input.next().toLowerCase();
        } while (!isLogin && ans.equals("y"));
        if (ans.equals("n")) {
            dashboard.mainMenu(userService);
        }
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
        User user = new Client(email, last_name, first_name, password, gender, birthDate);
        dashboard.userDashboard();
    }
    public void seeFriends() {
        ArrayList<User> friendsName = account.getFriends();
        ArrayList<Enums.FriendType> friendsType = account.getFriendType();
//        friendsName.forEach((User friend) -> {
//            System.out.println(friend.getAccountName() + friend.getFriendType());
//
//        });

        for (int i = 0; i < friendsName.size(); i++){
            System.out.println("user name:" + friendsName.get(i).getAccountName() +" user type:" + friendsType.get(i) + "\n");
        }
    }
    public void seePosts() {
        ArrayList<Post> posts = account.getPosts();
        posts.forEach((Post post) -> {
            System.out.println("created date:" + post.getCreationDate() + "\n");
            System.out.println("privacy option:" + post.isPrivacyOption() + "\n");
            System.out.println("created by :" + post.getCreatedBy() + "\n");
            System.out.println(post.getContent());
        });
    }
    public void seeGroups() {
        ArrayList<Group> groups = account.getGroups();
        groups.forEach((Group group) -> {
            System.out.println("group name :" + group.getName() + "\n");
            System.out.println("group description:" + group.getDescription() + "\n");
            System.out.println("members of group:" + group.getMembers() + "\n");
            System.out.println("posts of group :" + group.getPosts() + "\n");
        });
    }
    public void seeConversations() {
            ArrayList<User> friendsName = account.getFriendsConversations();
            ArrayList<Conversation> friendsConversations = account.getConversations();

            for (int i = 0; i < friendsName.size(); i++){
                System.out.println( "user name: " + i + 1 + "-" + friendsName.get(i).getAccountName());
            }
    }

    public void getConversations(int i) {

        ArrayList<User> friendsName = account.getFriendsConversations();
        ArrayList<Conversation> friendsConversations = account.getConversations();

        System.out.println("user name: "  + friendsName.get(i).getAccountName() + "\n");
        System.out.println(friendsConversations.get(i));
    }

    public void sendFriendRequest() {

    }
    public void writePost() {

    }
    public void joinGroup() {

    }
    public void logout() {

    }
}
