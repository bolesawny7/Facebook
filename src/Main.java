import Models.Users.Client;
import Services.*;
import Utils.InputOutput;
import Views.Dashboard;
import Utils.UserContext;

import java.util.ArrayList;

/**
 * This is the main class of the application.
 * It initializes the services, dashboard, and input/output utility, then reads data from files.
 * It then enters a loop where it displays the appropriate dashboard based on the user's actions.
 * When the user chooses to exit, it writes data to files and breaks the loop.
 */
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        CommentsService commentsService = new CommentsService();
        ConversationService conversationService = new ConversationService();

        // Initialize the dashboard
        Dashboard dashboard = new Dashboard();

        // Initialize the input/output utility
        InputOutput io = new InputOutput();

        // Read data from files
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

            // Write data to files and break the loop
            if (ans == 6) {
                io.write();
                break;
            }
        }
    }
}



















