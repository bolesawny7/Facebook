package Interfaces;

import Enums.PrivacyOption;
import Models.Post;
import Models.Users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PostManagement {
    void sharePost(Post post);
    ArrayList<Post> getPosts();
    ArrayList<Post> getTaggedPostsWithFriend(User friend);
}