package Models;
import java.text.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Comments {

    private HashMap<User, ArrayList<Comment>> userComments;

    public Comments() {
        this.userComments = new HashMap<>();
    }

    public void addComment(User user, String text) {
        Comment comment = new Comment(user, text);
        userComments.computeIfAbsent(user, k -> new ArrayList<>()).add(comment);
        System.out.println("Comment added successfully");
    }

    public void deleteComment(User user, int commentIndex, String text) {
        if (userComments.containsKey(user)) {
            ArrayList<Comment> comments = userComments.get(user);
            if (commentIndex >= 0 && commentIndex < comments.size()) {
                comments.remove(commentIndex);
                System.out.println("Comment deleted successfully");
            } else {
                System.out.println("Comment index out of bounds");
            }
        } else {
            System.out.println("User not found");
        }
    }

}
