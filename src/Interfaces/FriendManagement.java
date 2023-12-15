package Interfaces;

import Enums.FriendType;
import Models.Users.User;

import java.util.ArrayList;
import java.util.Set;

public interface FriendManagement {
    void acceptRequest(User user, FriendType friendType);

    ArrayList<User> getSentFriendRequests();

    void SentFriendRequest(User user);

    void removeSentFriendRequest(User user);

    Set<User> getFriends();

    FriendType getFriendType(User user);

    ArrayList<User> getMutual(User user);
}