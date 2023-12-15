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


/**
 * This class provides services related to posts.
 * It includes methods for liking, sharing, commenting on posts, and getting reacts and comments.
 */
public class PostService {
    Scanner input = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * This method allows a user to react to a post.
     * It prompts the user to choose a reaction type, then adds the reaction to the post.
     * If the reaction type is invalid, it will prompt the user to enter a valid reaction.
     *
     * @param post        The post to which the user is reacting
     * @param currentUser The user who is reacting to the post
     */
    public void like(Post post, User currentUser) {
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

    /**
     * This method allows a user to share a post.
     *
     * @param account The user who is sharing the post
     * @param post    The post that is being shared
     */
    public void share(User account, Post post) {
        account.sharePost(post);
    }

    /**
     * This method allows a user to write a comment on a post.
     * It prompts the user to enter the content of the comment, then adds the comment to the post.
     *
     * @param post The post on which the user is commenting
     * @param user The user who is writing the comment
     */
    public void writeComment(Post post, User user) {
        System.out.println("content :");
        String ans = null;
        try {
            ans = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        post.addComment(user, ans);
    }

    /**
     * This method retrieves the reactions to a post.
     * It prints the details of each reaction and provides options for the user to choose a reaction, see the next reaction, or return to the post dashboard.
     *
     * @param post The post whose reactions are being retrieved
     * @return A list of reactions to the post, or null if the user chooses to return to the post dashboard
     */
    public ArrayList<React> getReacts(Post post) {
        ArrayList<React> reacts = post.getReacts();

        try {
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

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("press any key to return to postServices");
        String ans = input.next();
        return null;
    }

    /**
     * This method retrieves the comments on a post.
     * It prints the details of each comment and provides options for the user to choose a comment, see the next comment, or return to the post dashboard.
     *
     * @param post The post whose comments are being retrieved
     * @return The chosen comment, or null if the user chooses to return to the post dashboard
     */
    public Comment getComments(Post post) {
        ArrayList<Comment> comments = post.getComments();
        try {
            for (int i = 0; i < comments.size(); i++) {
                System.out.println(i + 1 + "\n- created by: " + comments.get(i).getUser().getAccountName());
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

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("press any key to return to postServices");
        String ans = input.next();
        return null;
    }

    /**
     * This method generates a timeline of posts from a list of users.
     * It adds all posts from each user to the timeline, unless the user is restricted or the post is set to friends only.
     * The timeline is then shuffled to randomize the order of posts.
     *
     * @param clients The users whose posts are being added to the timeline
     * @return A shuffled list of posts for the timeline
     */
    static ArrayList<Post> Timeline(ArrayList<Client> clients) {
        ArrayList<Post> timeline = new ArrayList<>();

        clients.forEach((User user) -> {
            ArrayList<Post> userPosts = user.getPosts();
            if (UserContext.getCurrentUser().FriendType.get(user) != null) {
                if (UserContext.getCurrentUser().FriendType.get(user) != FriendType.restricted) {
                    timeline.addAll(userPosts);
                }
            } else {
                for (Post post : userPosts) {
                    if (post.isPrivacyOption() != PrivacyOption.FRIENDS) {
                        timeline.add(post);
                    }
                }
            }
        });
        Collections.shuffle(timeline);

        return timeline;
    }
}
