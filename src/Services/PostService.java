package Services;

import Models.Post;
import Models.Users.User;
import Views.Dashboard;

import java.util.ArrayList;

public class PostService {
    Dashboard dashboard = new Dashboard();

    private ArrayList<Post> posts = new ArrayList<Post>();

    public void like() {

    }

    public void comment(CommentsService commentsService, PostService postService, UserService userService, User user, Post post) {
        dashboard.commentsDashboard(commentsService, postService, userService, user, post);
    }

    public void share(User account, Post post) {
        account.sharePost(post);
    }

}
