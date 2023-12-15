package Services;

import Enums.ReactType;
import Models.Comment;
import Models.Post;
import Models.Users.User;
import Views.Dashboard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides services related to comments.
 */
public class CommentsService {

    Scanner input = new Scanner(System.in);

    /**
     * This method allows a user to react to a comment.
     * It prompts the user to choose a reaction type, then adds the reaction to the comment.
     * If the reaction type is invalid, it will prompt the user to enter a valid reaction.
     *
     * @param user    The user who is reacting to the comment
     * @param comment The comment to which the user is reacting
     */
    public void react(User user, Comment comment) {
        try {
            System.out.println("Choose react => like, love, wow, haha, sad, angry, care");
            String reactType = input.next();
            ReactType react = ReactType.valueOf(reactType.toLowerCase());
            comment.addReact(user, react);
        } catch (IllegalArgumentException e) {
            System.out.println("Enter a valid react.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows a user to reply to a comment.
     * It prompts the user to enter the content of the reply, then adds the reply to the comment.
     *
     * @param user    The user who is replying to the comment
     * @param comment The comment to which the user is replying
     */
    public void reply(User user, Comment comment) {
        System.out.println("content :");
        String ans = input.next();
        comment.addReply(user, ans);
    }

}
