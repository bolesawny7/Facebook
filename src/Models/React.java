package Models;

import Enums.ReactType;
import Models.Users.User;

public class React {
    private ReactType react;
    private User user;

    //----constructor--//
    public React(User user, ReactType react) {
        this.react = react;
        this.user = user;
    }

     public void setReact(ReactType react) {
        this.react = react;
    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public User getUser() {
        return user;
    }
    public ReactType getReact() {
        return react;
    }

}
