package Services;

import Enums.Gender;
import Models.Users.Client;
import Models.Users.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserService {
    private String email;
    private String password;
    private ArrayList<Client> clients = new java.util.ArrayList<Client>();

    public void login(){
        Scanner userData=new Scanner(System.in);
        String email =userData.next();
        String password =userData.next();
        boolean isLogin = false;
        for (Client client : clients ) {
            if(client.getEmail().equals(email) && client.getPassword().equals(password)){
                System.out.println("Login successfully");
                isLogin = true;
            }
        }
        if(!isLogin){
            System.out.println("Login failed");
        }
    }
    public void signUp(){
        Scanner userData=new Scanner(System.in);
        System.out.println("enter your email");
        String email =userData.next();
        System.out.println("enter your first name");
        String first_name =userData.next();
        System.out.println("enter your last name");
        String last_name =userData.next();
        System.out.println("enter your password");
        String password =userData.next();
        System.out.println("enter your gender 0 => male, 1 => female");
        String genderInput = userData.next();
        Gender gender = Gender.valueOf(genderInput.toLowerCase());
        System.out.println("enter your birth_date => year, month, day");
        int year =userData.nextInt();
        int month =userData.nextInt();
        int day =userData.nextInt();
        Date birthDate = new Date(year,month,day);
        User user = new Client( email,  last_name,  first_name,  password,  gender,  birthDate);
    }

}
