package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.service.UserService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApiImpl implements UserApi{
    private static final Logger LOG = LogManager.getLogger(Article.class);
    @Autowired
    private UserService userService;

    @Override
    public User create(@RequestBody User user) {
        System.out.println(JsonUtil.encode(user));
        return userService.save(user);
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUser();
    }

    @Override
    public User update(@RequestBody User user) {
        System.out.println(JsonUtil.encode(user));
        return userService.update(user);
    }

    @Override
    public User delete(int id) {
        return null;
    }

    @Override
    public User get(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @Override
    public int follow(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to follow " + userId);
        return this.userService.follow(userId, user.getId());
    }

    @Override
    public int unFollow(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to unfollow " + userId);
        return this.userService.unFollow(userId, user.getId());
    }

    @Override
    public boolean isFollowing(@PathVariable int user1, @PathVariable int user2){
        LOG.info("Check if user1 is following user2");
        return this.userService.isFollowing(user1, user2);
    }

}