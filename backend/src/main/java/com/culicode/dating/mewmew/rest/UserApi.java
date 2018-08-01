package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public interface UserApi {

    String BASE_URI = "/users";
    String USERNAME_URI = BASE_URI + "/{username}";
    String ID_URI = BASE_URI + "/id/{id}";
    String FOLLOW_URI = BASE_URI + "/{userId}/follow";
    String UNFOLLOW_URI = BASE_URI + "/{userId}/unfollow";
    String IS_FOLLOWING_URI = BASE_URI + "/{user1}/isfollowing/{user2}";

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    User create(@RequestBody User user);


    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<User> getAll(@RequestParam(value = "q", required = false) String query);

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    User update(@RequestBody User user);

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    User delete(int id);

    @RequestMapping(
            value = USERNAME_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    User get(@PathVariable String username, @RequestParam(value = "isFollowedBy", required = false) String userId);

    @RequestMapping(
            value = ID_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    User getById(@PathVariable int id, @RequestParam(value = "isFollowedBy", required = false) String userId);

    @RequestMapping(
            value = FOLLOW_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int follow(@PathVariable int userId, @RequestBody User user);

    @RequestMapping(
            value = UNFOLLOW_URI,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    int unFollow(@PathVariable int userId, @RequestBody User user);

    @RequestMapping(
            value = IS_FOLLOWING_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isFollowing(@PathVariable int user1, @PathVariable int user2);



}
