package Views;

import Models.Post;
import Models.Users.User;
import Services.CommentsService;
import Services.ConversationService;
import Services.PostService;
import Services.UserService;

import java.util.Scanner;

public class Dashboard {

    Scanner input = new Scanner(System.in);


    public void mainMenu(CommentsService commentsService, PostService postService, UserService userService) {
        System.out.println("1-login");
        System.out.println("2-signUP");
        String y = input.next();
        switch (y) {
            case "1":
                userService.login(commentsService, postService, userService);
                break;
            case "2":
                userService.signUp(commentsService, postService, userService);
                break;
            default:
                System.out.println("please enter 1 or 2");
                mainMenu(commentsService,postService,userService);
        }
    }

    public void userDashboard(CommentsService commentsService, PostService postService,UserService userService, User user) {
        System.out.println("1-see your friends");
        System.out.println("2-see your posts");
        System.out.println("3-see your groups");
        System.out.println("4-see your Conversations");
        System.out.println("5-send a friend request");
        System.out.println("6-write a post");
        System.out.println("7-join a group");
        System.out.println("8-logout");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                userService.seeFriends();
                break;
            case 2:
                userService.seePosts(commentsService, postService,userService);
                break;
            case 3:
                userService.seeGroups();
                break;
            case 4:
                userService.seeConversations();
                break;
            case 5:
                userService.sendFriendRequest(user);
                break;
            case 6:
                userService.writePost();
                break;
            case 7:
                userService.joinGroup();
                break;
            case 8:
                userService.logout(commentsService,postService,userService);
                break;
            case 9:
                userService.getFriendRequests(user);

            default:
                System.out.println("please enter a valid number");
        }
    }

    public void postDashboard(CommentsService commentsService, PostService postService,UserService userService, User user, Post post) {
        System.out.println("1-like");
        System.out.println("2-comment");
        System.out.println("3-share");
        System.out.println("4-back to user dashboard");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                postService.like();
                break;
            case 2:
                postService.comment(commentsService, postService, userService,user, post);
                break;
            case 3:
                postService.share(user, post);
                break;
            case 4:
                userDashboard(commentsService, postService,userService,user);
                break;
            default:
                System.out.println("please enter a valid number");
        }
    }

    public void commentsDashboard(CommentsService commentService, PostService postService, UserService userService,User user, Post post) {
        System.out.println("1-like");
        System.out.println("2-reply");
        System.out.println("3-back to post dashboard");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                commentService.react();
                break;
            case 2:
                commentService.reply();
                break;
            case 3:
                postDashboard(commentService, postService,userService, user, post);
                break;
            default:
                System.out.println("please enter a valid number");
        }
    }

    public void ConversationDashboard(CommentsService commentsService, PostService postService, ConversationService conversationService,UserService userService,User user) {
        System.out.println("1-see messages");
        System.out.println("2-send a message");
        System.out.println("3-back to user dashboard");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                conversationService.seeMessages();
                break;
            case 2:
                conversationService.sendMessage();
                break;
            case 3:
                userDashboard(commentsService, postService,userService,user);
                break;
            default:
                System.out.println("please enter a valid number");
        }
    }
}
