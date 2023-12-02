package Services;

import Enums.Gender;
import Models.Users.Client;
import Models.Users.User;
import Views.Dashboard;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserService {
    Dashboard dashboard = new Dashboard();
    private String email;
    private String password;
    private ArrayList<Client> clients = new ArrayList<Client>();
    public void login() {
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
                }
            }
            if (!isLogin) {
                System.out.println("Login failed");
            }
            System.out.println("Do You Wish to try again? (y/n)");
            ans = input.next().toLowerCase();
        } while (!isLogin && ans.equals("y"));
        if (ans.equals("n")) {
            dashboard.mainMenu();
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
        System.out.println("enter your gender 0 => male, 1 => female");
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
    }
    public void seePosts() {

    }
    public void seeGroups() {

    }
    public void seeConversations() {

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
