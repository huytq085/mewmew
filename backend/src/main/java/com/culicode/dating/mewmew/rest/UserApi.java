package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.service.UserService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    private final String BASE_URI = "/users";
    private final String ID_URI = BASE_URI + "/{id}";

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
    public List<User> getAll() {
        return userService.getAllUser();
    }

    @RequestMapping(
            value = BASE_URI,
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User update(@RequestBody User user) {
        System.out.println(JsonUtil.encode(user));
        return userService.save(user);
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
            value = ID_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable int id) {
        return userService.findById(id);
    }


}
