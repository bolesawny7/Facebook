import Models.Users.Client;
import Services.*;
import Utils.InputOutput;
import Views.Dashboard;
import Utils.UserContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        CommentsService commentsService = new CommentsService();
        ConversationService conversationService = new ConversationService();
        Dashboard dashboard = new Dashboard();

        InputOutput io = new InputOutput();

        io.read();

        int ans = 1;
        while (true) {
            if (ans == 1) {
                ans = dashboard.mainMenu(userService);
            } else if (ans == 2) {
                ans = dashboard.userDashboard(userService, UserContext.getCurrentUser());
            } else if (ans == 3) {
                ans = dashboard.postDashboard(postService, UserContext.getCurrentUser(), UserContext.getSelectedPost());
            } else if (ans == 4) {
                ans = dashboard.commentsDashboard(commentsService, UserContext.getCurrentUser(), UserContext.getSelectedPost(), UserContext.getSelectedComment());
            } else if (ans == 5) {
                ans = dashboard.ConversationsDashboard(conversationService, UserContext.getCurrentUser());
            }
            if (ans == 6) {
                io.write();
                break;
            }
        }
    }
}



















