package Services;

import Enums.Gender;
import Models.Users.Client;
import Models.Users.User;

import java.util.ArrayList;
import java.util.Date;

public class UserServices {
    private String email;
    private String password;
    private ArrayList<Client> clients = new java.util.ArrayList<Client>();

    public void login(String email,String password){
        for (Client client : clients ) {
            if(client.getEmail().equals(email) && client.getPassword().equals(password)){
                System.out.println("WP");
            }
        }
    }

    public void signUp(String email, String last_name, String first_name, String password, Gender gender, Date birth_date){
        User user = new Client( email,  last_name,  first_name,  password,  gender,  birth_date);
    }
}
