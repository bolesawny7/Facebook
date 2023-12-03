import Services.CommentsService;
import Services.ConversationService;
import Services.PostService;
import Services.UserService;
import Views.Dashboard;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        CommentsService commentService = new CommentsService();
        ConversationService conversationService = new ConversationService();

        Dashboard dashboard = new Dashboard();
        dashboard.mainMenu(userService);

    }
}




















