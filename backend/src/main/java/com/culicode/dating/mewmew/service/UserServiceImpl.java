package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.*;
import com.culicode.dating.mewmew.repository.AppRoleRepository;
import com.culicode.dating.mewmew.repository.UserRepository;
import com.culicode.dating.mewmew.repository.UserRoleRepository;
import com.culicode.dating.mewmew.util.Constants;
import com.culicode.dating.mewmew.util.EncrytedPasswordUtils;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger(Article.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    private boolean isExist(User user){
        if (userRepository.findByEmail(user.getEmail()).orElse(null) != null
                || userRepository.findByUsername(user.getUsername()).orElse(null) != null){
            System.out.println("Email or Username Exist============================");
            return true;
        }
        return false;
    }


    @Override
    public User save(User user) {
        User newUser = null;
        if (!isExist(user)){
//            user.setPassword(EncrytedPasswordUtils.encrytePassword(user.getPassword()));
            if (user.getAvatar() == null || user.getAvatar() == ""){
                user.setAvatar(Constants.DEFAULT_AVATAR);
            }
            newUser = userRepository.save(user);
//            userRoleRepository.setRole(newUser.getId(), ERole.USER.getId());
            System.out.println("User role: " + ERole.USER.getId());
            AppRole appRole = appRoleRepository.findById(ERole.USER.getId()).orElse(null);
            System.out.println("-------UserServiceImpl");
            if (appRole != null) {
                System.out.println(JsonUtil.encode(appRole));
                UserRole userRole = new UserRole();
                userRole.setAppRole(appRole);
                userRole.setUser(newUser);
                if (userRoleRepository.save(userRole).getId() != null) {
                    System.out.println("Set role successful");
                } else {
                    System.out.println("Set role failed");
                }
            }

        }
        return newUser;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public User checkLogin(String email, String password) {
//        User user = userRepository.findByEmail(email).orElse(null);
//        if (user == null || !EncrytedPasswordUtils.matches(password, user.getPassword())){
//            return null;
//        }
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public int follow(int user1, int user2) {
        LOG.info("Service is running...");
        return userRepository.follow(user1, user2);
    }

    @Override
    public int unFollow(int user1, int user2) {
        LOG.info("Service is running...");
        return userRepository.unFollow(user1, user2);
    }

    @Override
    public boolean isFollowing(int user1, int user2) {
        LOG.info("Service is running...");
        return 1 == this.userRepository.isFollowing(user1, user2);
    }


}
