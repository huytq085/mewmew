package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);

    User update(User user);

    List<User> getAllUser();

    User findById(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User checkLogin(String username, String password);

    int follow(int user1, int user2);

    int unFollow(int user1, int user2);

    boolean isFollowing(int user1, int user2);

    List<User> search(String query);

    int addFriend(int user1, int user2);

    int unFriend(int user1, int user2);

    int acceptFriend(int user1, int user2);

    Integer getFriendStatus(int user1, int user2);

}
