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
    public User currentUser;
    private ArrayList<Client> clients = new ArrayList<Client>();

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
                    this.currentUser = client;
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
    }

    public Post seePosts() {
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

    public void getFriendRequests(User user) {

        for (int i = 0; i < user.friendRequest.size(); i++) {

            System.out.println(user.friendRequest.get(i).getAccountName());
            System.out.println("1-accept Request \n2-reject Request \n3-pass");
            String y = input.next();
            switch (y) {
                case "1":
                    System.out.println("restricted or regular");
                    String FriendTypeInput = input.next();
                    FriendType friendType = Enums.FriendType.valueOf(FriendTypeInput.toLowerCase());
                    user.acceptRequest(user.friendRequest.get(i), friendType);
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

}
