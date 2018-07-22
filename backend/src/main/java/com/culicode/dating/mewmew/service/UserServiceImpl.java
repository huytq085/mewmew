package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.AppRole;
import com.culicode.dating.mewmew.domain.ERole;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.domain.UserRole;
import com.culicode.dating.mewmew.repository.UserRepository;
import com.culicode.dating.mewmew.repository.UserRoleRepository;
import com.culicode.dating.mewmew.util.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

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
            newUser = userRepository.save(user);
            userRoleRepository.setRole(newUser.getId(), ERole.USER.getId());

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


}
