package Services;

import Models.Users.Client;
import Models.Users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;

public class FileService {

    public void saveAllUsers(ArrayList<Client> users){
        //u cannot sign up twice in same session
        BufferedWriter writer;
        {
            try {
                writer = new BufferedWriter(new FileWriter("Users.txt"));
                for (Client user:users) {
                    ///constructor order
                    writer.write(user.getEmail()+" "+user.getLast_name()+" "+user.getFirst_name()+" "+user.getPassword()+" "+user.getGender()+" "+user.getBirth_date()+'\n');
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public  ArrayList<String> ReadAllUsers(){
        BufferedReader reader;
        String user;
        ArrayList<String> usersData = new ArrayList<>();
        {
            try {
                reader = new BufferedReader(new FileReader("Users.txt"));
                //read all lines till end of the file
                while((user=reader.readLine())!=null){
                    usersData.add(user);
//                    System.out.println(user);
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return usersData;
    }

    public void saveALlFriends(ArrayList<Client> users){
        BufferedWriter writer;
        for(Client user:users) {
            {
                Set <User>friends=user.getFriends();
                try {
                    writer = new BufferedWriter(new FileWriter("friends.txt"));
                    for (User friend : friends) {
                        writer.write(friend.getId()+' ');
                    }
                    writer.write('\n');
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public ArrayList<Client> readAllFriendsFromFile(ArrayList<Client> users) {
        ArrayList<Client> readUsers = users;

        try (BufferedReader reader = new BufferedReader(new FileReader("friends.txt"))) {
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] friendIds = line.split(" ");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}
