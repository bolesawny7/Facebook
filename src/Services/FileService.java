package Services;

import Enums.FriendType;
import Enums.PrivacyOption;
import Enums.ReactType;
import Models.Comment;
import Models.Post;
import Models.React;
import Models.Users.Client;
import Models.Users.User;
import Views.UserContext;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public class FileService {

    public void saveAllPosts(UserService userService) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("Posts.txt"));
            for (User user : userService.getClients()) {
                for (int i = 0; i < user.getPosts().size(); i++) {
                    writer.write(user.getPosts().get(i).getPostId() + "~" + user.getPosts().get(i).getCreatedBy().getId() + "~" + user.getPosts().get(i).getCreationDate() + "~" + user.getPosts().get(i).isPrivacyOption() + "~" + user.getPosts().get(i).getContent() + "~" + user.getPosts().get(i).getTaggedUsers().size() + "~");
                    for (int j = 0; j < user.getPosts().get(i).getTaggedUsers().size(); j++) {
                        writer.write(user.getPosts().get(i).getTaggedUsers().get(j).getId() + "~");
                    }
                    writer.write(user.getPosts().get(i).getReacts().size() + "~");
                    for (int j = 0; j < user.getPosts().get(i).getReacts().size(); j++) {
                        writer.write(user.getPosts().get(i).getReacts().get(j).getReact() + "~" + user.getPosts().get(i).getReacts().get(j).getUser().getId() + "~");
                    }
                    writer.write(user.getPosts().get(i).getComments().size() + "~");
                    for (int j = 0; j < user.getPosts().get(i).getComments().size(); j++) {
                        writer.write(user.getPosts().get(i).getComments().get(j).getCommentID() + "~" + user.getPosts().get(i).getComments().get(j).getUser().getId() + "~" + user.getPosts().get(i).getPostId() + "~" + user.getPosts().get(i).getComments().get(j).getText() + "~" + user.getPosts().get(i).getComments().get(j).getReacts().size() + "~");
                        for (int k = 0; k < user.getPosts().get(i).getComments().get(j).getReacts().size(); k++) {
                            writer.write(user.getPosts().get(i).getComments().get(j).getReacts().get(k).getReact() + "~" + user.getPosts().get(i).getComments().get(j).getReacts().get(k).getUser().getId() + "~");
                        }
                    }
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void readAllPosts() {
        BufferedReader reader;
        String post;
        try {
            reader = new BufferedReader(new FileReader("Posts.txt"));
            while ((post = reader.readLine()) != null) {
                String[] postInfo = post.split("~");
                int postId = Integer.parseInt(postInfo[0]);
                int userId = Integer.parseInt(postInfo[1]);
                String creationDate = postInfo[2];
                PrivacyOption privacyOption = PrivacyOption.valueOf(postInfo[3]);
                String content = postInfo[4];
                int taggedUsersSize = Integer.parseInt(postInfo[5]);
                ArrayList<User> taggedUsers = new ArrayList<>();
                for (int i = 0; i < taggedUsersSize; i++) {
                    int taggedUserId = Integer.parseInt(postInfo[6 + i]);
                    taggedUsers.add(UserService.clients.get(taggedUserId - 1));
                }
                int reactsSize = Integer.parseInt(postInfo[6 + taggedUsersSize]);
                ArrayList<React> reacts = new ArrayList<>();
                for (int i = 0; i < reactsSize; i++) {
                    String react = postInfo[7 + taggedUsersSize + 2 * i];
                    int reactUserId = Integer.parseInt(postInfo[8 + taggedUsersSize + 2 * i]);
                    reacts.add(new React(UserService.clients.get(reactUserId - 1), ReactType.valueOf(react.toLowerCase())));
                }
                int commentsSize = Integer.parseInt(postInfo[7 + taggedUsersSize + 2 * reactsSize]);
                ArrayList<Comment> comments = new ArrayList<>();
                for (int i = 0; i < commentsSize; i++) {
                    int commentUserId = Integer.parseInt(postInfo[9 + taggedUsersSize + 2 * reactsSize + 5 * i]);
                    int commentPostId = Integer.parseInt(postInfo[10 + taggedUsersSize + 2 * reactsSize + 5 * i]);
                    String commentText = postInfo[11 + taggedUsersSize + 2 * reactsSize + 5 * i];
                    int commentReactsSize = Integer.parseInt(postInfo[12 + taggedUsersSize + 2 * reactsSize + 5 * i]);
                    ArrayList<React> commentReacts = new ArrayList<>();
                    for (int j = 0; j < commentReactsSize; j++) {
                        String commentReact = postInfo[13 + taggedUsersSize + 2 * reactsSize + 5 * i + 2 * j];
                        int commentReactUserId = Integer.parseInt(postInfo[14 + taggedUsersSize + 2 * reactsSize + 5 * i + 2 * j]);
                        commentReacts.add(new React(UserService.clients.get(commentReactUserId - 1), ReactType.valueOf(commentReact.toLowerCase())));
                    }
                    comments.add(new Comment(UserService.clients.get(commentUserId - 1), commentText));
                    comments.get(i).setReacts(commentReacts);
                }
                LocalDateTime creationDate1 = LocalDateTime.parse(creationDate);
                Post post1 = new Post(UserService.clients.get(userId - 1), creationDate1, privacyOption, content, taggedUsers);
                post1.setComments(comments);
                post1.setReacts(reacts);
                UserService.clients.get(userId - 1).getPosts().add(post1);
            }
            reader.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveAllUsers(ArrayList<Client> users) {
        BufferedWriter writer;
        {
            try {
                writer = new BufferedWriter(new FileWriter("Users.txt"));
                for (Client user : users) {
                    ///constructor order
                    writer.write(user.getEmail() + " " + user.getLast_name() + " " + user.getFirst_name() + " " + user.getPassword() + " " + user.getGender() + " " + user.getBirth_date() + '\n');
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<String> ReadAllUsers() {
        BufferedReader reader;
        String user;
        ArrayList<String> usersData = new ArrayList<>();
        {
            try {
                reader = new BufferedReader(new FileReader("Users.txt"));
                //read all lines till end of the file
                while ((user = reader.readLine()) != null) {
                    usersData.add(user);
//                    System.out.println(user);
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return usersData;
    }

    public void saveALlFriends(ArrayList<Client> users) {
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("friends.txt"));


            for (Client user : users) {
                {
                    Set<User> friends = user.getFriends();
                    writer.write(user.getId() + " ");
                    for (User friend : friends) {
                        writer.write(friend.getId() + " " + user.getFriendType(friend) + " ");
                    }
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readUserFrinends(ArrayList<Client> clients) {
        String[] friendIds = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("friends.txt"));
            String line;
            for (Client user : clients) {
                int id = user.getId();
                while ((line = reader.readLine()) != null) {
                    friendIds = line.split(" ");
                    int thisId = Integer.parseInt(friendIds[0]);
                    if (thisId == id) {
                        break;
                    }
                }
                for (int i = 1; i < friendIds.length; i += 2) {
                    int friendId = Integer.parseInt(friendIds[i]);
                    FriendType friendType = FriendType.valueOf(friendIds[i + 1].toLowerCase());
                    clients.get(id - 1).setFriendType(clients.get(friendId - 1), friendType);
                }
            }
            reader.close();
        } catch (IOException e) {
            return;
        }
    }
}
