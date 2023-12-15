package Services;

import Enums.FriendType;
import Enums.Gender;
import Enums.PrivacyOption;
import Models.Group;
import Models.Post;
import Models.Users.Client;
import Models.Users.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static Utils.UserContext.setCurrentUser;

public class UserService {
    FileService fileService = new FileService();//should be refactored ( boules should do it )
    Scanner input = new Scanner(System.in);
    public User currentUser;
    public static ArrayList<Client> clients = new ArrayList<Client>();
    static ArrayList<Group> groups = new ArrayList<Group>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
        setCurrentUser(currentUser);
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
        System.out.println("enter your birth_date => yyyy-mm-dd");
        String date = input.next();
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate birthDate = LocalDate.parse(date, formatter);
        Client user = new Client(email, last_name, first_name, password, gender, birthDate);
        clients.add(user);
        saveUsers(clients);
    }

    public void seeFriends() {
        Set<User> friends = currentUser.getFriends();
        for (User friend : friends) {
            System.out.println(friend.getAccountName() + '\t' + currentUser.getFriendType(friend));
        }
        System.out.println("press y or any key to return to UserDashboard ");
        String ans = input.next();
    }

    public Post seePosts() {
        ArrayList<Post> posts = currentUser.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            System.out.println("created date:" + posts.get(i).getCreationDate());
            System.out.println("privacy option:" + posts.get(i).isPrivacyOption());
            System.out.print("created by: " + posts.get(i).getCreatedBy().getAccountName() + " ");
            System.out.print("Tagged: ");
            posts.get(i).getTaggedUsers().forEach((User user) -> {
                System.out.print(user.getAccountName() + ",");
            });
            System.out.println();
            System.out.println(posts.get(i).getContent());

            System.out.println("Press 1-to choose post\n2-to see next post\n3-to return to UserDashboard");
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
        System.out.println("press any key to return to user services");
        String ans = input.next();
        return null;
    }

    public Post seeTimeline() {
        ArrayList<Post> timeline = PostService.Timeline(clients);
        for (int i = 0; i < timeline.size(); i++) {
            System.out.println("created date:" + timeline.get(i).getCreationDate() + "\n");
            System.out.println("privacy option:" + timeline.get(i).isPrivacyOption() + "\n");
            System.out.println("created by :" + timeline.get(i).getCreatedBy().getAccountName() + "\n");
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


    public void getFriendRequests(User currentUser) {
        for (int i = 0; i < currentUser.ReceivedFriendRequests.size(); i++) {
            System.out.println(currentUser.ReceivedFriendRequests.get(i).getAccountName());
            System.out.println("1-accept Request \n2-reject Request \n3-pass");
            String y = input.next();
            switch (y) {
                case "1":
                    System.out.println("restricted or regular");
                    String FriendTypeInput = input.next();
                    FriendType friendType = Enums.FriendType.valueOf(FriendTypeInput.toLowerCase());
                    currentUser.acceptRequest(currentUser.ReceivedFriendRequests.get(i), friendType);
                    currentUser.ReceivedFriendRequests.get(i).acceptRequest(currentUser, FriendType.regular);
                    currentUser.ReceivedFriendRequests.get(i).removeSentFriendRequest(currentUser);
                    currentUser.ReceivedFriendRequests.remove(currentUser.ReceivedFriendRequests.get(i));
                    break;
                case "2":
                    currentUser.ReceivedFriendRequests.remove(currentUser.ReceivedFriendRequests.get(i));
                    break;
                default:
                    break;
            }
        }
    }

    public void getSentFriendRequests(User currentUser) {
        for (User user : currentUser.sentFriendRequests) {
            System.out.println(user.getAccountName() + "\n");
        }
        System.out.println("press any key to return to user services");
        String ans = input.next();
    }

    public ArrayList<User> userSearch(String input) {
        ArrayList<User> possibleUsers = new ArrayList<User>();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getAccountName().contains(input) && !clients.get(i).getAccountName().equals(currentUser.getAccountName())) {
                possibleUsers.add(clients.get(i));
            }
        }
        return possibleUsers;
    }

    public void sendFriendRequest(User currentUser) {
        Set<User> currentUserFriends = currentUser.getFriends();
        ArrayList<User> sentFriendRequests = currentUser.getSentFriendRequests();
        ArrayList<User> ReceivedFriendRequests = currentUser.ReceivedFriendRequests;

        boolean notAllowedFriend;
        for (int i = 0; i < clients.size(); i++) {
            notAllowedFriend = false;

            // It's not allowed to send friend request to yourself
            if (currentUser.getId() == clients.get(i).getId())
                continue;

            // It's not allowed to send friend request to your friends
            for (User friend : currentUserFriends) {
                if (friend.getId() == clients.get(i).getId()) {
                    notAllowedFriend = true;
                    break;
                }
            }

            // It's not allowed to send a request to a friend you already sent a request to
            for (User friend : sentFriendRequests) {
                if (friend.getId() == clients.get(i).getId()) {
                    notAllowedFriend = true;
                    break;
                }
            }
            // It's not allowed to send a request to a friend he already sent a request to you
            for (User friend : ReceivedFriendRequests) {
                if (friend.getId() == clients.get(i).getId()) {
                    notAllowedFriend = true;
                    break;
                }
            }

            if (notAllowedFriend) {
                continue;
            }
            System.out.println(clients.get(i).getAccountName());
            System.out.println("1-Send Request \n2-pass");
            String y = input.next();
            switch (y) {
                case "1":
                    System.out.println("Request sent");
                    clients.get(i).ReceivedFriendRequests.add(currentUser);
                    currentUser.SentFriendRequest(clients.get(i));
                    break;
                default:
                    break;
            }
        }
    }


    public Post writePost(User currentUser) {
        System.out.println("Select Privacy option (friends ,public)");
        String privacyOptionInput = input.next().toUpperCase();
        PrivacyOption privacyOption = Enums.PrivacyOption.valueOf(privacyOptionInput);
        System.out.println("Enter the post content");
        String postContent = null;
        try {
            postContent = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime now = LocalDateTime.now();
        Post newPost = new Post(currentUser, now, privacyOption, postContent);
        String choice;
        do {
            System.out.println("Do you want to tag a friend? y/n");
            choice = input.next().toLowerCase();
            if (choice.equals("y")) {
                System.out.println("Enter Account name");
                String userName = input.next();
                ArrayList<User> users = userSearch(userName);
                for (int i = 0; i < users.size(); i++) {
                    System.out.println(i + 1 + "-" + users.get(i).getAccountName());
                }
                int index;
                do {
                    System.out.println("choose the id of the user you want to tag");
                    index = input.nextInt();
                    if (index <= users.size() && index >= 1) {
                        newPost.setTaggedUser(users.get(index - 1));
                    } else {
                        System.out.println("please enter a valid id");
                    }
                } while (index > users.size() && index < 1);
            }
        } while (choice.equals("y"));
        currentUser.posts.add(newPost);
        return newPost;
    }


    public ArrayList<Group> groupSearch(String input) {
        ArrayList<Group> possibleGroups = new ArrayList<Group>();
        for (int i = 0; i < clients.size(); i++) {
            if (groups.get(i).getName().contains(input)) {
                possibleGroups.add(groups.get(i));
            }
        }
        return possibleGroups;
    }

    public void joinGroup() {
        String groupName = input.next();

        ArrayList<Group> groups = groupSearch(groupName);
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(i + 1 + "-" + groups.get(i).getName());
        }
        int index;
        do {
            System.out.println("choose the id of the group you want to join");
            index = input.nextInt();
            if (index <= groups.size() && index >= 1) {
                currentUser.setGroups(groups.get(index - 1));
            } else {
                System.out.println("please enter a valid id");
            }
        } while (index > groups.size() && index < 1);
    }

    public void createGroup() {
        System.out.println("Enter Group Name");
        String groupName = input.next();
        System.out.println("Enter the group description");
        String groupDescription = input.next();
        Group newGroup = new Group(groupName, groupDescription);
        newGroup.setAdmins(currentUser);
        currentUser.setCreatedGroups(newGroup);
    }

    public void getFriendship(User currentUser) {
        //get the user
        System.out.println("Enter Account name");
        String userName = input.next();
        ArrayList<User> users = userSearch(userName);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + 1 + "-" + users.get(i).getAccountName());
        }
        System.out.println("Enter the number of the user you want to get friendship with");
        int index = input.nextInt();
        User friend = users.get(index - 1);

        //get the friendship
        ArrayList<Post> commonPosts = new ArrayList<>();
        currentUser.getTaggedPostsWithFriend(friend).forEach((Post post) -> {
            commonPosts.add(post);
        });
        friend.getTaggedPostsWithFriend(currentUser).forEach((Post post) -> {
            commonPosts.add(post);
        });
        for (int i = 0; i < commonPosts.size(); i++) {
            System.out.println(commonPosts.size());
            System.out.println("created date:" + commonPosts.get(i).getCreationDate() + "\n");
            System.out.println("privacy option:" + commonPosts.get(i).isPrivacyOption() + "\n");
            System.out.println("created by :" + commonPosts.get(i).getCreatedBy().getAccountName() + "\n");
            System.out.println(commonPosts.get(i).getContent());
            System.out.println("Press any key to return to UserDashboard");
            String ans = input.next().toLowerCase();
        }
    }

    public void getMutualFriends(User currentUser) {
        //get the user
        System.out.println("Enter Account name");
        String userName = input.next();
        ArrayList<User> users = userSearch(userName);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + 1 + "-" + users.get(i).getAccountName());
        }
        System.out.println("Enter the number of the user you want to get mutual friends with");
        int index = input.nextInt();
        User friend = users.get(index - 1);

        //get the mutual
        ArrayList<User> commonFriends = new ArrayList<>();
        currentUser.getMutual(friend).forEach((User commonfriend) -> {
            commonFriends.add(commonfriend);
        });
        for (int i = 0; i < commonFriends.size(); i++) {
            System.out.println(commonFriends.get(i).getAccountName());
        }
        System.out.println("Press any key to return to UserDashboard");
        String ans = input.next().toLowerCase();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void readUsers() {
        ArrayList<String> usersData = fileService.ReadAllUsers();
        String pattern = "yyyy-MM-dd";

        for (int i = 0; i < usersData.size(); i++) {
            String[] user = usersData.get(i).split(" ");
            //to get date pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate birthDate = LocalDate.parse(user[5], formatter);
            //to get gender
            Gender gender = Gender.valueOf(user[4].toLowerCase());
            Client newUser = new Client(user[0], user[1], user[2], user[3], gender, birthDate);
            clients.add(newUser);
        }
    }

    public void saveUsers(ArrayList<Client> clients) {
        fileService.saveAllUsers(clients);
    }
}










