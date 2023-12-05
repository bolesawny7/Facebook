package Services;
import Enums.ReactType;
import Models.Comment;
import Models.Post;
import Models.Users.User;
import Views.Dashboard;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentsService {

    Scanner input = new Scanner(System.in);
    Dashboard menu = new Dashboard();

    public void react(User user,Comment comment)
    {
        System.out.println("choose react => like, love, wow, haha, sad, angry, care");
        String reactType = input.next();
        ReactType react = ReactType.valueOf(reactType.toLowerCase());
        comment.addReact(user, react);
    }

    public void reply( User user,Comment comment)
    {
        System.out.println("content :");
        String ans = input.next();
        comment.addReply(user, ans);
    }
}
