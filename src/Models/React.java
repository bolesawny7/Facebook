package Models;

public class React {
    private ReactType react;
    private User user;


    //----constructor--//
    public React(User user, ReactType react) {
        this.react = react;
        this.user = user;
    }


    public void changeUserReact(User user , ReactType react) {
        this.react = react;
    }

//    public User getUser() {
//        return user;
//    }
//    public ReactType getReact() {
//        return react;
//    }

}
