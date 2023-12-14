package Models.Users;

import Enums.Gender;
import Enums.RelationshipStatus;
import Models.Post;
import Models.Users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Client extends User {
    public Client(String email, String last_name, String first_name, String password, RelationshipStatus status, Gender gender, LocalDate birth_date, String phone) {
        super(email, last_name, first_name, password, status, gender, birth_date, phone);
    }

    public Client(String email, String last_name, String first_name, String password, RelationshipStatus status, Gender gender, LocalDate birth_date) {
        super(email, last_name, first_name, password, status, gender, birth_date);
    }

    public Client(String email, String last_name, String first_name, String password, String phone, Gender gender, LocalDate birth_date) {
        super(email, last_name, first_name, password, phone, gender, birth_date);
    }

    public Client(String email, String last_name, String first_name, String password, Gender gender, LocalDate birth_date) {
        super(email, last_name, first_name, password, gender, birth_date);
    }

    public ArrayList<Post> getTaggedPostsWithFriend(User friend) {
        ArrayList<Post> taggedPosts = new ArrayList<>();
        for (Post post : posts)
        {
            if (post.getTaggedUsers().contains(friend))
                taggedPosts.add(post);
        }
        return taggedPosts;
    }
}
