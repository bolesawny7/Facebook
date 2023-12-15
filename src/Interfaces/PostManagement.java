package Interfaces;

import Enums.PrivacyOption;
import Models.Post;
import Models.Users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PostManagement {
    void createPost(User user, LocalDateTime date, PrivacyOption privacyOption, String content, ArrayList<User> tagged);
    void createPost(User user, LocalDateTime date, PrivacyOption privacyOption, String content);
    void sharePost(Post post);
    ArrayList<Post> getPosts();
    ArrayList<Post> getTaggedPostsWithFriend(User friend);
}