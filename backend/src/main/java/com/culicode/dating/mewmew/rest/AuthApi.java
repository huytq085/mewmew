package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.AuthorizedUser;
import com.culicode.dating.mewmew.domain.Token;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.security.service.JwtService;
import com.culicode.dating.mewmew.service.UserService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class AuthApi {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/auth",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorizedUser> auth(@RequestBody Token token){
        System.out.println("Token: " + token.getToken());
        User user = null;
        HttpStatus httpStatus = null;
        String message = "";
        if (jwtService.validateTokenLogin(token.getToken())) {
            String username = jwtService.getUsernameFromToken(token.getToken());
            user = userService.findByUsername(username);
            message = "Get user successful";
            httpStatus = HttpStatus.OK;
        } else {
            message = "Get user failed";
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(new AuthorizedUser(user, token.getToken(), message), httpStatus);
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorizedUser> login(@RequestBody User credentials) {
        System.out.println(credentials.getEmail());
        System.out.println(credentials.getPassword());
        User user = null;
        String result = "";
        HttpStatus httpStatus = null;
        String token = "";
        String message = "";
        try {
            user = userService.checkLogin(credentials.getEmail(), credentials.getPassword());
            System.out.println(JsonUtil.encode(user));
            if (user != null) {
                token = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
                message = "Login successfully";
            } else {
                message = "Wrong userId and password";
                httpStatus = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception ex) {
            message = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new AuthorizedUser(user, token, message), httpStatus);
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorizedUser> register(@RequestBody User credentials) {
        System.out.println(credentials.getUsername());
        System.out.println(credentials.getEmail());
        System.out.println(credentials.getPassword());
        System.out.println(credentials.getGender());
        User user = null;
        HttpStatus httpStatus = null;
        String token = "";
        String message = "";
        try {
            user = userService.save(credentials);
            System.out.println(JsonUtil.encode(user));
            if (user != null) {

                token = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
                message = "Successful";
            } else {
                message = "Username or Email already exist";
                httpStatus = HttpStatus.CONFLICT;
            }
        } catch (Exception ex) {
            message = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new AuthorizedUser(user, token, message), httpStatus);
    }
}
