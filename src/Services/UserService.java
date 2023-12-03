package Services;

import Enums.FriendType;
import Enums.Gender;
import Models.Chat.Conversation;
import Models.Comment;
import Models.Group;
import Models.Post;
import Models.React;
import Models.Users.Client;
import Models.Users.User;
import Views.Dashboard;


import java.util.*;

public class UserService {
    Scanner input = new Scanner(System.in);
    Dashboard dashboard = new Dashboard();
    private String email;
    private String password;
    public User account;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public void login(CommentsService commentsService,PostService postService,UserService userService) {
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
                    this.account = client;
                }
            }
            if (!isLogin) {
                System.out.println("Login failed");
                System.out.println("Do You Wish to try again? (y/n)");
                ans = input.next().toLowerCase();
            }
        } while (!isLogin && ans.equals("y"));
        if (ans.equals("n")) {
            dashboard.mainMenu(commentsService, postService, userService);
        }
        dashboard.userDashboard(commentsService,postService,userService, account);
    }

    public void signUp(CommentsService commentsService,PostService postService,UserService userService) {
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
        dashboard.mainMenu(commentsService,postService,userService);
    }

    public void seeFriends() {
        ArrayList<User> friendsName = account.getFriends();
        ArrayList<Enums.FriendType> friendsType = account.getFriendType();
//        friendsName.forEach((User friend) -> {
//            System.out.println(friend.getAccountName() + friend.getFriendType());
//
//        });

        for (int i = 0; i < friendsName.size(); i++) {
            System.out.println("user name:" + friendsName.get(i).getAccountName() + " user type:" + friendsType.get(i) + "\n");
        }
    }

    public void seePosts(CommentsService commentsService, PostService postService,UserService userService) {
        ArrayList<Post> posts = account.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            System.out.println("created date:" + posts.get(i).getCreationDate() + "\n");
            System.out.println("privacy option:" + posts.get(i).isPrivacyOption() + "\n");
            System.out.println("created by :" + posts.get(i).getCreatedBy() + "\n");
            System.out.println(posts.get(i).getContent());
            System.out.println("Press 1 to choose post, 2 to pass, 3 to return to UserDashboard");
            int ans = input.nextInt();
            switch (ans) {
                case 1:
                    dashboard.postDashboard(commentsService, postService, userService,account, posts.get(i));
            }
            System.out.println();

        }

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

        for (int i = 0; i < friendsName.size(); i++) {
            System.out.println("user name: " + i + 1 + "-" + friendsName.get(i).getAccountName());
        }
    }

    public void getConversations(int i) {

        ArrayList<User> friendsName = account.getFriendsConversations();
        ArrayList<Conversation> friendsConversations = account.getConversations();

        System.out.println("user name: " + friendsName.get(i).getAccountName() + "\n");
        System.out.println(friendsConversations.get(i));
    }
    public void  getFriendRequests(User user) {

        for (int i = 0; i < user.friendRequest.size(); i++) {

            System.out.println(user.friendRequest.get(i).getAccountName());
            System.out.println("1-accept Request \n2-reject Request \n3-pass");
            String y =input.next();
            switch (y){
                case "1":
                    System.out.println("restricted or regular");
                    String FriendTypeInput = input.next();
                    FriendType friendType = Enums.FriendType.valueOf(FriendTypeInput.toLowerCase());
                    user.acceptRequest(user.friendRequest.get(i),friendType);
                    user.spliceArray(user.friendRequest.get(i));
                case "2":
                    user.spliceArray(user.friendRequest.get(i));
                case "3":
                    continue;
            }

        }
    }
    public void sendFriendRequest(User user) {

    }

    public void writePost() {

    }

    public void joinGroup() {

    }

    public void logout(CommentsService commentsService, PostService postService, UserService userService) {
        dashboard.mainMenu(commentsService,postService,userService);
    }
}
