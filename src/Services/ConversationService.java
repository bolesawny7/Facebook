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

/**
 * This class provides services related to conversations.
 */
public class ConversationService {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner input = new Scanner(System.in);

    /**
     * This method allows a user to see their conversations.
     * It prompts the user to choose a friend to chat with, then returns the chosen friend.
     * If the user does not want to chat, it will return null.
     *
     * @param user The user who is viewing their conversations
     * @return The chosen friend to chat with, or null if the user does not want to chat
     */
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
            chosenUser = UserService.clients.get(userId - 1);
            return chosenUser;
        } else {
            return null;
        }
    }

    /**
     * This method allows a user to see the content of a conversation.
     * It retrieves the conversation between the user and a chosen friend, then prints the messages in the conversation.
     * If the conversation is empty, it will initialize sending a message.
     *
     * @param user       The user who is viewing the conversation content
     * @param chosenUser The friend with whom the user is viewing the conversation
     */
    public void seeConversationContent(User user, User chosenUser) {
        Conversation conversation = user.getChosenUserConversation(chosenUser);
        if (conversation != null) {
            for (Message message : conversation.getMessages()) {
                System.out.println(message.getSender().getAccountName() + ":" + message.getContent());
            }
        } else {
            System.out.println("this conversation is empty");
            initializeSendMessage(user, chosenUser);
        }
    }

    /**
     * This method initializes a new conversation between the user and a chosen friend.
     *
     * @param user       The user who is initializing the conversation
     * @param chosenUser The friend with whom the user is initializing the conversation
     */
    public void initializeConv(User user, User chosenUser) {
        Conversation newConv = new Conversation(user, chosenUser);
        user.FriendChat.put(chosenUser, newConv);
    }

    /**
     * This method allows a user to send a message.
     * It prompts the user to choose a friend to chat with, then initializes sending a message to the chosen friend.
     *
     * @param user The user who is sending the message
     */
    public void sendMessage(User user) {
        Set<User> users = user.getFriends();
        for (User user1 : users) {
            System.out.println(user1.getId() + "- " + user1.getAccountName());
        }
        User chosenUser;
        System.out.println("Enter the id of the user you want to chat with");
        int userId = input.nextInt();
        chosenUser = UserService.clients.get(userId - 1);
        initializeSendMessage(user, chosenUser);
    }

    /**
     * This method initializes sending a message.
     * It prompts the user to enter the content of the message, then sends the message to the chosen friend.
     * If the conversation with the chosen friend does not exist, it will initialize a new conversation.
     *
     * @param user       The user who is initializing sending the message
     * @param chosenUser The friend to whom the user is sending the message
     */
    public void initializeSendMessage(User user, User chosenUser) {

        System.out.println("Enter the message u want to send:");
        String content;
        try {
            content = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
        LocalDateTime now = LocalDateTime.now();
        Message newMessage = new Message(content, now, user, chosenUser);
        if (user.FriendChat.get(chosenUser) != null) {
            user.FriendChat.get(chosenUser).sendMessage(newMessage);
            System.out.println("empty");
        } else {
            initializeConv(chosenUser, user);
            initializeConv(user, chosenUser);
            user.FriendChat.get(chosenUser).sendMessage(newMessage);
            System.out.println("sent");
        }
        Conversation newConv = new Conversation(user, chosenUser);
        newConv.setMessages(user.FriendChat.get(chosenUser).getMessages());
        chosenUser.FriendChat.put(user, newConv);

    }

}
