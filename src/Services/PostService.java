package Services;

import Enums.Gender;
import Enums.ReactType;
import Models.Comment;
import Models.Post;
import Models.Users.Client;
import Models.Users.User;
import Views.Dashboard;
import Services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class PostService {
    Dashboard dashboard = new Dashboard();
    Scanner input = new Scanner(System.in);


    public void like(Post post,User currentUser)
    {
        System.out.println("choose react => like, love, wow, haha, sad, angry, care");
        String reactType = input.next();
        ReactType react = ReactType.valueOf(reactType.toLowerCase());
        post.addReact(currentUser, react);
    }

    public void share(User account, Post post)
    {
        account.sharePost(post);
    }

    public void writeComment(Post post, User user)
    {
        System.out.println("content :");
        String ans = input.next();
        post.addComment(user, ans);
    }

    public Comment getComments(Post post)
    {
        ArrayList<Comment> comments = post.getComments();

        for(int i = 0; i < comments.size(); i++){
            System.out.println( i + " - created by :" + comments.get(i).getUser());
            System.out.println("\n creation Date :" + comments.get(i).getCreationDate());
            System.out.println("\n" + comments.get(i).getText());
            System.out.println("Press 1-to choose comment\n2-to see next comment\n3-to return to postDashboard");
            String ans = input.next().toLowerCase();
            switch (ans) {
                case "1":
                    return comments.get(i);
                case "2":
                    continue;
                case "3":
                    return null;
            }
            System.out.println();
        }
        System.out.println("press any key to return to postServices");
        String ans = input.next();
        return null;
    }

    static ArrayList<Post> Timeline(ArrayList<Client> clients)
    {
        ArrayList<Post> timeline = new ArrayList<>();

        clients.forEach((User user) -> {
            ArrayList<Post> userPosts = user.getPosts();
            timeline.addAll(userPosts);
        });
        Collections.shuffle(timeline);

        return timeline;
    }
}
