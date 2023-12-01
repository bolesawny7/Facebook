package Models.Users;

import Enums.RelationshipStatus;
import Models.Comment;
import Models.Post;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends User {

    private static ArrayList<User> bannedUsers = new ArrayList<User>();
    public Admin(String email, String last_name, String first_name, String password, RelationshipStatus status, boolean gender, Date birth_date, String phone) {
        super(email, last_name, first_name, password, status, gender, birth_date, phone);
    }

    public Admin(String email, String last_name, String first_name, String password, RelationshipStatus status, boolean gender, Date birth_date) {
        super(email, last_name, first_name, password, status, gender, birth_date);
    }

    public Admin(String email, String last_name, String first_name, String password, String phone, boolean gender, Date birth_date) {
        super(email, last_name, first_name, password, phone, gender, birth_date);
    }

    public Admin(String email, String last_name, String first_name, String password, boolean gender, Date birth_date) {
        super(email, last_name, first_name, password, gender, birth_date);
    }

    public void deleteOtherPost(Post post) {
        post = null;
    }

    public void deleteOtherComment(Comment comment) {
        comment = null;
    }

    public void banUsers(User user) {
        bannedUsers.add(user);
    }

    public ArrayList<User> getBanList() {
        return bannedUsers;
    }
}
