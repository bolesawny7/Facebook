package Views;

import Models.Comment;
import Models.Group;
import Models.Post;
import Models.Users.User;
import Services.CommentsService;
import Services.ConversationService;
import Services.PostService;
import Services.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Dashboard {
    Scanner input = new Scanner(System.in);

    public int mainMenu(UserService userService) {
        System.out.println("1-login");
        System.out.println("2-signUP");
        String y = input.next();
        switch (y) {
            case "1":
                UserContext.setCurrentUser(userService.login());
                if (UserContext.getCurrentUser() == null)
                    return 1;
                else
                    return 2;
            case "2":
                userService.signUp();
                return 1;
            default:
                System.out.println("please enter 1 or 2");
                return 1;
        }
    }

    public int userDashboard(UserService userService, User user) {
        System.out.println("1-see your friends");
        System.out.println("2-see your posts");
        System.out.println("3-see your groups");
        System.out.println("4-see your Conversations");
        System.out.println("5-send a friend requests");
        System.out.println("6-write a post");
        System.out.println("7-join a group");
        System.out.println("8-Received Friend Requests");
        System.out.println("9-pending Friend Requests");
        System.out.println("10-see the timeLine");
        System.out.println("11-conversations");
        System.out.println("12-see friendship");
        System.out.println("13-see mutual friends");
        System.out.println("14- logout");
        System.out.println("15- close app");

        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                userService.seeFriends();
                return 2;
            case 2:
                UserContext.setSelectedPost(userService.seePosts());
                if (UserContext.getSelectedPost() != null)
                    return 3;
                else
                    return 2;
            case 3:
                userService.seeGroups();
                return 2;
            case 4:
                userService.seeConversations();
                return 2;
            case 5:
                userService.sendFriendRequest(user);
                return 2;
            case 6:
                UserContext.setSelectedPost(userService.writePost(user));
                return 3;
            case 7:
                userService.joinGroup();
                return 2;
            case 8:
                userService.getFriendRequests(user);
                return 2;
            case 9:
                userService.getSentFriendRequests(user);
                return 2;
            case 10:
                UserContext.setSelectedPost(userService.seeTimeline());
                if (UserContext.getSelectedPost() != null)
                    return 3;
                else
                    return 2;
            case 11:
                return 5;
            case 12:
                userService.getFriendship(user);
                return 2;
            case 13:
                userService.getMutualFriends(user);
                return 2;
            case 14:
                return 1;
            case 15:
                //it does nothing
                return 6;
            default:
                System.out.println("please enter a valid number");
                return 2;
        }
    }

    public int postDashboard(PostService postService, User user, Post post) {
        System.out.println("1-React or change React");
        System.out.println("2-add comment");
        System.out.println("3-get comments");
        System.out.println("4-get reacts");
        System.out.println("5-share");
        System.out.println("6-back to user dashboard");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                postService.like(post, user);
                return 3;
            case 2:
                postService.writeComment(post, user);
                return 3;
            case 3:
                UserContext.setSelectedComment(postService.getComments(post));
                if (UserContext.getSelectedComment() != null)
                    return 4;
                else
                    return 3;
            case 4:
                postService.getReacts(post);
                return 3;
            case 5:
                postService.share(user, post);
                return 3;
            case 6:
                return 2;
            default:
                System.out.println("please enter a valid number");
                return 3;
        }
    }

    public int commentsDashboard(CommentsService commentService, User user, Post post, Comment comment) {
        System.out.println("1-react");
        System.out.println("2-reply");
        System.out.println("3-back to post dashboard");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                commentService.react(user, comment);
                return 4;
            case 2:
                commentService.reply(user, comment);
                return 4;
            case 3:
                return 3;
            default:
                System.out.println("please enter a valid number");
                return 4;
        }
    }

    public int ConversationsDashboard(ConversationService conversationService, User user) {
        System.out.println("1-see Conversations");
        System.out.println("2-send a message");
        System.out.println("3-back to user dashboard");
        Scanner input = new Scanner(System.in);
        int y = input.nextInt();
        switch (y) {
            case 1:
                User chosenUserToChatWith = conversationService.seeConversations(user);
                if (chosenUserToChatWith != null) {
                    conversationService.seeConversationContent(user, chosenUserToChatWith);
                    return 5;
                } else
                    return 2;
            case 2:
                conversationService.sendMessage();
                return 5;
            case 3:
                return 2;
            default:
                System.out.println("please enter a valid number");
                return 5;
        }
    }
}
