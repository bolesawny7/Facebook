package Services;

import Enums.FriendType;
import Enums.Gender;
import Enums.PrivacyOption;
import Enums.ReactType;
import Models.Comment;
import Models.Post;
import Models.React;
import Models.Users.Client;
import Models.Users.User;
import Utils.UserContext;
import Views.Dashboard;
import Services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class PostService {
    Scanner input = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));



    public void like(Post post,User currentUser)
    {
        try {
            System.out.println("choose react => like, love, wow, haha, sad, angry, care");
            String reactType = input.next();
            ReactType react = ReactType.valueOf(reactType.toLowerCase());
            post.addReact(currentUser, react);
        } catch (IllegalArgumentException e) {
            System.out.println("Enter a valid react.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void share(User account, Post post)
    {
        account.sharePost(post);
    }

    public void writeComment(Post post, User user)
    {
        System.out.println("content :");
        String ans = null;
        try {
            ans = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        post.addComment(user, ans);
    }
    public ArrayList<React> getReacts(Post post) {
        ArrayList<React> reacts = post.getReacts();

        try{
            for (int i = 0; i < reacts.size(); i++) {
                System.out.println(i + 1 + "\n- created by: " + reacts.get(i).getUser().getAccountName());
                System.out.println("\n -React: " + reacts.get(i).getReact());
                System.out.println("1-to choose react\n2-to see next react\n3-to return to postDashboard");
                String ans = input.next().toLowerCase();
                switch (ans) {
                    case "1":
                        return reacts;
                    case "2":
                        continue;
                    case "3":
                        return null;
                }
                System.out.println();
            }

        } catch (NullPointerException e){
            e.printStackTrace();
        }
        System.out.println("press any key to return to postServices");
        String ans = input.next();
        return null;
    }
    public Comment getComments(Post post)
    {
        ArrayList<Comment> comments = post.getComments();
        try{
            for(int i = 0; i < comments.size(); i++){
                System.out.println( i + 1  + "\n- created by: " + comments.get(i).getUser().getAccountName());
                System.out.println("\n -creation Date: " + comments.get(i).getCreationDate());
                System.out.println("\n -Content: " + comments.get(i).getText());
                System.out.println("1-to choose comment\n2-to see next comment\n3-to return to postDashboard");
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

        } catch (NullPointerException e){
            e.printStackTrace();
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
            if(UserContext.getCurrentUser().FriendType.get(user) != null){
                if(UserContext.getCurrentUser().FriendType.get(user) != FriendType.restricted){
                    timeline.addAll(userPosts);
                }
            }else{
                for(Post post: userPosts){
                    if(post.isPrivacyOption() != PrivacyOption.FRIENDS){
                        timeline.add(post);
                    }
                }
            }
        });
        Collections.shuffle(timeline);

        return timeline;
    }
}
