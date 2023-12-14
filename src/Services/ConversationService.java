package Services;

import Models.Chat.Conversation;
import Models.Users.User;
import Views.Dashboard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class ConversationService {
    private ArrayList<Conversation> conversations = new ArrayList<Conversation>();
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
            chosenUser = UserService.clients.get(userId);
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
