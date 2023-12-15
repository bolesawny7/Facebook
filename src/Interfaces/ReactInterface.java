package Interfaces;

import Enums.ReactType;
import Models.Comment;
import Models.Users.User;

public interface ReactInterface {
    void react(User user, Comment comment);
}
