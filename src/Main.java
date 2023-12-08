import Models.Post;
import Models.React;
import Models.Users.User;
import Services.CommentsService;
import Services.ConversationService;
import Services.PostService;
import Services.UserService;
import Views.Dashboard;
import Views.UserContext;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        CommentsService commentsService = new CommentsService();
        ConversationService conversationService = new ConversationService();

        Dashboard dashboard = new Dashboard();

        int ans = 1;
        while(true){
            if(ans == 1)
                ans = dashboard.mainMenu(commentsService,postService,userService);

            else if(ans == 2)
                ans = dashboard.userDashboard(commentsService, postService, userService, UserContext.getCurrentUser());

            else if(ans == 3)
                ans = dashboard.postDashboard( commentsService, postService, userService, UserContext.getCurrentUser(), UserContext.getSelectedPost());

            else if(ans == 4)
                ans = dashboard.commentsDashboard(commentsService, postService, userService, UserContext.getCurrentUser(), UserContext.getSelectedPost(), UserContext.getSelectedComment());

            else if(ans == 5)
                ans = dashboard.ConversationsDashboard( commentsService, postService, conversationService, userService, UserContext.getCurrentUser());
        }


    }
}



















