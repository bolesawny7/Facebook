package Services;

import Models.Users.Client;

import java.util.ArrayList;

public class UserServices {
    private String email;
    private String password;
    private ArrayList<Client> clients = new java.util.ArrayList<Client>();

    public void checkValues(String email,String password){
        for (Client client : clients ) {
            if(client.getEmail().equals(email) && client.getPassword().equals(password)){
                System.out.println("WP");
            }
        }
    }

}
