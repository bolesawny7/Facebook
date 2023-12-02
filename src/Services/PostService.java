package Services;

import Models.Post;
import Views.Dashboard;

import java.util.ArrayList;

public class PostService {
    Dashboard dashboard = new Dashboard();

    private ArrayList<Post> posts = new ArrayList<Post>();

    public void like() {

    }
    public void comment() {
        dashboard.commentsDashboard();
    }
    public void share() {

    }

}
