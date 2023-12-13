package Services;

import Enums.FriendType;
import Models.Users.Client;
import Models.Users.User;
import Views.UserContext;

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
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("friends.txt"));


            for (Client user : users) {
                {
                    Set<User> friends = user.getFriends();
                    writer.write(user.getId() + " ");
                    for (User friend : friends) {
                        writer.write(friend.getId() + " " + user.getFriendType(friend)+" ");
                    }
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readUserFrinends(ArrayList <Client> clients) {
        String[] friendIds = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("friends.txt"));
            String line;
            for(Client user:clients) {
                int id = user.getId();
                while ((line = reader.readLine()) != null) {
                    friendIds = line.split(" ");
                    int thisId = Integer.parseInt(friendIds[0]);
                    if (thisId == id) {
                        System.out.println(thisId);
                        break;
                    }
                }
                for(int i=1;i<friendIds.length;i++){
                    System.out.println(friendIds[i]);
                }
                System.out.println();
                for(int i=1;i<friendIds.length;i+=2){
                    int friendId=Integer.parseInt(friendIds[i]);
                    FriendType friendType = FriendType.valueOf(friendIds[i+1].toLowerCase());
                   clients.get(id-1).setFriendType(clients.get(friendId-1),friendType);
                }
            }

            reader.close();
        } catch (IOException e) {
            return;
        }
    }
}
