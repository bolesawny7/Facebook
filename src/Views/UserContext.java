package Views;

import Models.Comment;
import Models.Group;
import Models.Post;
import Models.Users.User;
import Services.CommentsService;
import Services.ConversationService;
import Services.PostService;
import Services.UserService;

public class UserContext {
    private static User currentUser;
    private static Post currentPost;
    private static Comment currentComment;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static Post getSelectedPost() {
        return currentPost;
    }

    public static void setSelectedPost(Post post) {
        currentPost = post;
    }

    public static Comment getSelectedComment() {
        return currentComment;
    }

    public static void setSelectedComment(Comment comment) {
        currentComment = comment;
    }

}

