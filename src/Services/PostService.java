package Services;

import Enums.Gender;
import Enums.ReactType;
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

    public User currentUser;

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public void like(Post post)
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
