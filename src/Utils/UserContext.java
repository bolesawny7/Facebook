package Utils;

import Models.Comment;
import Models.Group;
import Models.Post;
import Models.Users.User;
import Services.CommentsService;
import Services.ConversationService;
import Services.PostService;
import Services.UserService;

/**
 * This is the main class of the application.
 * It initializes the services, dashboard, and input/output utility, then reads data from files.
 * It then enters a loop where it displays the appropriate dashboard based on the user's actions.
 * When the user chooses to exit, it writes data to files and breaks the loop.
 */
public class UserContext {
    private static User currentUser;
    private static Post currentPost;
    private static Comment currentComment;

    /**
     * This method writes data to files.
     * It saves users, friends, posts, and messages to files.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * This method sets the current user.
     *
     * @param user The user to be set as the current user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * This method retrieves the selected post.
     *
     * @return The selected post
     */
    public static Post getSelectedPost() {
        return currentPost;
    }

    /**
     * This method sets the selected post.
     *
     * @param post The post to be set as the selected post
     */
    public static void setSelectedPost(Post post) {
        currentPost = post;
    }

    /**
     * This method retrieves the selected comment.
     *
     * @return The selected comment
     */
    public static Comment getSelectedComment() {
        return currentComment;
    }

    /**
     * This method sets the selected comment.
     *
     * @param comment The comment to be set as the selected comment
     */
    public static void setSelectedComment(Comment comment) {
        currentComment = comment;
    }

}

