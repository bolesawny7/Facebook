package Services;

import Models.Chat.Conversation;
import Models.Users.User;
import Views.Dashboard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class ConversationService {
    private ArrayList<Conversation> conversations = new ArrayList<Conversation>();
    Dashboard dashboard = new Dashboard();
    Scanner input = new Scanner(System.in);
    public User seeConversations(User user) {
        ArrayList<User> users = user.getFriendsConversations();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + ' ' + users.get(i).getAccountName());
        }
        User chosenUser;
        System.out.println("Do you want to chat? y/n");
        String answer = input.next();
        if (answer.equals("y")) {
            System.out.println("Enter the id of the user you want to chat with");
            int userId = input.nextInt();
            chosenUser = users.get(userId);
            return chosenUser;
        } else {
            return null;
        }
    }

    public String seeConversationContent(User user, User chosenUser) {
        Conversation conversation = user.getChosenUserConversation(chosenUser);
        conversation.getMessages();
        return sendMessage();
    }

    public String sendMessage() {
        return "message sent";
    }

}
