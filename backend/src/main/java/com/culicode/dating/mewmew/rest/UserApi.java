package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.Article;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.service.UserService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class UserApi {
    private static final Logger LOG = LogManager.getLogger(Article.class);
    private final String BASE_URI = "/users";
    private final String USERNAME_URI = BASE_URI + "/{username}";
    private final String ID_URI = BASE_URI + "/id/{id}";
    private final String FRIENDS_URI = BASE_URI + "/{username}/friends";
    private final String FOLLOW_URI = BASE_URI + "/{userId}/follow";
    private final String UNFOLLOW_URI = BASE_URI + "/{userId}/unfollow";
    private final String IS_FOLLOWING_URI = BASE_URI + "/{user1}/isfollowing/{user2}";
    private final String ADD_FRIEND_URI = BASE_URI + "/{userId}/addfriend";
    private final String UN_FRIEND_URI = BASE_URI + "/{userId}/unfriend";
    private final String ACCEPT_FRIEND_URI = BASE_URI + "/{userId}/acceptfriend";

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User create(@RequestBody User user) {
        System.out.println(JsonUtil.encode(user));
        return userService.save(user);
    }

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> getAll(@RequestParam(value = "q", required = false) String query) {
        if (query != null) {
            return userService.search(query);
        }
        return userService.getAllUser();
    }

    @RequestMapping(
            value = FRIENDS_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> getFriends(@PathVariable String username) {
        return userService.getFriends(username);
    }

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<String, Object> update(@RequestBody User user) {
        System.out.println(JsonUtil.encode(user));
        Map<String, Object> map = new HashMap<>();
        try {
            user = userService.update(user);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            e.printStackTrace();
        }
        map.put("user", user);
        return map;
    }

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User delete(int id) {
        return null;
    }

    @RequestMapping(
            value = USERNAME_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable String username, @RequestParam(value = "isFollowedBy", required = false) String userId) {
        User user = userService.findByUsername(username);
        LOG.info("get user");
        LOG.info(userId);
        LOG.debug(userId);
        if (userId != null && !userId.equals("undefined")) {
            user.setFollowing(this.userService.isFollowing(user.getId(), Integer.parseInt(userId)));
            user.setFriendStatus(this.userService.getFriendStatus(Integer.parseInt(userId), user.getId()));
        }
        return user;
    }

    @RequestMapping(
            value = ID_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable int id, @RequestParam(value = "isFollowedBy", required = false) String userId) {
        User user = userService.findById(id);
        LOG.info("get user");
        LOG.info(userId);
        LOG.debug(userId);
        if (userId != null && !userId.equals("undefined")) {
            user.setFollowing(this.userService.isFollowing(user.getId(), Integer.parseInt(userId)));
        }
        return user;
    }

    @RequestMapping(
            value = FOLLOW_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int follow(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to follow " + userId);
        return this.userService.follow(userId, user.getId());
    }

    @RequestMapping(
            value = UNFOLLOW_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int unFollow(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to unfollow " + userId);
        return this.userService.unFollow(userId, user.getId());
    }

    @RequestMapping(
            value = IS_FOLLOWING_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isFollowing(@PathVariable int user1, @PathVariable int user2) {
        LOG.info("Check if user1 is following user2");
        return this.userService.isFollowing(user1, user2);
    }

    @RequestMapping(
            value = ADD_FRIEND_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int addFriend(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to add friend " + userId);
        return this.userService.addFriend(user.getId(), userId);
    }

    @RequestMapping(
            value = UN_FRIEND_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int unFriend(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to unfriend " + userId);
        return this.userService.unFriend(user.getId(), userId);
    }

    @RequestMapping(
            value = ACCEPT_FRIEND_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int acceptFriend(@PathVariable int userId, @RequestBody User user) {
        LOG.info(user.getUsername() + " want to accept friend " + userId);
        return this.userService.acceptFriend(userId, user.getId());
    }

}