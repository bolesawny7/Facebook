package Services;

import Models.Chat.Conversation;
import Models.Chat.Message;
import Models.Users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ConversationService {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner input = new Scanner(System.in);
    public User seeConversations(User user) {
        Set<User> users = user.getFriends();
        for (User user1 : users) {
            System.out.println(user1.getId() + "- " + user1.getAccountName());
        }
        User chosenUser;
        System.out.println("Do you want to chat? y/n");
        String answer = input.next();

        if (answer.equals("y")) {
            System.out.println("Enter the id of the user you want to chat with");
            int userId = input.nextInt();
            chosenUser = UserService.clients.get(userId-1);
            return chosenUser;
        } else {
            return null;
        }
    }

    public void seeConversationContent(User user, User chosenUser) {
        Conversation conversation = user.getChosenUserConversation(chosenUser);
        if(conversation!=null) {
            for (Message message : conversation.getMessages()) {
                System.out.println(message.getSender().getAccountName() + ":" + message.getContent());
            }
        }
        else{
            System.out.println("this conversation is empty");
            initializeSendMessage(user,chosenUser);
        }
    }
    public void initializeConv(User user,User chosenUser) {
        Conversation newConv = new Conversation(user, chosenUser);
        user.FriendChat.put(chosenUser , newConv);
    }

    public void sendMessage(User user) {
        Set<User> users = user.getFriends();
        for (User user1 : users) {
            System.out.println(user1.getId() + "- " + user1.getAccountName());
        }
        User chosenUser;
        System.out.println("Enter the id of the user you want to chat with");
        int userId = input.nextInt();
        chosenUser = UserService.clients.get(userId-1);
        initializeSendMessage(user,chosenUser);
    }
    public void initializeSendMessage(User user,User chosenUser){

        System.out.println("Enter the message u want to send:");
        String content;
        try {
            content= reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
        LocalDateTime now = LocalDateTime.now();
        Message newMessage= new Message(content,now,user,chosenUser);
        if(user.FriendChat.get(chosenUser)!=null) {
            user.FriendChat.get(chosenUser).sendMessage(newMessage);
            System.out.println("empty");
        }
        else{
            initializeConv(chosenUser,user);
            initializeConv(user,chosenUser);
            user.FriendChat.get(chosenUser).sendMessage(newMessage);
            System.out.println("sent");
        }
        Conversation newConv = new Conversation(user, chosenUser);
        newConv.setMessages(user.FriendChat.get(chosenUser).getMessages());
        chosenUser.FriendChat.put(user,newConv);

    }

}
