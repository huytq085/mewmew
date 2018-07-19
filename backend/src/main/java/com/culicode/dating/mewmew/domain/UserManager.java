package com.culicode.dating.mewmew.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UserManager {
    private static UserManager instance = null;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public Set<User> getFollowersOf(User user) {
        Set<User> followers = new HashSet<>();
//        for (FriendShip friendShip : user.getFollowers()) {
//            followers.add(friendShip.getFollower());
//        }
        return followers;
    }

    public Set<User> getFollowingsOf(User user) {
        Set<User> followings = new HashSet<>();
//        for (FriendShip friendShip : user.getFollowings()) {
//            followings.add(friendShip.getFollowing());
//        }
        return followings;
    }
}
