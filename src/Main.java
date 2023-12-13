import Models.Post;
import Models.React;
import Models.Users.Client;
import Models.Users.User;
import Services.*;
import Views.Dashboard;
import Views.UserContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        CommentsService commentsService = new CommentsService();
        ConversationService conversationService = new ConversationService();
        FileService fileService=new FileService();
        ArrayList<Client> clients=userService.getClients();

        Dashboard dashboard = new Dashboard();

        int ans = 1;
        while(true){
            if(ans == 1) {
                ans = dashboard.mainMenu(userService);
            }

            else if(ans == 2) {
                ans = dashboard.userDashboard(userService, UserContext.getCurrentUser());
            }

            else if(ans == 3) {
                ans = dashboard.postDashboard( postService, UserContext.getCurrentUser(), UserContext.getSelectedPost());
            }

            else if(ans == 4) {
                ans = dashboard.commentsDashboard(commentsService, UserContext.getCurrentUser(), UserContext.getSelectedPost(), UserContext.getSelectedComment());
            }

            else if(ans == 5) {
                ans = dashboard.ConversationsDashboard( conversationService, UserContext.getCurrentUser());
            }
            if (ans==6){
                fileService.saveAllUsers(clients);
                fileService.saveALlFriends(clients);
                break;
            }
        }


    }
}



















