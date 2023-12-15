package Services;
import Enums.ReactType;
import Models.Comment;
import Models.Post;
import Models.Users.User;
import Views.Dashboard;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentsService implements Interfaces.ReactInterface {

    Scanner input = new Scanner(System.in);

    public void react(User user,Comment comment)
    {
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

    public void reply( User user,Comment comment)
    {
        System.out.println("content :");
        String ans = input.next();
        comment.addReply(user, ans);
    }

}
